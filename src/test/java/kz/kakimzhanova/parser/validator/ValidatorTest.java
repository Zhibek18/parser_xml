package kz.kakimzhanova.parser.validator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void validate() {
        boolean expected = true;
        String fileName = new File("src/main/resources/papers.xml").getAbsolutePath();
        Validator validator = new Validator();
        boolean actual = validator.validate(fileName);
        Assert.assertEquals(expected,actual);
    }
}