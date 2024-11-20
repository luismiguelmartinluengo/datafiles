package com.lmml.datafiles.Explorer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class FieldsSelector {
    /*Este objeto asume que el array que representa la linea de la que tiene que extraer
     * los valores siempre es correcta, es decir, que encaja con el Descriptor de fichero
     * que se ha usado para construir el selector de campos. Por lo tanto la longitud del array
     * pasado a getSelección siempre será igual o superior al índice máximo almacenado en el 
     * array indices
     */
   
    private int[] indices = new int[0];

    public int[] getIndices(){
        return indices;
    }//End getIndices

    public int getMaxIndex(){
        return (indices.length > 0)?Arrays.stream(indices).max().getAsInt():-1;
    }//End getMaxIndex
    
    public String[] getSelection(String[] _lineList){
        List<String> extracted = IntStream.of(indices).mapToObj(i -> _lineList[i]).collect(Collectors.toList());
        return extracted.toArray(new String[extracted.size()]);
    }//End for

    public FieldsSelector(int[] _indices){
        indices = _indices;
    }//End Constructor





}//End FieldsSelector
