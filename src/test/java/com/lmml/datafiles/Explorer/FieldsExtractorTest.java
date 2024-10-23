package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FieldsExtractorTest {

    /*Refactorizar FildsExtractor para quitar el if que evalue si tiene que 
    ejecutear con o sin delimitador. Usar clase privada interna para 
    extracción con delimitador y sin delimitador construyéndoa conforme corresponda
    */


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

    /*hay que probrar la extracción se separador y delimitador para cadenas que
     * no incluyan delimitadores en todos los campos, otra prueba en una cadena que 
     * deje algún campo en blanco, incluyendo el primero y el último
     * y para terminar una prueba con una cadena que deje algun campo sin delimitadores y
     * que también tenga campos en blanco
     */

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

    /*
    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuote() {
        String testString = "\"Hola\"|\"que\"|\"tal\"";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor("|","\"");
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuoteIncludingPipeInFields() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorTabWithoutDelimiter() {
        String testString = "Hola\tque\ttal";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor("\t");
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuote() {
        String testString = "'Hola'\t'que'\t'tal'";
        String[] expectedResult = {"Hola","que","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor("\t","'");
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuoteIncludingTabinFields() {
        String testString = "'Hola'\t'q\tue'\t'tal'";
        String[] expectedResult = {"Hola","q\tue","tal"};
        FieldsExtractor fieldsExtractor = new FieldsExtractor("\t","'");
        String[] result = fieldsExtractor.get(testString);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlyFirstField() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlySecondField() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlyLastField() {
        assertTrue(false);
    }//End test
    */

    //¿lo tengo preparado para separador de varios caracteres?

}//End LogTest