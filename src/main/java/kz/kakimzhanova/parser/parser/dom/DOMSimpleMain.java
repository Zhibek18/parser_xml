package kz.kakimzhanova.parser.parser.dom;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class DOMSimpleMain {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        PapersDOMBuilder domBuilder = new PapersDOMBuilder();
        domBuilder.buildSetPapers(new File("src/main/resources/papers.xml").getAbsolutePath());
        logger.log(Level.INFO, domBuilder.getPapers());
    }
}
