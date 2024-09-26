package com.lmml.datafiles;

import java.io.IOException;

import com.lmml.datafiles.DataFrame.*;
import com.lmml.datafiles.Explorer.*;
import com.lmml.datafiles.Util.Logs;

public class App 
{
    public static void main(String[] args){

        try{
            Logs.start(Logs.INFO_LEVEL);
        }catch (IOException e){
            System.out.println("Error en el inicio del objeto de escritura de logs");
            System.out.println(e.getMessage());
        }//End try

        String[][] datosPrueba = {{"a1","a2","a3"},{"b1","b2","b3"}};
        String[] nombres = {"ColA","ColB"};
        Dataframe df = new Dataframe(nombres, datosPrueba);
        df.writeConsole();

        String linea = "'Hola,que','tal,estas'";
        FieldsExtractor fe = new FieldsExtractor(",","'");
        for (String palabra : fe.get(linea)) {
            System.out.println(palabra);
        }//End for

    }//End main
}//End App
