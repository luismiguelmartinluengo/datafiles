package com.lmml.datafiles.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ComparatorTest {

    @Test
    public void comparatorEqualsPassEqualsReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.IGUAL);
        String valueA = "Valor";
        String valueB = "Valor";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test
    
    @Test
    public void comparatorEqualsPassNotEqualsReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.IGUAL);
        String valueA = "Valor";
        String valueB = "ValorOtro";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test
    
    @Test
    public void comparatorGreaterPassGraterAReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MAYORQUE);
        String valueA = "b";
        String valueB = "a";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorGreaterPassEqualsReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MAYORQUE);
        String valueA = "a";
        String valueB = "a";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorGreaterPassLessAReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MAYORQUE);
        String valueA = "a";
        String valueB = "b";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorGreaterEquealPassGreaterAReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MAYOROIGUALQUE);
        String valueA = "b";
        String valueB = "a";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorGreaterEquealPassEqualsReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MAYOROIGUALQUE);
        String valueA = "a";
        String valueB = "a";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorGreaterEquealPassLessAReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MAYOROIGUALQUE);
        String valueA = "a";
        String valueB = "b";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessPassGreaterAReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MENORQUE);
        String valueA = "b";
        String valueB = "a";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessPassEqualsReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MENORQUE);
        String valueA = "a";
        String valueB = "a";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessPassLessAReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MENORQUE);
        String valueA = "a";
        String valueB = "b";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessEqualPassGreaterAReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.MENOROIGUALQUE);
        String valueA = "b";
        String valueB = "a";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessEqualPassEqualsReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MENOROIGUALQUE);
        String valueA = "a";
        String valueB = "a";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorLessEqualPassLessAReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.MENOROIGUALQUE);
        String valueA = "a";
        String valueB = "b";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorStartWithPass_abc_ab_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.EMPIEZAPOR);
        String valueA = "abc";
        String valueB = "ab";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorStartWithPass_abc_de_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.EMPIEZAPOR);
        String valueA = "abc";
        String valueB = "de";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorEndsWithPass_abc_bc_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.TERMINAPOR);
        String valueA = "abc";
        String valueB = "bc";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorEndsWithPass_abc_de_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.TERMINAPOR);
        String valueA = "abc";
        String valueB = "de";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorContainsPass_abcd_bc_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.CONTIENE);
        String valueA = "abcd";
        String valueB = "bc";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorContainsPass_abcd_ef_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.CONTIENE);
        String valueA = "abcd";
        String valueB = "bc";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotStartWithPass_abc_ab_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.NOEMPIEZAPOR);
        String valueA = "abc";
        String valueB = "ab";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotStartWithPass_abc_de_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.NOEMPIEZAPOR);
        String valueA = "abc";
        String valueB = "de";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotEndsWithPass_abc_bc_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.NOTERMINAPOR);
        String valueA = "abc";
        String valueB = "bc";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotEndsWithPass_abc_de_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.NOTERMINAPOR);
        String valueA = "abc";
        String valueB = "de";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotContainsPass_abcd_bc_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.NOCONTIENE);
        String valueA = "abcd";
        String valueB = "bc";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorNotContainsPass_abcd_ef_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.NOCONTIENE);
        String valueA = "abcd";
        String valueB = "ef";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorRegexReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.REGEX);
        String valueA = "abcde";
        String valueB = "[a-z]{5}";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorRegexReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.REGEX);
        String valueA = "12345";
        String valueB = "[a-z]{5}";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorDifferentPass_abc_abc_ReturnFalse() {
        Comparator comparator = new Comparator(ComparatorType.DIFERENTE);
        String valueA = "abc";
        String valueB = "abc";
        boolean expectedResult = false;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

    @Test
    public void comparatorDifferentPass_abc_def_ReturnTrue() {
        Comparator comparator = new Comparator(ComparatorType.DIFERENTE);
        String valueA = "abc";
        String valueB = "def";
        boolean expectedResult = true;
        boolean result = comparator.get(valueA, valueB);
        assertEquals(result, expectedResult);
    }//End test

}//End ComparatorTest
