package com.lmml.datafiles.Serie;

import java.util.ArrayList;
import java.util.Arrays;

public class SerieString extends Serie {

    ArrayList<String> data = new ArrayList<String>();

    SerieString(String _name){
        super(_name);
    }//End Constructor
 
    @Override
    void add(String _value) {
        data.add(_value);
    }//End add

    @Override
    void addAll(String[] _values) {
        data.addAll(Arrays.asList(_values));
    }//End addAll

    @Override
    String getString(int _index) {
        return (_index < data.size() && _index >-1)?data.get(_index):null;    
    }//End getString

    @Override
    void add(Integer _value) {
        this.add(_value.toString());
    }//End add

    @Override
    void addAll(Integer[] _values) {
        Arrays.
    }//End addAll

    @Override
    Integer getInteger(int _index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInteger'");
    }

}
