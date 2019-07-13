package kz.kakimzhanova.parser.parser.sax;

import kz.kakimzhanova.parser.entity.Paper;
import kz.kakimzhanova.parser.parser.PaperEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaperHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private List<Paper> papers;
    private Paper current = null;
    private PaperEnum currentEnum = null;
    private EnumSet<PaperEnum> withText;

    public PaperHandler() {
        papers = new ArrayList<>();
        withText = EnumSet.range(PaperEnum.COLOR, PaperEnum.SUBSCRIPTION);
    }

    public List<Paper> getPapers(){
        return papers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("paper".equals(localName)){
            current = new Paper();
            Pattern titlePattern = Pattern.compile("[A-Z][a-z]+");
            Pattern monthlyPattern = Pattern.compile("(true|false)");
            Pattern typePattern = Pattern.compile("(newspaper|magazine|booklet)");
            for (int i = 0; i < attributes.getLength(); i++){
                String value = attributes.getValue(i);
                Matcher titleMatcher = titlePattern.matcher(value);
                Matcher monthlyMatcher = monthlyPattern.matcher(value);
                Matcher typeMatcher = typePattern.matcher(value);
                if (titleMatcher.matches()) {
                    current.setTitle(value);
                } else if (typeMatcher.matches()){
                    current.setType(value);
                } else if ((attributes.getLength() == 3)&&(monthlyMatcher.matches())){
                    current.setMonthly(Boolean.valueOf(value));
                }else {
                    throw new SAXNotRecognizedException("wrong attribute value " + value);
                }

            }
        } else {
            PaperEnum tmp = PaperEnum.valueOf(localName.toUpperCase());
            if (withText.contains(tmp)){
                currentEnum = tmp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("paper".equals(localName)){
            papers.add(current);
        }
        currentEnum = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length);
        if ((currentEnum != null)&&(current != null)){
            switch (currentEnum){
                case COLOR:
                    current.getChars().setColor(Boolean.valueOf(s));
                    break;
                case GLOSSY:
                    current.getChars().setGlossy(Boolean.valueOf(s));
                    break;
                case VOLUME:
                    try {
                        current.getChars().setVolume(Integer.parseInt(s));
                    }catch (NumberFormatException e){
                        logger.log(Level.WARN, e);
                    }
                    break;
                case SUBSCRIPTION:
                    current.getChars().setSubscription(Boolean.valueOf(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(  currentEnum.getDeclaringClass(), "No such Enum: " + s);

            }
        }
    }
}
