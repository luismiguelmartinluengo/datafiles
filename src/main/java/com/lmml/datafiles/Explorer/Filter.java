package com.lmml.datafiles.Explorer;

public class Filter {

    Comparator comparator;
    int indexField;
    String expression;

    boolean getEvaluation(String[] _values){
       return comparator.get(_values[indexField], expression);
    }//End getEvaluation

    Filter (Comparator _comparator, int _indexField, String _expression){
        comparator = _comparator;
        indexField = _indexField;
        expression  = _expression;
    }//End Constructor

}//End Filter
