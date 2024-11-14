package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FieldsExtractorTest {

    @Test
    public void testGetSeparatorCommaWithoutDelimiter() {
        String testString = "Hola,que,tal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorCommaDelimiterSingleQuote() {
        String testString = "'Hola','que','tal'";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',','\'');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorCommaDelimiterSingleQuoteInOnlySomeFields() {
        String testString = "'Hola','que',tal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',','\'');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorCommaDelimiterSingleQuoteWithBlanks() {
        String testString = "'','Hola','que','','tal',''";
        String[] expectedResult = {"","Hola","que","","tal",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',','\'');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorCommaDelimiterSingleQuoteInOnlySomeFieldsAndWithBlanks() {
        String testString = ",'Hola',,'que','','tal',";
        String[] expectedResult = {"","Hola","","que","","tal",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',','\'');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorCommaDelimiterSingleQuoteInOnlySomeFieldsAndWithBlanksAndSeparatorIntoDelimitedFields() {
        String testString = ",'Hola',,'que','','tal,estas',";
        String[] expectedResult = {"","Hola","","que","","tal,estas",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',','\'');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithoutDelimiter() {
        String testString = "Hola;que;tal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(';');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorTabWithoutDelimiter() {
        String testString = "Hola\tque\ttal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('\t');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorPipeWithoutDelimiter() {
        String testString = "Hola|que|tal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('|');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorPipeWithoutDelimiterWithBlankFields() {
        String testString = "|Hola|que||tal|";
        String[] expectedResult = {"","Hola","que","","tal",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('|');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetComplexSeparatorTabDelimiterDoubleQuote() {
        String testString = "\t\"Hola\"\tque\t\t\"t\tal\"\t";
        String[] expectedResult = {"","Hola","que","","t\tal",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('\t', '\"');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetComplexSeparatorPipeDelimiterPad() {
        String testString = "|#Hola#|que||#t|al#|";
        String[] expectedResult = {"","Hola","que","","t|al",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('|', '#');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetComplexSeparatorBackSlashDelimiterForwardSlash() {
        String testString = "\\/Hola/\\que\\\\/t\\al/\\";
        String[] expectedResult = {"","Hola","que","","t\\al",""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor('\\', '/');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetBlankString() {
        String testString = "";
        String[] expectedResult = {""};
        FieldsExtractor fieldsExtractor = new FieldsExtractor(',', '\"');
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

}//End LogTest