package com.lmml.datafiles.Explorer;

import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.lmml.datafiles.DataFrame.Dataframe;
import com.lmml.datafiles.Filter.FilterDefault;

public class RunnableFileExplorerTest {

    FieldsSelector fieldsSelector;
    private FileDescriptor fileDescriptor;
    private FileExplorerListener listener;
    
    @BeforeEach
    public void setUp() {
        fieldsSelector = new FieldsSelector(new int[]{0,1,2});
        fileDescriptor = new FileDescriptor("./test/test.csv", ',', null, new String[]{"c1","c2","c3"}, 1);
        listener = mock(FileExplorerListener.class);
    }//End setup

    @Test
    public void testRun() {
        RunnableFileExplorer runnableFileExplorer = new RunnableFileExplorer(fieldsSelector, fileDescriptor, new FilterDefault(), listener);
        runnableFileExplorer.run();
        ArgumentCaptor<Dataframe> captor = ArgumentCaptor.forClass(Dataframe.class);
        verify(listener).FileExplorationCompleted(captor.capture());
        Dataframe result = captor.getValue();
        assertNotNull(result);
        assertTrue(result instanceof Dataframe);
    }//End test

    @Test
    public void testRunWithoutFilter() {
        RunnableFileExplorer runnableFileExplorer = new RunnableFileExplorer(fieldsSelector, fileDescriptor, listener);
        runnableFileExplorer.run();
        ArgumentCaptor<Dataframe> captor = ArgumentCaptor.forClass(Dataframe.class);
        verify(listener).FileExplorationCompleted(captor.capture());
        Dataframe result = captor.getValue();
        assertNotNull(result);
        assertTrue(result instanceof Dataframe);
    }//End test

}//End RunableFileExplorerTest

