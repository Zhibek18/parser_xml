package kz.kakimzhanova.parser.parser.stax;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class StAXSimpleMain {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        PapersStaxBuilder staxBuilder = new PapersStaxBuilder();
        staxBuilder.buildSetPapers(new File("src/main/resources/papers.xml").getAbsolutePath());
        logger.log(Level.INFO, staxBuilder.getPapers());
    }

}
