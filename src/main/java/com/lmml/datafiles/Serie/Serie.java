package com.lmml.datafiles.Serie;

import java.util.ArrayList;

public class Serie {

    public String name = "";
    
    private ArrayList<Object> data = new ArrayList<Object>();

    public void add(Object _value){
        data.add(_value);
    };
    
    public Serie(String _name){
        name = _name;
    }//End Constructor

}//End Serie
