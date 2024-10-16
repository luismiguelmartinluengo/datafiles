package com.lmml.datafiles.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class FilterTest {

    private String[] values = {"Hola","que","tal","estás"};
    
    @Test
    public void testGetEvaluationTrue() {
        Filter filter = new Filter(1, ComparatorType.IGUAL, "que");
        boolean result = filter.getEvaluation(values);
        assertTrue(result);
    }//End test

    @Test
    public void testGetEvaluationFalse() {
        Filter filter = new Filter(0, ComparatorType.CONTIENE, "j");
        boolean result = filter.getEvaluation(values);
        assertFalse(result);
    }//End test

    @Test
    public void testGetEvaluationIndexOutOfBoundsReturnFalse() {
        Filter filter = new Filter(10, ComparatorType.EMPIEZAPOR, "x");
        boolean result = filter.getEvaluation(values);
        assertFalse(result);
    }//End test

    @Test
    public void testGetMaxIndexFieldReturn2() {
        Filter filter = new Filter(2, ComparatorType.DIFERENTE, "x");
        int result = filter.getMaxIndexField();
        assertEquals(2, result);
    }//End test

}//E nd FilterTest
