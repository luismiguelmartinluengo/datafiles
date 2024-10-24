package com.lmml.datafiles;

import com.lmml.datafiles.Util.Logs;

public class App 
{
    public static void main(String[] args){

        Logs.changeLevel(Logs.INFO_LEVEL);
        System.out.println("main ok");
        Logs.close();
        
    }//End main
}//End App
