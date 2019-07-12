package kz.kakimzhanova.parser.parser.sax;

import kz.kakimzhanova.parser.entity.Paper;
import kz.kakimzhanova.parser.parser.PaperEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PaperHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    private Set<Paper> papers;
    private Paper current = null;
    private PaperEnum currentEnum = null;
    private EnumSet<PaperEnum> withText;

    public PaperHandler() {
        papers = new HashSet<>();
        withText = EnumSet.range(PaperEnum.COLOR, PaperEnum.SUBSCRIPTION);
    }

    public Set<Paper> getPapers(){
        return papers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //logger.log(Level.INFO, "start element " + localName + " current: " + currentEnum);
        if("paper".equals(localName)){
            current = new Paper();
            current.setTitle(attributes.getValue(0));//add atributes checking
            current.setType(attributes.getValue(1));
            if (attributes.getLength() == 3){
                current.setMonthly(Boolean.valueOf(attributes.getValue(2)));
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
        //logger.log(Level.INFO, "end element " + current);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length);
        //logger.log(Level.INFO, currentEnum + " s=" + s);
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
