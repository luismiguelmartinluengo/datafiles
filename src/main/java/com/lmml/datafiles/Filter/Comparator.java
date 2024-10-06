package com.lmml.datafiles.Filter;

public class Comparator {

    private abstract class InnerComparator{

        abstract boolean get(String _a, String _b);

    }//End InnerComparator

    private class InnerComparatorEqual extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.equals(_b);
        }//End get

    }//End InnerComparatorEqual

    private class InnerComparatorGreater extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.compareTo(_b) > 0;
        }//End get

    }//End InnerComparatorGreater

    private class InnerComparatorGreaterEqual extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.compareTo(_b) >= 0;
        }//End get

    }//End InnerComparatorGreaterEqual

    private class InnerComparatorLess extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.compareTo(_b) < 0;
        }//End get

    }//End InnerComparatorLess

    private class InnerComparatorLessEqual extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.compareTo(_b) <= 0;
        }//End get

    }//End InnerComparatorLessEqual

    private class InnerComparatorDifferent extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return !(_a.equals(_b));
        }//End get

    }//End InnerComparatorDifferent

    private class InnerComparatorStartWith extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.startsWith(_b);
        }//End get

    }//End InnerComparatorStartWith

    private class InnerComparatorEndsWith extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.endsWith(_b);
        }//End get

    }//End InnerComparatorEndsWith

    private class InnerComparatorNotStartWith extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return !(_a.startsWith(_b));
        }//End get

    }//End InnerComparatorNotStartWith

    private class InnerComparatorNotEndsWith extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return !(_a.endsWith(_b));
        }//End get

    }//End InnerComparatorNotEndsWith

    private class InnerComparatorContains extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.contains(_b);
        }//End get

    }//End InnerComparatorContains

    private class InnerComparatorNotContains extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return !(_a.contains(_b));
        }//End get

    }//End InnerComparatorNotContains

    private class InnerComparatorRegex extends InnerComparator{

        @Override
        boolean get(String _a, String _b) {
            return _a.matches(_b);
        }//End get

    }//End InnerComparatorRegex

    InnerComparator innerComparator;

    boolean get(String _a, String _b){
        return innerComparator.get(_a, _b);
    }//End get

    Comparator(ComparatorType _comparatorType){
        switch(_comparatorType){
            case IGUAL:
                innerComparator = new InnerComparatorEqual(); break;
            case MAYORQUE:
                innerComparator = new InnerComparatorGreater(); break;
            case MAYOROIGUALQUE:
                innerComparator = new InnerComparatorGreaterEqual(); break;
            case MENORQUE:
                innerComparator = new InnerComparatorLess(); break;
            case MENOROIGUALQUE:
                innerComparator = new InnerComparatorLessEqual(); break;
            case DIFERENTE:
                innerComparator = new InnerComparatorDifferent(); break;
            case EMPIEZAPOR:
                innerComparator = new InnerComparatorStartWith(); break;
            case TERMINAPOR:
                innerComparator = new InnerComparatorEndsWith(); break;
            case NOEMPIEZAPOR:
                innerComparator = new InnerComparatorNotStartWith(); break;
            case NOTERMINAPOR:
                innerComparator = new InnerComparatorNotEndsWith(); break;
            case CONTIENE:
                innerComparator = new InnerComparatorContains(); break;
            case NOCONTIENE:
                innerComparator = new InnerComparatorNotContains(); break;
            case REGEX:
                innerComparator = new InnerComparatorRegex(); break;
        }//End switch

    }//End Constructor

}//End  Comparator
