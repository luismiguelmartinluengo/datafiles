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
    private Dataframe emptyDataframe;
    private Dataframe seriesEmptyDataframe;

    @BeforeEach
    public void setUp() {
        dataframe = new Dataframe(names, values);
        emptyDataframe = new Dataframe(names);
        seriesEmptyDataframe = new Dataframe(new String[0]);
    }//End setUP

    @Test
    public void testGetRecordCount() {
        int expectedRecordCount = 2;
        int resultRecordCount = dataframe.getRecordCount();
        assertEquals(expectedRecordCount, resultRecordCount);
    }//End test

    @Test
    public void testGetRecordCountOnEmptyDataframe() {
        int expectedRecordCount = 0;
        int resultRecordCount = emptyDataframe.getRecordCount();
        assertEquals(expectedRecordCount, resultRecordCount);
    }//End test

    @Test
    public void testGetSeriesCount(){
        int expectedSeriesCount = 2;
        int resultSeriesCount = dataframe.getSeriesCount();
        assertEquals(expectedSeriesCount, resultSeriesCount);
    }//End test

    @Test
    public void testGetSeriesCountOnSeriesEmptyDataframe(){
        int expectedSeriesCount = 0;
        int resultSeriesCount = seriesEmptyDataframe.getSeriesCount();
        assertEquals(expectedSeriesCount, resultSeriesCount);
    }//End test

    @Test
    public void testGetStringReturnValue() {
        String expectedResult = "value2.2";
        String result = dataframe.getString(1, 1);
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetStringReturnValueOnEmptyDataframe() {
        String expectedResult = "";
        String result = emptyDataframe.getString(1, 1);
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
    public void testAddRecordOnEmptyDataframe() {
        String[] newRecord = {"value3.1","value3.2"};
        emptyDataframe.addRecord(newRecord);
        String[] result = {emptyDataframe.getString(0, 0), emptyDataframe.getString(1, 0)};
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
    public void testAddRecordWithMissingValuesOnEmptyDataframe() {
        String[] newRecord = {"value3.1"};
        emptyDataframe.addRecord(newRecord);
        String[] expectedResult = {"value3.1",""};
        String[] result = {emptyDataframe.getString(0,0), emptyDataframe.getString(1, 0)};
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
        String expectedResultMaskFirstLine = "Serie1.*Serie2.*";
        String expectedResultMaskLastLine = "value1.2.*value2.2.*";
        String resultFirstLine = consoleOuts[0];
        String resultLasttLine = consoleOuts[consoleOuts.length - 1];
        assertTrue(resultFirstLine.matches(expectedResultMaskFirstLine));
        assertTrue(resultLasttLine.matches(expectedResultMaskLastLine));
    }//End test

    @Test
    public void testWriteConsoleOnEmptyDataframe() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);
        System.setOut(newOut);
        emptyDataframe.writeConsole();
        System.setOut(originalOut);
        String consoleOut = baos.toString();
        String[] consoleOuts = consoleOut.split(System.lineSeparator());
        String expectedResultMaskFirstLine = "Serie1.*Serie2.*";
        String expectedResultMaskLastLine = "Serie1.*Serie2.*";
        String resultFirstLine = consoleOuts[0];
        String resultLasttLine = consoleOuts[consoleOuts.length - 1];
        assertTrue(resultFirstLine.matches(expectedResultMaskFirstLine));
        assertTrue(resultLasttLine.matches(expectedResultMaskLastLine));
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

    @Test
    public void testGetHeads(){
        String[] expectedResult = {"Serie1", "Serie2"};
        String[] result = dataframe.getHeads();
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test

    @Test
    public void testGetHeadsOnSeriesEmptyDataFrame(){
        String[] expectedResult = new String[0];
        String[] result = seriesEmptyDataframe.getHeads();
        assertTrue(Arrays.equals(expectedResult, result));
    }//End test
    
    @Test
    public void testGetStringAllValues(){
        String[][] expectedResult = {{"value1.1", "value1.2"}, {"value2.1", "value2.2"}};
        String[][] result = dataframe.getString();
        assertTrue(Arrays.equals(expectedResult[0], result[0]));
        assertTrue(Arrays.equals(expectedResult[1], result[1]));
    }//End test



}//End DataframeTest
