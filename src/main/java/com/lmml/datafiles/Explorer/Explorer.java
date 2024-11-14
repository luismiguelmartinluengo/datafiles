package com.lmml.datafiles.Explorer;

import java.io.BufferedReader;

import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.IFilter;

public class Explorer {

    private FieldsSelector fieldsSelector;
    private FileDescriptor fileDescriptor;
    private IFilter filter;
    
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
                    if (filter.getEvaluation(lineArray)){
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

    Explorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor, IFilter _filter){
        fieldsSelector = _fieldsSelector;
        fileDescriptor = _fileDescriptor;
        filter = _filter;
    }//End Constructor

}//End Explorer
