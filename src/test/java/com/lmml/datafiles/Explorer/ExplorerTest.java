package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.fields.FieldSelector;

import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.ComparatorType;
import com.lmml.datafiles.Filter.Filter;
import com.lmml.datafiles.Filter.FilterDefault;
import com.lmml.datafiles.Filter.FilterGroup;

public class ExplorerTest {


    String filePath = "./TestResources/TableFile.txt";
    char separator = ';';
    String[] fields = {"Campo1","Campo2","Campo3","Campo4","Campo5"};
    FilterDefault filterDefault = new FilterDefault();
    int skipLines = 1;
    
    @Test
    public void testGetFullFile(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, fields, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fields);
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, filterDefault);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fields;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor4_3";
        String resultValue = resultDataframe.getString(2,3);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondLine(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, fields, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fields);
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, filterSecondLine);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 1;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fields;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor2_3";
        String resultValue = resultDataframe.getString(2,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndLastLines(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, fields, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fields);
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        Filter filterLastLine = new Filter(1,ComparatorType.CONTIENE,"valor5");
        FilterGroup filterGrop = new FilterGroup(new Filter[]{filterSecondLine, filterLastLine}, false);
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, filterGrop);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 2;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fields;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_5";
        String resultValue = resultDataframe.getString(4,1);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndForthFields(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, fields, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fieldsSelection);
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, filterDefault);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 5;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        Arrays.sort(expectedHeads);
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_4";
        String resultValue = resultDataframe.getString(1,4);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnlySecondAndForthFieldsAndSecondAndLastLines(){
        FileDescriptor fileDescriptor = new FileDescriptor(filePath, separator, null, fields, skipLines);
        String[] fieldsSelection = {"Campo4","Campo2"};
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fieldsSelection);
        Filter filterSecondLine = new Filter(0,ComparatorType.CONTIENE, "2_");
        Filter filterLastLine = new Filter(1,ComparatorType.CONTIENE,"valor5");
        FilterGroup filterGrop = new FilterGroup(new Filter[]{filterSecondLine, filterLastLine}, false);
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, filterGrop);
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 2;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fieldsSelection;
        Arrays.sort(expectedHeads);
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "valor5_4";
        String resultValue = resultDataframe.getString(1,1);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

    @Test
    public void testGetOnNoExistFile(){
        String wrongFilePath = "./RutaInexistente/FicheroInexistente.txt";
        FileDescriptor fileDescriptor = new FileDescriptor(wrongFilePath, separator, null, fields, skipLines);
        FieldsSelector fieldsSelector = new FieldsSelector(fileDescriptor, fields);
        Explorer explorer = new Explorer(fieldsSelector, fileDescriptor, new FilterDefault());
        Dataframe resultDataframe = explorer.get();
        int expectedRecordCount = 0;
        int resultRecordCount = resultDataframe.getRecordCount();
        String[] expectedHeads = fields;
        String[] resultHeads = resultDataframe.getHeads();
        String expectedValue = "";
        String resultValue = resultDataframe.getString(0,0);
        assertEquals(expectedRecordCount, resultRecordCount);
        assertTrue(Arrays.equals(expectedHeads,resultHeads));
        assertEquals(expectedValue, resultValue);
    }//End test

}//End ExplorerTest
