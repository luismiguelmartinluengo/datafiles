package com.lmml.datafiles.Filter;

public class Filter implements IFilter{

    Comparator comparator;
    int indexField;
    String expression;

    public Filter (int _indexField, ComparatorType _comparatorType, String _expression){
        comparator = new Comparator(_comparatorType);
        indexField = _indexField;
        expression  = _expression;
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        if (_values.length > indexField){
            return comparator.get(_values[indexField], expression);
        }else{
            return false;
        }//End if
    }//End getEvaluation

    @Override
    public int getMaxIndexField() {
        return indexField;
    }//End getMaxIndexField
    

}//End Filter
