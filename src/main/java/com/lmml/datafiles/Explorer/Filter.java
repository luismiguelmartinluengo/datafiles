package com.lmml.datafiles.Explorer;

public class Filter implements IFilter{

    Comparator comparator;
    int indexField;
    String expression;

    public Filter (Comparator _comparator, int _indexField, String _expression){
        comparator = _comparator;
        indexField = _indexField;
        expression  = _expression;
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        return comparator.get(_values[indexField], expression);
    }//End getEvaluation
    

}//End Filter
