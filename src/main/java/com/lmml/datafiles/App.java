package com.lmml.datafiles;

import com.lmml.datafiles.DataFrame.*;
import com.lmml.datafiles.Explorer.*;
import com.lmml.datafiles.Util.Logs;

public class App 
{
    public static void main(String[] args){

        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        
        Test.runDataframeTests();
        Test.runFieldExtractorTests();

    }//End main
}//End App
