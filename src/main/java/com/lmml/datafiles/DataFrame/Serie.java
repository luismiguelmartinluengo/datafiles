package com.lmml.datafiles.DataFrame;

import java.util.ArrayList;
import java.util.Arrays;

public class Serie {

    public String name = "";
    private int consoleWidth = -1; //todos los métodos que alteren el contenido de data tienen que poner este valor a -1
    
    private ArrayList<Object> data = new ArrayList<Object>();

    public void add(Object _value){
        data.add(_value);
        consoleWidth = -1;
    }//End add

    public String getString(int _index){
        if (data.size()> _index){
            return data.get(_index).toString();
        }else{
            return "";
        }//End getString
    }//End

    int getSize(){
        return data.size();
    }//End getSize

    void resize(int _newSize){
        while (data.size()> _newSize) {
            data.remove(_newSize);
        }//End while
        while (data.size()< _newSize){
            data.add(null);
        }//End data
    }//End resize

    private void calculateWidthConsoleOutput(){
        int width = name.length();
            for (Object d: data){
                width = Math.max(width, d.toString().length());
            }//End for
            consoleWidth = width;
    }//End calculateWidthConsoleOutput
    
    String getConsoleName(){
        if(consoleWidth < 0){
            this.calculateWidthConsoleOutput();
        }//End if
        return this.name + " ".repeat(consoleWidth - this.name.length());
    }//End getConsoleName

    String getConsoleOutput(int _index){
        if (consoleWidth < 0) {
            calculateWidthConsoleOutput();
        }//End if
        String stringData = this.getString(_index);
        return stringData + " ".repeat(consoleWidth - stringData.length());
    }//End getConsoleWidth

    public Serie(String _name, String[] _values){
        name = _name;
        data.addAll(Arrays.asList(_values));
    }//End Constructor

}//End Serie
