package kz.kakimzhanova.parser.handler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class PaperErrorHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    public void warning(SAXParseException e){
        logger.log(Level.WARN, getLineAddress(e) + e);
    }
    public void error(SAXParseException e){
        logger.log(Level.ERROR, getLineAddress(e) + e);
    }
    public void fatalError(SAXParseException e){
        logger.log(Level.FATAL, getLineAddress(e) + e);
    }
    public String getLineAddress(SAXParseException e){
        return e.getLineNumber() + ":" + e.getColumnNumber() + " ";
    }
}
