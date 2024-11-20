package com.lmml.datafiles.Filter;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterGroup implements IFilter{
    
    private abstract class FilterGroupEvaluator{

        ArrayList<IFilter> filters = new ArrayList<IFilter>();

        void add(IFilter _filter){
            filters.add(_filter);
        }//End add

        void addAll(IFilter[] _filtros){
            filters.addAll(Arrays.asList(_filtros));
        }//End addAll

        int getMaxIndexField(){
            int maxIndexField = 0;
            for(IFilter filter: filters){
                maxIndexField = Math.max(maxIndexField, filter.getMaxIndexField());
            }//End for
            return maxIndexField;
        }//End 

        abstract boolean getEvaluation(String[] _values);

    }//End FilterGroupEvalutar

    private class FilterGroupEvaluatorAnd extends FilterGroupEvaluator{

        @Override
        boolean getEvaluation(String[] _values) {
            for (IFilter filter : filters) {
                if (!filter.getEvaluation(_values)){
                    return false;
                }//End if
            }//End for
            return true;
        }//End getEvaluation

    }//End FilterGroupEvaluatorAnd

    private class FilterGroupEvaluatorOr extends FilterGroupEvaluator{

        @Override
        boolean getEvaluation(String[] _values) {
            for (IFilter filter : filters){
                if (filter.getEvaluation(_values)){
                    return true;
                }//End if
            }//End for
            return false;
        }//End getEvaluation

    }//End FilterGroupEvaluatorOr

    public static final int AND_FILTER_GROUP = 0;
    public static final int OR_FILTER_GROUP = 1;
    FilterGroupEvaluator evaluator;

    private void createEvaluator(int _filterGroupType){
        if(_filterGroupType == FilterGroup.AND_FILTER_GROUP){
            evaluator = new FilterGroupEvaluatorAnd();
        }else {
            evaluator = new FilterGroupEvaluatorOr();
        }//End if
    }//createEvaluator

    public void add(IFilter _filter){
        evaluator.add(_filter);
    }//End add

    public void addAll(IFilter[] _filters){
        evaluator.addAll(_filters);
    }//End addAll

    public FilterGroup (IFilter _filtro, int _filterGroupType){
        this.createEvaluator(_filterGroupType);
        evaluator.add(_filtro);
    }//End Constructor

    public FilterGroup(IFilter[] _filters, int _filterGroupType){
        this.createEvaluator(_filterGroupType);
        evaluator.addAll(_filters);
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        return evaluator.getEvaluation(_values);
    }//End getEvaluation

    @Override
    public int getMaxIndexField() {
        return evaluator.getMaxIndexField();
    }//End getMaxIndexField

}//End Filters
