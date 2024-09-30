package com.lmml.datafiles.Explorer;

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

    FilterGroupEvaluator evaluator;

    private void createEvaluator(boolean _isAndFilterGroup){
        if(_isAndFilterGroup){
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

    public FilterGroup (IFilter _filtro, boolean _isAndFilterGroup){
        this.createEvaluator(_isAndFilterGroup);
        evaluator.add(_filtro);
    }//End Constructor

    public FilterGroup(IFilter[] _filters, boolean _isAndFilterGroup){
        this.createEvaluator(_isAndFilterGroup);
        evaluator.addAll(_filters);
    }//End Constructor

    @Override
    public boolean getEvaluation(String[] _values) {
        return evaluator.getEvaluation(_values);
    }//End getEvaluation

}//End Filters
