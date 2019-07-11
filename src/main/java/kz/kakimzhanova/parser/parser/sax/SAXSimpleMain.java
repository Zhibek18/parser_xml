package kz.kakimzhanova.parser.parser.sax;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class SAXSimpleMain {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        PaperSAXBuilder saxBuilder = new PaperSAXBuilder();
        saxBuilder.buildPaperSet(new File("src/main/resources/papers.xml").getAbsolutePath());
        logger.log(Level.INFO, saxBuilder.getPapers());
    }
}
