package kz.kakimzhanova.parser.parser;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class ParserRunner {
    private static Logger logger = LogManager.getLogger();
    private static String fileName = new File("src/main/resources/papers.xml").getAbsolutePath();
    public static void main(String[] args) {
        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder saxBuilder = factory.createPaperBuilder("sax");
        saxBuilder.buildSetPapers(fileName);
        logger.log(Level.INFO, saxBuilder.getPapers());

        AbstractPaperBuilder staxBuilder = factory.createPaperBuilder("stax");
        staxBuilder.buildSetPapers(fileName);
        logger.log(Level.INFO, staxBuilder.getPapers());

        AbstractPaperBuilder domBuilder = factory.createPaperBuilder("dom");
        domBuilder.buildSetPapers(fileName);
        logger.log(Level.INFO, domBuilder.getPapers());
    }
}
