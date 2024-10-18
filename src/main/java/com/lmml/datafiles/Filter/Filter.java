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
        /*En caso de que se pase un array de valores que no incluye el index experado por el filtro,
         * se devolverá False sin provocar error. La evaluación de la longitud del array, que debe
         * ajustarse al Descriptor de fichero, y la validez de los filtros, la realizará en el objeto que
         * contenga y llame al filtro. Para ello se proporciona la función getMaxIndexField
         * en la interfaz IFilter.
         */
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
