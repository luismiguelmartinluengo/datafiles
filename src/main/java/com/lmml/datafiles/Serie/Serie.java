package com.lmml.datafiles.Serie;

import java.util.Arrays;
import java.util.ArrayList;

public class Serie {

    private String[] data = new String[0];
    
    void add(String _value){
        int newIndex = data.length;
        data = Arrays.copyOf(data, newIndex+1);
        data[newIndex] = _value;
    }//End add

    void add(String[] _values){
        int newIndex = data.length;
        data = Arrays.copyOf(data, newIndex + _values.length);
        for (String value : _values) {
            data[newIndex] = value;
            newIndex++;
        }//End for
    }//End add

}//End Serie
