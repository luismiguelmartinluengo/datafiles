package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FieldsSelectorTest {
    /*Como el objeto presupone siempre que la lista de valores va a encarjar con la longitud de 
     * encabezados especificados por el descriptor de fichero, no proceden pruebas para los casos
     * de que la lista de valores sea superior o inferior a la esperada.
    */

    
    String[] testValues = {"0","1","2","3","4"};

    @Test
    public void testGetMaxIndexOnNoSelectedIndices(){
        int[] selectedIndices = new int[0];
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        int expectedResult = -1;
        int result = fieldsSelector.getMaxIndex();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetMaxIndexResult5(){
        int[] selectedIndices = {0,5,1,4};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        int expectedResult = 5;
        int result = fieldsSelector.getMaxIndex();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetIndices(){
        int[] selectedIndices = {0,5,1,4};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        int[] expectedResult = selectedIndices;
        int[] result = fieldsSelector.getIndices();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetFullFields(){
        int[] selectedIndices = {0,1,2,3,4};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        String[] expectedResult = testValues;
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetSelection(){
        int[] selectedIndices = {0,2,4};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        String[] expectedResult = {"0","2","4"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetAssortedSelection(){
        int[] selectedIndices = {4,2,0};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        String[] expectedResult = {"4","2","0"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetAssortedAndDucplicateSelection(){
        int[] selectedIndices = {4,2,0,0,4,2};
        FieldsSelector fieldsSelector = new FieldsSelector(selectedIndices);
        String[] expectedResult = {"4","2","0","0","4","2"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test




}//End FieldsSelectorTest
