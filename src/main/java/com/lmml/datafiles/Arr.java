package com.lmml.datafiles;

final class ArrExt {

    static int[] toInt(String[] _values){
        int convertedValue;
        int[] convertedArray = new int[_values.length];
        for(int i = 0; i<_values.length; i++){
            try {
                convertedValue = Integer.parseInt(_values[i]);
            } catch (NumberFormatException e) {
                convertedValue = 0;
            }//End try
            convertedArray[i] = convertedValue;
        }//End for
        return convertedArray;
    }//End toInt

}//End ArrExt
