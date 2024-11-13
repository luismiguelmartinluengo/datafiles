package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FieldsSelectorTest {
    /*Como el objeto presupone siempre que la lista de valores va a encarjar con la longitud de 
     * encabezados especificados por el descriptor de fichero, no proceden pruebas para los casos
     * de que la lista de valores sea superior o inferior a la esperada.
    */

    String[] descriptorHeads = {"Col1","Col2","Col3","Col4","Col5"};
    private FileDescriptor fileDescriptor = new FileDescriptor("", ',', '\"', descriptorHeads, 1);
    String[] testValues = {"1","2","3","4","5"};

    @Test
    public void testConstructorFullLine(){
        String[] testSelection = descriptorHeads ;
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = testValues;
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
        
        String[] expectedResultHeads = descriptorHeads;
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorPartialSelection(){
        String[] testSelection ={"Col1","Col3","Col5"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","3","5"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
        
        String[] expectedResultHeads = {"Col1","Col3","Col5"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorPartialSelectionWithRepeatedHeads(){
        String[] testSelection ={"Col1","Col1","Col3","Col3","Col3"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","3"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));
        
        String[] expectedResultHeads = {"Col1","Col3"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorExceededSelection(){
        String[] testSelection ={"Col1","Col2","Col6","Col3","Col4","Col5","Col7"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","2","3","4","5"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));

        String[] expectedResultHeads = {"Col1","Col2","Col3","Col4","Col5"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorExceededSelectionWithRepeatedHeads(){
        String[] testSelection ={"Col1","Col2","Col7","Col4","Col4","Col5","Col7"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","2","4","5"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));

        String[] expectedResultHeads = {"Col1","Col2","Col4","Col5"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorAssortedSelection(){
        String[] testSelection ={"Col3","Col1","Col5","Col2","Col4"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","2","3","4","5"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));

        String[] expectedResultHeads = {"Col1","Col2","Col3","Col4","Col5"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

    @Test
    public void testConstructorPartialAssortedExceededSelectionWithRepeatedHeads(){
        String[] testSelection ={"Col3","Col1","Col1","Col4","Col6","Col7","Col7","Col2","Col4"}; 
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, testSelection);
        String[] expectedResult = {"1","2","3","4"};
        String[] result = fieldsSelector.getSelection(testValues);
        assertTrue(Arrays.equals(expectedResult, result));

        String[] expectedResultHeads = {"Col1","Col2","Col3","Col4"};
        String[] resultHeads = fieldsSelector.getdHeads();
        assertTrue(Arrays.equals(expectedResultHeads, resultHeads));
    }//End test

}//End FieldsSelectorTest
