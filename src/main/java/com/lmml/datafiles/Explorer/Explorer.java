package com.lmml.datafiles.Explorer;

import java.io.BufferedReader;
import java.io.IOException;

import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.FilterDefault;
import com.lmml.datafiles.Filter.IFilter;

public class Explorer {

    private FieldsSelector fieldsSelector;
    private FileDescriptor fileDescriptor;
    private IFilter filter;

    private int getValidationValue(){
        int result = 0;
        if (fileDescriptor.getFieldsSeparator().equals(fileDescriptor.getFieldsDelimiter())) result += 1;
        if (!fileDescriptor.fileExist()) result += 1;
        if (filter.getMaxIndexField() >= fileDescriptor.getHeads().length) result += 1;
        if (fieldsSelector.getMaxIndex() >= fileDescriptor.getHeads().length) result +=10;//
        return result;
    }//End getValidationValue

    public boolean getValidation(){
        return getValidationValue() == 0;
    }//End getValidation

    public Dataframe get(){
        int validationValue = getValidationValue();
        Dataframe returnDataframe;
        if (validationValue >= 10){ //Parte Select incorrecta, devuelvo Dataframe completamente vacío.
            returnDataframe = new Dataframe(new String[0]);
        }else{
            returnDataframe = new Dataframe(fileDescriptor.getHeads(fieldsSelector.getIndices()));
            if (validationValue == 0){
                FieldsExtractor fieldsExtractor = fileDescriptor.getFieldsExtractor();
                int expectedLineFieldsCount = fileDescriptor.getHeads().length;
                BufferedReader reader = null;
                try {
                    reader = fileDescriptor.getReader();
                    String line = reader.readLine();
                    String[] lineArray;
                    while (line != null){
                        lineArray = fieldsExtractor.get(line);
                        if (lineArray.length == expectedLineFieldsCount){
                            if (filter.getEvaluation(lineArray)){
                                returnDataframe.addRecord(fieldsSelector.getSelection(lineArray));
                            }//End if
                        }//End if
                        line = reader.readLine();
                    }//End while
                    reader.close();
                }catch (Exception e){
                    if (reader != null && reader instanceof BufferedReader){
                        try {
                            reader.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }//End try
                    }//End if
                }//End try
            }//Ed if
        }//End if
        return returnDataframe;
    }//End get

    Explorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor, IFilter _filter){
        fieldsSelector = _fieldsSelector;
        fileDescriptor = _fileDescriptor;
        filter = _filter;
    }//End Constructor

    Explorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor){
        this(_fieldsSelector, _fileDescriptor, new FilterDefault());
    }//End Constructor

}//End Explorer
