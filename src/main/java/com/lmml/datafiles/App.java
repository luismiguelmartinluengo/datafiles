package com.lmml.datafiles;

import com.lmml.datafiles.Util.Logs;
import com.lmml.datafiles.UI.FrmMain;

public class App 
{
    public static void main(String[] args){

        Logs.changeLevel(Logs.INFO_LEVEL);
        System.out.println("main ok");
        new FrmMain();
        Logs.close();
        
    }//End main
}//End App
