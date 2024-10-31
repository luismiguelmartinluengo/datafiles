package com.lmml.datafiles.DataFrame;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataframeTest {

    private Dataframe dataframe;
    private String[] names = {"Serie1", "Serie2"};
    private String[][] values = {{"value1.1", "value1.2"}, {"value2.1", "value2.2"}};

    @BeforeEach
    public void setUp() {
        dataframe = new Dataframe(names, values);
    }//End setUP

    @Test
    public void testGetRecordCount() {
        int expectedRecordCount = 2;
        int resultRecordCount = dataframe.getRecordCount();
        assertEquals(expectedRecordCount, resultRecordCount);
    }//End test

    @Test
    public void testGetStringReturnValue() {
        String expectedResult = "value2.2";
        String result = dataframe.getString(1, 1);
        assertEquals(expectedResult, result);
    }//End test
    
    @Test
    public void testGetStringOnRecordOutOfIndex() {
        String expectedResult = "";
        String result = dataframe.getString(1, 5);
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetStringOnSerieOutOfIndex() {
        String expectedResult = "";
        String result = dataframe.getString(5, 1);
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testAddRecord() {
        String[] newRecord = {"value3.1","value3.2"};
        dataframe.addRecord(newRecord);
        String[] result = {dataframe.getString(0, 2), dataframe.getString(1, 2)};
        assertTrue(Arrays.equals(newRecord, result));
    }//End test
    
    @Test
    public void testAddRecordWithMissingValues() {
        String[] newRecord = {"value3.1"};
        dataframe.addRecord(newRecord);
        String[] expectedResult = {"value3.1",""};
        String[] result = {dataframe.getString(0,2), dataframe.getString(1, 2)};
        assertTrue(Arrays.equals(expectedResult, result));
    }//End Test
    
    @Test
    public void testWriteConsole() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        System.setOut(newOut);
        dataframe.writeConsole();
        System.setOut(originalOut);
        String consoleOut = baos.toString();
        String[] consoleOuts = consoleOut.split(System.lineSeparator());
        String expectedResultMask = "value1.2.*value2.2.*";
        String result = consoleOuts[consoleOuts.length - 1];
        assertTrue(result.matches(expectedResultMask));
    }//End test

    @Test
    public void testConstructorInSetup() {
        assertTrue(dataframe.getRecordCount() == 2);
        assertEquals(dataframe.getString(0, 0), "value1.1");
        assertTrue(dataframe.getString(1,1).equals("value2.2"));
        assertTrue(dataframe.getString(2, 0).equals(""));//No hay serie con index 2
    }//End test

    @Test
    public void testConstructorOnMismatchesSeries() {
        String[] otherNames = {"Serie1", "Serie2", "Serie3"};
        String[][] otherValues = {{"value1.1"}, {"value2.1", "value2.2"}};
        Dataframe otherDataframe = new Dataframe(otherNames, otherValues); //Aquí tiene que ajustar las series
        String[] newRecord = {"value1.3", "value2.3", "value3.3"}; //registro añadido después de ajustar las series
        otherDataframe.addRecord(newRecord);
        assertTrue(otherDataframe.getRecordCount() == 3);
        assertTrue(otherDataframe.getString(0, 0).equals("value1.1"));
        assertTrue(otherDataframe.getString(0, 1).equals("")); //valor no provisto al constructor
        assertTrue(otherDataframe.getString(0, 2).equals("value1.3"));//valor añadido después del ajuste de series
        assertTrue(otherDataframe.getString(1,0).equals("value2.1"));
        assertTrue(otherDataframe.getString(1, 1).equals("value2.2"));
        assertTrue(otherDataframe.getString(1, 2).equals("value2.3"));//valor añadido después del ajuste de series
        assertTrue(otherDataframe.getString(2, 2).equals(""));//No hay serie con index 2 porque en el constructor no se pasaron datos para ese nombre de serie
    }//End test

/*
    @Test
    public void testAdjustSeries() {
        String[] newRecord = {"value5.1", "value5.2", "extraValue"};
        dataframe.addRecord(newRecord); // This won't add extraValue
        dataframe.adjustSeries();
        assertEquals(3, dataframe.getRecordCount());
        assertNull(dataframe.series.get(2)); // extraValue should not be added as we only have 2 series
    }

     */

}//End DataframeTest
