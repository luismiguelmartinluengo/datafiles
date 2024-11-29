package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.ComparatorType;
import com.lmml.datafiles.Filter.Filter;
import com.lmml.datafiles.Filter.FilterGroup;

public class FileExplorerTest {


    String filePath = "./TestResources/TableFile.txt";
    char separator = ';';
    String[] heads = {"Campo1","Campo2","Campo3","Campo4","Campo5"};
    int skipLines = 1;
    
    @Test
    public void testGetFullFile(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor4_3";
        String resultValue = resultDataframe.getString(2,3);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetFullFileWithAssortedAndDuplicatesAndLostHeads(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2","Campo1","Campo1"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(fieldsSelection));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor4_1";
        String resultValue = resultDataframe.getString(3,3);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondLine(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor, filterSecondLine);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 1;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor2_3";
        String resultValue = resultDataframe.getString(2,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndLastLines(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        Filter filterLastLine = new Filter(1,ComparatorType.CONTIENE,"valor5");
        FilterGroup filterGrop = new FilterGroup(new Filter[]{filterSecondLine, filterLastLine}, FilterGroup.OR_FILTER_GROUP);
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor, filterGrop);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 2;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_5";
        String resultValue = resultDataframe.getString(4,1);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndForthFields(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(fieldsSelection));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_2";
        String resultValue = resultDataframe.getString(1,4);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndForthFieldsWithDucplicateFields(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2","Campo2"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(fieldsSelection));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_2";
        String resultValue = resultDataframe.getString(2,4);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndForthFieldsAndSecondAndLastLines(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(fieldsSelection));
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        Filter filterLastLine = new Filter(1,ComparatorType.CONTIENE,"valor5");
        FilterGroup filterGrop = new FilterGroup(new Filter[]{filterSecondLine, filterLastLine}, FilterGroup.OR_FILTER_GROUP);
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor, filterGrop);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 2;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_2";
        String resultValue = resultDataframe.getString(1,1);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetValidateOnFineDescriptor(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        boolean expectedResult = true;
        boolean result = explorer.getValidation();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetValidateOnWrongDescriptor(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, separator, heads, skipLines); //fuerzo error de validación con delimitador y separador iguales
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        boolean expectedResult = false;
        boolean result = explorer.getValidation();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetValidateOnNoFileExist(){
        String wrongFilePath = "./RutaInexistente/FicheroInexistente.txt";
        FileDescriptor fileDescriptor = new FileDescriptor(wrongFilePath, separator, separator, heads, skipLines); //fuerzo error de validación con delimitador y separador iguales
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        boolean expectedResult = false;
        boolean result = explorer.getValidation();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetValidateOnWrongFilters(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        Filter filterOk = new Filter(0,ComparatorType.CONTIENE, "2_");
        Filter filterKO = new Filter(9,ComparatorType.CONTIENE,"valor5");
        FilterGroup filterGrop = new FilterGroup(new Filter[]{filterOk, filterKO}, FilterGroup.OR_FILTER_GROUP);
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor, filterGrop);
        boolean expectedResult = false;
        boolean result = explorer.getValidation();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetValidateOnWrongFieldsSelection(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(new int[]{0,2,5,7});
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        boolean expectedResult = false;
        boolean result = explorer.getValidation();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetOnNoExistFile(){
        String wrongFilePath = "./RutaInexistente/FicheroInexistente.txt";
        FileDescriptor fileDescriptor = new FileDescriptor(wrongFilePath, separator, null, heads, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 0;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "";
        String resultValue = resultDataframe.getString(0,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnWrongDescriptor(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, separator, heads, skipLines);//Descriptor incorrecto con mismo caracter para separador y delimitador
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 0;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "";
        String resultValue = resultDataframe.getString(0,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnWrongFilters(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);//Descriptor incorrecto con mismo caracter para separador y delimitador
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor.getHeadsIndex(heads));
        Filter filterKO = new Filter(9,ComparatorType.CONTIENE,"valor5");
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor, filterKO);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 0;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = heads;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "";
        String resultValue = resultDataframe.getString(0,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnWrongSelector(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, heads, skipLines);//Descriptor incorrecto con mismo caracter para separador y delimitador
        FieldsSelector fieldsSelector = new FieldsSelector(new int[]{15,23});
        FileExplorer explorer = new FileExplorer(fieldsSelector, fileDescriptor);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 0;
        int resultRecordCount = resultDataframe.getRecordCount();
        int expectedSeriesCount = 0;
        int resultSereiesCount = resultDataframe.getSeriesCount();
        String expectedValue = "";
        String resultValue = resultDataframe.getString(0,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertEquals(expectedSeriesCount, resultSereiesCount);
        assertEquals(expectedValue, resultValue);
    }//End test

}//End ExplorerTest
