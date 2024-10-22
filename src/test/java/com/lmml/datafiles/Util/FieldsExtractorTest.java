package com.lmml.datafiles.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LogTest {

    /*Refactorizar FildsExtractor para quitar el if que evalue si tiene que 
    ejecutear con o sin delimitador. Usar clase privada interna para 
    extracción con delimitador y sin delimitador construyéndoa conforme corresponda
    */

    String[] testFields = {"Hola", "que", "tal"};

    private String getTestString(String _separator, String _delimiter){
        ArrayList<String> fields = new ArrayList<String>(Arrays.asList(testFields));
        if (_delimiter != null){
            for(String field: testFields){
                fields.add(String.Format(%s%s%s, _delimiter, field, _delimiter));
            }//End for
        }//End if
        return String.join("_separator", fields);
    }//End getTestString 

    @Test
    public void testGetSeparatorPipeWithoutDelimiter() {
        String testString = getTestString("|", null, "Hola", "que", "tal");
        FieldsExtractor fieldsExtractor = new FieldsExtractor("|");
        String[] results = fieldExtractor.get(testString);
        assertTrue()
    }//End test

    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuote() {
        
    }//End test

    @Test
    public void testGetSeparatorPipeWithDelimiterDoubleQuoteIncludingPipeInFields() {
        
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuote() {
        
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuoteIncludingTabinFields() {
        
    }//End test

    @Test
    public void testGetSeparatorTabWithDelimiterSingleQuoteIncludingTabinFields() {
        
    }//End test

    @Test
    public void testGetSeparatorDoublePadWithoutDelimiter() {
        
    }//End test

    @Test
    public void testGetSeparatorDoublePadWithDelimiterSingleQuote() {
        
    }//End test

    @Test
    public void testGetSeparatorDoublePadWithDelimiterSingleQuoteIncludingDoublePadinFields() {
        
    }//End test



}//End LogTest