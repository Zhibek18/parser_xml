package kz.kakimzhanova.parser.parser.stax;

import kz.kakimzhanova.parser.entity.Paper;
import kz.kakimzhanova.parser.parser.AbstractPaperBuilder;
import kz.kakimzhanova.parser.parser.PaperEnum;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class PapersStAXBuilder extends AbstractPaperBuilder {
    private static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    public PapersStAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Paper> buildSetPapers(String fileName){
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try{
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER){
                        Paper paper = buildPaper(reader);

                        papers.add(paper);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            logger.log(Level.WARN, e);
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();

                }
            } catch (IOException e) {
                logger.log(Level.WARN, e);
            }
        }
        return papers;
    }

    private Paper buildPaper(XMLStreamReader reader) throws XMLStreamException {
        Paper paper = new Paper();
        paper.setTitle(reader.getAttributeValue(null, PaperEnum.TITLE.getValue()));
        paper.setType(reader.getAttributeValue(null, PaperEnum.TYPE.getValue()));
        String monthly = reader.getAttributeValue(null, PaperEnum.MONTHLY.getValue());
        if (monthly != null) {
            paper.setMonthly(Boolean.valueOf(monthly));
        }
        String name;
        while (reader.hasNext()){
            int type =  reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase())== PaperEnum.CHARS){
                        paper.setChars(getXMLChars(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER){
                        return paper;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Paper");
    }

    private Paper.Chars getXMLChars(XMLStreamReader reader) throws XMLStreamException {
        Paper.Chars chars = new Paper().new Chars();
        int type;
        String name;

        while (reader.hasNext()){
            type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PaperEnum.valueOf(name.toUpperCase())){
                        case COLOR:
                            chars.setColor(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case VOLUME:
                            chars.setVolume(Integer.parseInt(getXMLText(reader)));
                            break;
                        case GLOSSY:
                            chars.setGlossy(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case SUBSCRIPTION:
                            chars.setSubscription(Boolean.valueOf(getXMLText(reader)));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.CHARS){
                        return chars;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Chars");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()){
            int type = reader.next();
            if (type != XMLStreamConstants.END_ELEMENT) {
                text = reader.getText();
            }
        }
        return text;
    }
}
