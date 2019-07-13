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
    public static void main(String[] args) {
        String filename = new File("src/main/resources/papers.xml").getAbsolutePath();
        String schemaname = new File("src/main/resources/papers.xsd").getAbsolutePath();
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        try{
            schema = factory.newSchema(new File(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(filename, new PaperErrorHandler());
            logger.log(Level.INFO, filename + " is valid " + schemaname);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            logger.log(Level.WARN, e);
        }
    }
}
