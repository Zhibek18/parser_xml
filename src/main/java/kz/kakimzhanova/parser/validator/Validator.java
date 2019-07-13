package kz.kakimzhanova.parser.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class Validator {
    private static Logger logger = LogManager.getLogger();
    public boolean validate(String fileName) {
        boolean isValid;
        String schemaName = new File("src/main/resources/papers.xsd").getAbsolutePath();
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try{
            schema = factory.newSchema(new File(schemaName));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            isValid = parse(parser, fileName);

        } catch (SAXException | ParserConfigurationException e) {
            logger.log(Level.WARN, e);
            isValid = false;
        }

        return isValid;
    }
    private boolean parse(SAXParser parser, String fileName){
        try {
            parser.parse(fileName, new PaperErrorHandler());
        }catch (SAXException | IOException e){
            return false;
        }
        return true;
    }
}
