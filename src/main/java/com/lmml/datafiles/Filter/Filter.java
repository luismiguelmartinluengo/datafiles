package com.lmml.datafiles.Filter;

public class Filter implements IFilter{

    Comparator comparator;
    int indexField;
    String expression;

    public Filter (ComparatorType _comparatorType, int _indexField, String _expression){
        comparator = new Comparator(_comparatorType);
        indexField = _indexField;
        expression  = _expression;
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        return comparator.get(_values[indexField], expression);
    }//End getEvaluation

    @Override
    public int getMaxIndexField() {
        return indexField;
    }//End getMaxIndexField
    

}//End Filter
