package com.lmml.datafiles.DataFrame;

import java.util.ArrayList;

import com.lmml.datafiles.Serie.Serie;


public class Dataframe {

    private ArrayList<Serie> series = new ArrayList<Serie>();

    void addRecord(Object[] _values){
        /*Aáde un elemento del registro pasado por parámetro a cada una de las series del Dataframe
         * Si se pasan más valores en el registro que seires existentes, se descartan los datos que sobren por la derecha
         * Si no se pasan valores para todas las series, se rellenan las últims con valor nulo
         */
        int numSeriesWithValue = Math.min(_values.length, series.size());
        for(int i = 0; i < numSeriesWithValue; i++){
            series.get(i).add(_values[i]);
        }//End for
        for (int i = _values.length; i < series.size(); i++){
            series.get(i).add(null);
        }//End for
    }//End addRecord


    Dataframe(String[] _names){
        for(String name : _names){
            series.add(new Serie(name));
        }//End for
    }//End Constructor

}
