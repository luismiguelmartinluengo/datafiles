package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class FieldsExtractorTest {

    /*Refactorizar FildsExtractor para quitar el if que evalue si tiene que 
    ejecutear con o sin delimitador. Usar clase privada interna para 
    extracción con delimitador y sin delimitador construyéndoa conforme corresponda
    */


    @Test
    public void testGetSeparatorPipeWithoutDelimiter() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuote() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuoteIncludingPipeInFields() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorTabWithoutDelimiter() {
        assertTrue(false);
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuote() {
        
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuoteIncludingTabinFields() {
        
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlyFirstField() {
        
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlySecondField() {
        
    }//End test

    @Test
    public void testGetSeparatorSemicolonWithDelimiterOnlyLastField() {
        
    }//End test

    //¿lo tengo preparado para separador de varios caracteres?

}//End LogTest