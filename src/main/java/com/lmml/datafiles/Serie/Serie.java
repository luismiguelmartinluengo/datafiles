package com.lmml.datafiles.Serie;

abstract class Serie {

    private String name = "";
    
    String getName(){
        return name;
    }//End getName

    abstract void add(String _value);
    abstract void add(Integer _value);
    abstract void addAll(String[] _values);
    abstract void addAll(Integer[] _values);
    abstract String getString(int _index);
    abstract Integer getInteger(int _index);

    Serie(String _name){
        name = _name;
    }//End Constructor

}//End Serie
