package com.lmml.datafiles.Filter;

public interface IFilter {

    boolean getEvaluation(String[] _values);

    /*Este método se proporciona para permitir la evaluación del máximo index que va a 
    inspeccionar el filtro o grupo de filtros dentro del array de campos sobre los que 
    ejecutar la evaluación de filtro*/
    int getMaxIndexField();


}//End IFilter
