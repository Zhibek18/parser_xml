package kz.kakimzhanova.parser.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class PaperErrorHandler extends DefaultHandler {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void warning(SAXParseException e){
        logger.log(Level.WARN, getLineAddress(e) + e);
    }
    @Override
    public void error(SAXParseException e){
        logger.log(Level.ERROR, getLineAddress(e) + e);
    }
    @Override
    public void fatalError(SAXParseException e){
        logger.log(Level.FATAL, getLineAddress(e) + e);
    }
    private String getLineAddress(SAXParseException e){
        return e.getLineNumber() + ":" + e.getColumnNumber() + " ";
    }
}
