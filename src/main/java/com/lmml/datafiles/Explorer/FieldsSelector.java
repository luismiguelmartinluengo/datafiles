package com.lmml.datafiles.Explorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
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
    private String[] sortedHeads;
    
    public String[] getSelection(String[] _lineList){
        List<String> extracted = IntStream.of(indices).mapToObj(i -> _lineList[i]).collect(Collectors.toList());
        return extracted.toArray(new String[extracted.size()]);
    }//End for

    public String[] getdHeads(){
        return sortedHeads;
    }//return

    FieldsSelector(FileDescriptor _fileDescriptor, String[] _fields){
        ArrayList<String> fileDescriptorFields = new ArrayList<String>(Arrays.asList(_fileDescriptor.getHeads()));
        TreeSet<Integer> fieldsIndexSet = new TreeSet<>(Arrays.stream(_fields).map(fileDescriptorFields::indexOf).collect(Collectors.toList()));
        fieldsIndexSet.remove(-1);
        indices = fieldsIndexSet.stream().mapToInt(Integer::intValue).toArray();
        sortedHeads = fieldsIndexSet.stream().map(fileDescriptorFields::get).toArray(String[]::new);
    }//End Constructor


}//End FieldsSelector
