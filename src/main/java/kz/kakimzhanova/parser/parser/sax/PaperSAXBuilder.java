package kz.kakimzhanova.parser.parser.sax;

import kz.kakimzhanova.parser.entity.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.util.Set;

public class PaperSAXBuilder {
    private static Logger logger = LogManager.getLogger();
    private Set<Paper> papers;
    private PaperHandler handler;
    private XMLReader reader;
    public PaperSAXBuilder(){
        handler = new PaperHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.log(Level.WARN, e);
        }
    }
    public Set<Paper> getPapers(){
        return papers;
    }

    public void buildPaperSet(String filename){
        try{
            reader.parse(filename);
        } catch (SAXException| IOException e) {
            logger.log(Level.WARN, e);
        }
        papers = handler.getPapers();
    }
}
