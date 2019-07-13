package kz.kakimzhanova.parser.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class PaperErrorHandler extends DefaultHandler  {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void warning(SAXParseException e)throws SAXParseException{
        logger.log(Level.WARN, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }
    @Override
    public void error(SAXParseException e) throws SAXParseException{
        logger.log(Level.ERROR, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }
    @Override
    public void fatalError(SAXParseException e) throws SAXParseException{
        logger.log(Level.FATAL, getLineAddress(e) + e);
        throw new SAXParseException(e.getMessage(), null);
    }
    private String getLineAddress(SAXParseException e){
        return e.getLineNumber() + ":" + e.getColumnNumber() + " ";
    }
}
