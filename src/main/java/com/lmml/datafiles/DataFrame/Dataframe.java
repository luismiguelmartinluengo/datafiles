package com.lmml.datafiles.DataFrame;

import java.util.ArrayList;

public class Dataframe {

    private ArrayList<Serie> series = new ArrayList<Serie>();

    int getRecordCount(){
        if (series.size() > 0){
            return series.get(0).getSize();
        }else{
            return 0;
        }//End if
    }//End getRecordCount
    
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

    void writeConsole(){
        StringBuffer output = new StringBuffer();
        for(Serie s: series){
            output.append(s.getConsoleName());
        }//End for
        for(int index = 0; index < this.getRecordCount(); index++){
            output.append("\n");
            for(Serie s: series){
                output.append(s.getConsoleOutput(index));
            }//End for
        }//End for
        System.out.println(output.toString());
    }//End writeConsole

    private void adjustSeries(){
        int maxRecordCount = 0;
        for(Serie serie : series){
            maxRecordCount = Math.max(maxRecordCount, serie.getSize());
        }//End for
        for(Serie serie: series){
            serie.resize(maxRecordCount);
        }//End for
    }//End adjustSeries

    Dataframe(String[] _names, String[][] _values){
        int maxLength = Math.min(_names.length, _values.length);
        for (int index = 0; index < maxLength; index++ ){
            series.add(new Serie(_names[index], _values[index]));
        }//End for
        this.adjustSeries();
    }//End Constructor

}//End Dataframe
