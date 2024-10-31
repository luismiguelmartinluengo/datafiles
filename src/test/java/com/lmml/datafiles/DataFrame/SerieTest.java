package com.lmml.datafiles.DataFrame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SerieTest {

    private Serie serie;
    private String[] initialValues = {"value1", "valu2", "val3"};

    @BeforeEach
    public void setUp() {
        serie = new Serie("TestSerie", initialValues);
    }//End test

    @Test
    public void testAdd() {
        serie.add("value4");
        assertEquals(4, serie.getSize());
        assertEquals("value4", serie.getString(3));
    }//End test

    @Test
    public void testGetString() {
        assertEquals("value1", serie.getString(0));
        assertEquals("valu2", serie.getString(1));
        assertEquals("val3", serie.getString(2));
        assertEquals("", serie.getString(3)); // Index out of bounds
    }//End test

    @Test
    public void testGetSize() {
        assertEquals(3, serie.getSize());
    }//End test

    @Test
    public void testResizeOnEnlargement() {
        serie.resize(2);
        assertEquals(2, serie.getSize());
        assertEquals("", serie.getString(2));
    }//End test

    @Test
    public void testResizeOnReduction(){
        serie.resize(4);
        assertEquals(4, serie.getSize());
        String resultGetString = serie.getString(3);
        assertEquals("", resultGetString);
    }//End test

    @Test
    public void testGetConsoleNameReturnNameWith() {
        assertEquals("TestSerie", serie.getConsoleName().trim());
    }//End test

    @Test
    public void testGetConsoleNameReturnValueWith() {
        String newLargeValue = "nuevo valor largo";
        serie.add(newLargeValue);
        String expectedResult = serie.getName() + " ".repeat(newLargeValue.length() - serie.getName().length());
        assertEquals(expectedResult, serie.getConsoleName());
    }//End test

    @Test
    public void testGetConsoleOutput() {
        assertEquals("value1   ", serie.getConsoleOutput(0));
        assertEquals("valu2    ", serie.getConsoleOutput(1));
        assertEquals("val3     ", serie.getConsoleOutput(2));
    }//End Test

    @Test
    public void testGetConsoleOutputOnNewLargeValue() {
        assertEquals("value1   ", serie.getConsoleOutput(0));
        assertEquals("valu2    ", serie.getConsoleOutput(1));
        serie.add("nuevo valor largo");
        assertEquals("val3             ", serie.getConsoleOutput(2));
        assertEquals("nuevo valor largo", serie.getConsoleOutput(3));
    }//End Test

    @Test
    public void testGetConsoleOutputOnResize() {
        serie.resize(4);
        assertEquals("value1   ", serie.getConsoleOutput(0));
        assertEquals("valu2    ", serie.getConsoleOutput(1));
        assertEquals("val3     ", serie.getConsoleOutput(2));
        assertEquals("         ", serie.getConsoleOutput(3));
    }//End Test

}//End SerieTest
