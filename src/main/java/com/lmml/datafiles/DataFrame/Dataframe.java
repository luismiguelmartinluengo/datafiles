package com.lmml.datafiles.DataFrame;

import java.util.ArrayList;

public class Dataframe {

    private ArrayList<Serie> series = new ArrayList<Serie>();

    public int getRecordCount(){
        if (series.size() > 0){
            return series.get(0).getSize();
        }else{
            return 0;
        }//End if
    }//End getRecordCount
    
    public int getSeriesCount(){
        return series.size();
    }//End getSeriesCount

    public void addRecord(String[] _values){
        /*Añade un elemento del registro pasado por parámetro a cada una de las series del Dataframe
         * Si se pasan más valores en el registro que series existentes, se descartan los datos que sobren por la derecha
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

    public String getString(int _indexSerie, int _indexRecord){
        if (series.size() > _indexSerie && getRecordCount() > _indexRecord){
            return series.get(_indexSerie).getString(_indexRecord);
        }else{
            return "";
        }//End if
    }//End getString

    public String[] getHeads(){
        return series.stream().map(Serie::getName).toArray(String[]::new);
    }//End getHeads
    
    public String[][] getString(){
        return series.stream().map(Serie::getString).toArray(String[][]::new);
    }//End getString

    public void writeConsole(){
        StringBuffer output = new StringBuffer();
        for(Serie s: series){
            output.append(s.getConsoleName()).append("\t");
        }//End for
        for(int index = 0; index < this.getRecordCount(); index++){
            output.append(System.lineSeparator());
            for(Serie s: series){
                output.append(s.getConsoleOutput(index)).append("\t");
            }//End for
            output.deleteCharAt(output.length()-1);//para quitar el último tabulador del registro
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

    public Dataframe(String[] _names, String[][] _values){
        int maxLength = Math.min(_names.length, _values.length);
        for (int index = 0; index < maxLength; index++ ){
            series.add(new Serie(_names[index], _values[index]));
        }//End for
        this.adjustSeries();
    }//End Constructor

    public Dataframe(String[] _names){
        for (String name: _names){
            series.add(new Serie(name, new String[0]));
        }//End for
    }//End constructor

}//End Dataframe
