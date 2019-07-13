package kz.kakimzhanova.parser.parser.stax;

import kz.kakimzhanova.parser.entity.Paper;
import kz.kakimzhanova.parser.parser.AbstractPaperBuilder;
import kz.kakimzhanova.parser.parser.PaperBuilderFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PapersStAXBuilderTest {
    private static final String fileName = new File("src/main/resources/papers.xml").getAbsolutePath();
    @Test
    public void buildSetPapers() {
        List<Paper> expected = new ArrayList<>();
        expected.add(new Paper("Times","newspaper", true, false, 50, false, true));
        expected.add( new Paper("Cosmopolitan","magazine", true, true,60,true, true));
        expected.add( new Paper("Space", "booklet", false, true, 7, true, false));
        expected.add( new Paper("Guardian", "newspaper", false, false, 30, false, true));
        expected.add( new Paper("Music", "booklet", false, true, 10, false, false));
        expected.add( new Paper("Science", "magazine", false, true, 40, true, true));
        expected.add( new Paper("News", "newspaper", false, false, 44, false, true));
        expected.add( new Paper("Dance", "booklet", false, true, 11, false, false));
        expected.add( new Paper("Hair", "booklet", false, true, 3, true, false));
        expected.add( new Paper("Glass", "booklet", false, true, 10, false, false));
        expected.add( new Paper("Techno", "magazine", false, true,50,false, true));
        expected.add( new Paper("Nature", "magazine", false, true, 54, true, true));
        expected.add( new Paper("Paint", "magazine", false, true, 40, true, false));
        expected.add( new Paper("Cream", "booklet", false, true, 16, false, true));
        expected.add( new Paper("Zoo", "booklet", false, false, 10, false, false));
        expected.add( new Paper("Guitar", "booklet", false, true, 4, true, false));

        PaperBuilderFactory factory = new PaperBuilderFactory();
        AbstractPaperBuilder domBuilder = factory.createPaperBuilder("stax");
        domBuilder.buildSetPapers(fileName);
        List<Paper> actual = domBuilder.getPapers();

        actual.sort((Paper firstPaper, Paper secondPaper)->firstPaper.getTitle().compareTo(secondPaper.getTitle()));
        expected.sort((Paper firstPaper, Paper secondPaper)->firstPaper.getTitle().compareTo(secondPaper.getTitle()));
        Assert.assertEquals(expected.toString().trim(), actual.toString().trim());
    }
}