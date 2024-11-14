package com.lmml.datafiles.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FilterDefaultTest {

    @Test
    public void testGetEvaluation(){
        FilterDefault filterDefault = new FilterDefault();
        boolean expectedResult = true;
        boolean result = filterDefault.getEvaluation(new String[0]);
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetMaxIndexField(){
        FilterDefault filterDefault = new FilterDefault();
        int expectedResult = -1;
        int result = filterDefault.getMaxIndexField();
        assertEquals(expectedResult, result);
    }//End test

}//End FilterDefaultTest
