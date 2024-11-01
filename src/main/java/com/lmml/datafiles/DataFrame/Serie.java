package com.lmml.datafiles.DataFrame;

import java.util.ArrayList;
import java.util.Arrays;

public class Serie {

    private String name = "";
    private int consoleWidth = -1; //todos los métodos que alteren el contenido de data tienen que poner este valor a -1
    
    private ArrayList<String> data = new ArrayList<String>();

    public String getName(){
        return name;
    }//End getName

    public void add(String _value){
        data.add(_value);
        consoleWidth = -1;
    }//End add

    public String getString(int _index){
        String returnValue = null;
        if (data.size()> _index){
            returnValue = data.get(_index);
        }//End if
        return (returnValue == null)?"":returnValue;
    }//End getString

    public String[] getString(){
        return data.toArray(String[]::new);
    }//End getString

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
        for (String d: data){
            if (d != null) {
                width = Math.max(width, d.length());
            }//End if
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
