package com.lmml.datafiles.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MaskedValues<M,V> {
    private ArrayList<M> masks = new ArrayList<>();
    private ArrayList<V> values = new ArrayList<>();

    public M getMask(V _value) {
        return masks.get(values.indexOf(_value));
    }//End getMask

    public V getValue(M _mask) {
        return values.get(masks.indexOf(_mask));
    }//End getValue
    
    @SuppressWarnings("unchecked")
    public M[] getMasks() {
        return masks.toArray((M[]) Array.newInstance(masks.get(0).getClass(), masks.size()));
    }//End getMasks


    @SuppressWarnings("unchecked")
    public V[] getValues(){
        return values.toArray((V[]) Array.newInstance(values.get(0).getClass(), values.size()));
    }//End getValue

    public MaskedValues(M[] _masks, V[] _values) {
        if (_masks.length != _values.length) {
            throw new IllegalArgumentException("Los arrays de máscaras y valores deben tener la misma longitud.");
        }
        masks = new ArrayList<>(Arrays.asList(_masks));
        values = new ArrayList<>(Arrays.asList(_values));
    }//End Constructor
    
}//End class MaskedValue
