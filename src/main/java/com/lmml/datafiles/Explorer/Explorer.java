package com.lmml.datafiles.Explorer;

import java.io.BufferedReader;

import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.FilterGroup;

public class Explorer {

    private FieldsSelector fieldsSelector;
    private FileDescriptor fileDescriptor;
    private FilterGroup filters;
    
    public Dataframe get(){
        Dataframe dataframe = new Dataframe(fieldsSelector.getdHeads());
        FieldsExtractor fieldsExtractor = fileDescriptor.getFieldsExtractor();
        int expectedLineFieldsCount = fileDescriptor.getHeads().length;
        try {
            BufferedReader reader = fileDescriptor.getReader();
            String line = reader.readLine();
            String[] lineArray;
            while (line != null){
                lineArray = fieldsExtractor.get(line);
                if (lineArray.length == expectedLineFieldsCount){
                    if (filters.getEvaluation(lineArray)){
                        dataframe.addRecord(fieldsSelector.getSelection(lineArray));
                    }//End if
                }//End if
                line = reader.readLine();
            }//End while
            reader.close();
            return dataframe;
        }catch (Exception e){
            return dataframe;
        }//End try
    }//End get

    Explorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor, FilterGroup _filsters){
        fieldsSelector = _fieldsSelector;
        fileDescriptor = _fileDescriptor;
        filters = _filsters;
    }//End Constructor

}//End Explorer
