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

    



}//End  Comparator
