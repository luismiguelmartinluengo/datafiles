package com.lmml.datafiles;

import com.lmml.datafiles.DataFrame.*;

public class App 
{
    public static void main(String[] args){
        String[][] datosPrueba = {{"a1","a2","a3"},{"b1","b2","b3"}};
        String[] nombres = {"ColA","ColB"};
        Dataframe df = new Dataframe(nombres, datosPrueba);
        df.writeConsole();
    }//End main
}//End App
