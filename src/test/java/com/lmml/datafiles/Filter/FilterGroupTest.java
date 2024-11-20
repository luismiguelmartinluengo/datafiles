package com.lmml.datafiles.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FilterGroupTest {

    String[] objetive = "Hola que tal estas".split(" ");
    
    @Test
    public void constructorOneFilterAndReturn3() {
        Filter filter = new Filter(3,ComparatorType.IGUAL, "Hola");
        FilterGroup filterGroup = new FilterGroup(filter, FilterGroup.AND_FILTER_GROUP);
        int expectedResult = filterGroup.getMaxIndexField();
        assertEquals(expectedResult, 3);
    }//End test

    @Test
    public void ConstuctorTwoFiltersOrReturn5() {
        Filter filter1a = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2a = new Filter(1,ComparatorType.IGUAL,"que");
        Filter filter2b  = new Filter(5,ComparatorType.IGUAL,"tal");
        Filter[] filters2 = {filter2a, filter2b};
        FilterGroup filterGroup1b = new FilterGroup(filters2, FilterGroup.AND_FILTER_GROUP);
        IFilter[] ifilters1 = {filter1a, filterGroup1b};
        FilterGroup filterGroup0 = new FilterGroup(ifilters1, FilterGroup.OR_FILTER_GROUP);
        int expectedResult = filterGroup0.getMaxIndexField();
        assertEquals(expectedResult, 5);
    }//End test
    
    @Test
    public void addOneFilterReturn2() {
        Filter filterConstructor = new Filter(1,ComparatorType.IGUAL, "Hola");
        Filter filterToAdd = new Filter(2,ComparatorType.IGUAL, "Hola");
        FilterGroup filterGroup = new FilterGroup(filterConstructor, FilterGroup.AND_FILTER_GROUP);
        filterGroup.add(filterToAdd);
        int expectedResult = filterGroup.getMaxIndexField();
        assertEquals(expectedResult, 2);
    }//End test

    @Test
    public void addOneGroupFilterReturn3() {
        Filter filterConstructor = new Filter(1,ComparatorType.IGUAL, "Hola");
        Filter filter1ToAdd = new Filter(2,ComparatorType.IGUAL, "Hola");
        Filter filter2ToAdd = new Filter(3,ComparatorType.IGUAL, "Hola");
        Filter[] filtersToAdd = {filter1ToAdd, filter2ToAdd};
        FilterGroup filterGroupToAdd = new FilterGroup(filtersToAdd, FilterGroup.AND_FILTER_GROUP);
        FilterGroup filterGroup = new FilterGroup(filterConstructor, FilterGroup.AND_FILTER_GROUP);
        filterGroup.add(filterGroupToAdd);
        int expectedResult = filterGroup.getMaxIndexField();
        assertEquals(expectedResult, 3);
    }//End test
    
    @Test
    public void addAllTwoFiltersReturn3() {
        Filter filterConstructor = new Filter(1,ComparatorType.IGUAL, "Hola");
        Filter filter1ToAdd = new Filter(2,ComparatorType.IGUAL, "Hola");
        Filter filter2ToAdd = new Filter(3,ComparatorType.IGUAL, "Hola");
        Filter[] filtersToAdd = {filter1ToAdd, filter2ToAdd};
        FilterGroup filterGroup = new FilterGroup(filterConstructor, FilterGroup.AND_FILTER_GROUP);
        filterGroup.addAll(filtersToAdd);
        int expectedResult = filterGroup.getMaxIndexField();
        assertEquals(expectedResult, 3);
    }//End test

    @Test
    public void addAllOneFilterPlusOneGroupFilterReturn4() {
        Filter filterConstructor = new Filter(1,ComparatorType.IGUAL, "Hola");
        Filter filter1ToAdd = new Filter(2,ComparatorType.IGUAL, "Hola");
        Filter filter2ToAdd = new Filter(3,ComparatorType.IGUAL, "Hola");
        Filter[] filtersToAdd = {filter1ToAdd, filter2ToAdd};
        FilterGroup filterGroup = new FilterGroup(filterConstructor, FilterGroup.AND_FILTER_GROUP);
        filterGroup.addAll(filtersToAdd);
        int expectedResult = filterGroup.getMaxIndexField();
        assertEquals(expectedResult, 3);
    }//End test
   
    @Test
    public void getEvaluatonOneFilterAndReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        FilterGroup filterGroup = new FilterGroup(filter1, FilterGroup.AND_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonOneFilterAndReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup = new FilterGroup(filter1, FilterGroup.AND_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test

    @Test
    public void getEvaluatonOneFilterOrReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        FilterGroup filterGroup = new FilterGroup(filter1, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonOneFilterOrReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup = new FilterGroup(filter1, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test

    @Test
    public void getEvaluatonTwoFilterAndReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "que");
        FilterGroup filterGroup = new FilterGroup(filter1, FilterGroup.AND_FILTER_GROUP);
        filterGroup.add(filter2);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonTwoFilterAndReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup = new FilterGroup(new Filter[]{filter1,filter2}, FilterGroup.AND_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test

    @Test
    public void getEvaluatonTwoFilterOrReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup = new FilterGroup(new Filter[]{filter1,filter2}, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonTwoFilterOrReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "xxx");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup = new FilterGroup(new Filter[]{filter1,filter2}, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test

    @Test
    public void getEvaluatonOneFilterPlusOneGroupFilterAndReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "que");
        Filter filter3 = new Filter(2,ComparatorType.IGUAL, "tal");
        FilterGroup filterGroup1 = new FilterGroup(new Filter[]{filter2, filter3}, FilterGroup.AND_FILTER_GROUP);
        FilterGroup filterGroup0 = new FilterGroup(new IFilter[]{filter1,filterGroup1}, FilterGroup.AND_FILTER_GROUP);
        boolean expectedResult = filterGroup0.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonOneFilterPlusOneGroupFilterAndReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "Hola");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "que");
        Filter filter3 = new Filter(2,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup1 = new FilterGroup(new Filter[]{filter2, filter3}, FilterGroup.AND_FILTER_GROUP);
        FilterGroup filterGroup0 = new FilterGroup(new IFilter[]{filter1,filterGroup1}, FilterGroup.AND_FILTER_GROUP);
        boolean expectedResult = filterGroup0.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test

    @Test
    public void getEvaluatonOneFilterPlusOneGroupFilterOrReturnTrue() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "xxx");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "que");
        Filter filter3 = new Filter(2,ComparatorType.IGUAL, "tal");
        FilterGroup filterGroup1 = new FilterGroup(new Filter[]{filter2, filter3}, FilterGroup.AND_FILTER_GROUP);
        FilterGroup filterGroup0 = new FilterGroup(new IFilter[]{filter1,filterGroup1}, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup0.getEvaluation(objetive);
        assertEquals(expectedResult, true);
    }//End test

    @Test
    public void getEvaluatonOneFilterPlusOneGroupFilterOrReturnFalse() {
        Filter filter1 = new Filter(0,ComparatorType.IGUAL, "xxx");
        Filter filter2 = new Filter(1,ComparatorType.IGUAL, "que");
        Filter filter3 = new Filter(2,ComparatorType.IGUAL, "xxx");
        FilterGroup filterGroup1 = new FilterGroup(new Filter[]{filter2, filter3}, FilterGroup.AND_FILTER_GROUP);
        FilterGroup filterGroup0 = new FilterGroup(new IFilter[]{filter1,filterGroup1}, FilterGroup.OR_FILTER_GROUP);
        boolean expectedResult = filterGroup0.getEvaluation(objetive);
        assertEquals(expectedResult, false);
    }//End test
    
}//End FilterGroupTest
