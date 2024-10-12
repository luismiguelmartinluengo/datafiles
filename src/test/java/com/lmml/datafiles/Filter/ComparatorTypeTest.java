package com.lmml.datafiles.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


public class ComparatorTypeTest {

    @Test
    public void testComparatorValues() {
        ComparatorType[] expectedValues = {ComparatorType.IGUAL, ComparatorType.MAYORQUE, ComparatorType.MAYOROIGUALQUE, ComparatorType.MENORQUE, ComparatorType.MENOROIGUALQUE, ComparatorType.DIFERENTE, ComparatorType.EMPIEZAPOR, ComparatorType.TERMINAPOR, ComparatorType.CONTIENE, ComparatorType.NOEMPIEZAPOR, ComparatorType.NOTERMINAPOR, ComparatorType.NOCONTIENE, ComparatorType.REGEX};
        ComparatorType[] resultValues = ComparatorType.values();
        assertTrue(Arrays.asList(expectedValues).containsAll(Arrays.asList(resultValues)));
        assertTrue(Arrays.asList(resultValues).containsAll(Arrays.asList(expectedValues)));
    }//End test

    @Test
    public void testComparatorMasks() {
        assertEquals(ComparatorType.IGUAL.mask, "==");
        assertEquals(ComparatorType.MAYORQUE.mask, ">");
        assertEquals(ComparatorType.MENORQUE.mask, "<");
        assertEquals(ComparatorType.MAYOROIGUALQUE.mask, ">=");
        assertEquals(ComparatorType.MENOROIGUALQUE.mask, "=<");
        assertEquals(ComparatorType.DIFERENTE.mask, "<>");
        assertEquals(ComparatorType.EMPIEZAPOR.mask, "Empieza por");
        assertEquals(ComparatorType.TERMINAPOR.mask, "Termina por");
        assertEquals(ComparatorType.CONTIENE.mask, "Contiene");
        assertEquals(ComparatorType.NOEMPIEZAPOR.mask, "No empieza por");
        assertEquals(ComparatorType.NOTERMINAPOR.mask, "No termina por");
        assertEquals(ComparatorType.NOCONTIENE.mask, "No contiene");
        assertEquals(ComparatorType.REGEX.mask, "Expresión regular");
    }//End test

}//End ComparatorTypeTest
