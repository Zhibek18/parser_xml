package kz.kakimzhanova.parser.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SimplePaperHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void startDocument() throws SAXException {
        
    }
}
