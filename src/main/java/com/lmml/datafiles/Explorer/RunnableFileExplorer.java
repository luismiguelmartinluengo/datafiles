package com.lmml.datafiles.Explorer;

import com.lmml.datafiles.Filter.IFilter;

public class RunnableFileExplorer extends FileExplorer implements Runnable{

    FileExplorerListener listener = null;

    RunnableFileExplorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor, IFilter _filter, FileExplorerListener _listener){
        super(_fieldsSelector, _fileDescriptor,_filter);
        listener = _listener;
    }//End Constructor

    RunnableFileExplorer(FieldsSelector _fieldsSelector, FileDescriptor _fileDescriptor, FileExplorerListener _listener){
        super(_fieldsSelector, _fileDescriptor);
        listener = _listener;
    }//End Constructor

    @Override
    public void run() {
        listener.FileExplorationCompleted(get());
    }//End run

}//End RunnableFileExplorer

