package com.lmml.datafiles.Filter;

public class Filter implements IFilter{

    ComparatorOld comparator;
    int indexField;
    String expression;

    public Filter (ComparatorOld _comparator, int _indexField, String _expression){
        comparator = _comparator;
        indexField = _indexField;
        expression  = _expression;
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        return comparator.get(_values[indexField], expression);
    }//End getEvaluation
    

}//End Filter
