package com.lmml.datafiles.Explorer;

import java.util.ArrayList;
import java.util.Arrays;

public class Filters {
    
    ArrayList<Filter> filters = new ArrayList<Filter>();
    boolean isAndRelationship = true;

    void add(Filter _filter){
        filters.add(_filter);
    }//End add

    void addAll(Filter[] _filters){
        filters.addAll(Arrays.asList(_filters));
    }//End addAll

    private boolean getEvaluationAsAndRelationship(String[] _values){

    }//End getEvaluationAsAndRelationship

    private boolean getEvaluationAsOrRelationship(String[] _values){

    }//End getEvaluationAsOrRelationship

    boolean getEvaluation(String[] _values){
        if 

    }//End if

    Filters(Filter _filter, boolean _isAndRelationship){
        this.add(_filter);
        isAndRelationship = _isAndRelationship;
    }//End Constructor

    Filters(Filter[] _filters, boolean _isAndRelationship){
        this.addAll(_filters);
        isAndRelationship = _isAndRelationship;
    }//End Constructor

}//End Filters
