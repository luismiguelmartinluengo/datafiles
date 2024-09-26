package com.lmml.datafiles;

import com.lmml.datafiles.DataFrame.*;
import com.lmml.datafiles.Explorer.*;
import com.lmml.datafiles.Util.Logs;

public class Test {

    static void runDataframeTests(){
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        String[][] datosPrueba = {{"a1","a2","a3"},{"b1","b2","b3"}};
        String[] nombres = {"ColA","ColB"};
        Dataframe df = new Dataframe(nombres, datosPrueba);
        df.writeConsole();
    }//End runDataframeTests

    static void runFieldExtractorTests() {
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        String linea = "'Hola,que','tal,estas'";
        FieldsExtractor fe = new FieldsExtractor(",","'");
        for (String palabra : fe.get(linea)) {
            System.out.println(palabra);
        }//End for
    }//

}//Test
