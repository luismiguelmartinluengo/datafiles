package com.lmml.datafiles;

import com.lmml.datafiles.DataFrame.*;
import com.lmml.datafiles.Explorer.*;
import com.lmml.datafiles.Filter.ComparatorType;
import com.lmml.datafiles.Filter.Filter;
import com.lmml.datafiles.Filter.FilterGroup;
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

    static void runFilterTest(){
        /*
        Las condiciones de los filtros se configuran 
        para que devuelva true si se cumplen todas estas condiciones en el registro:
            - El campo 0 es igual a "Hola" y el campo 1 es diferente de "pepe";
            - El campo 2 contiene "a" y el campo 3 empieza por "e", o el campo 2 tiene longitud 3;
            - El campo 1 termina por "e"
        En pseudo código sería:
            (0=Hola and 1!=pepe)
            and ((2_Contains_a and 3_startwith_e)
                or 2_length_3)
            and 1_endswith_e
        */
        String[] record = {"Hola","que","tal","estas"};
        FilterGroup finalFilterGroup;
        Filter equalFilter = new Filter(0, ComparatorType.IGUAL, "Hola");
        Filter diffFilter = new Filter(1, ComparatorType.DIFERENTE,  "pepe");
        Filter[] arrayEqualDiffFilters = {equalFilter,diffFilter}; //addicción
        FilterGroup andFilterGroup = new FilterGroup(arrayEqualDiffFilters, true);
        Filter containsFilter = new Filter(2, ComparatorType.CONTIENE, "a");
        Filter startWithFilter = new Filter(3, ComparatorType.EMPIEZAPOR,  "e");
        FilterGroup andFilterGroupIn = new FilterGroup(containsFilter, true);
        andFilterGroupIn.add(startWithFilter);
        Filter regexFilter = new Filter(2,ComparatorType.REGEX,  ".{3}");
        FilterGroup orFilterGroupOut = new FilterGroup(andFilterGroupIn, false);
        orFilterGroupOut.add(regexFilter);
        Filter endWithFilter = new Filter(1,ComparatorType.TERMINAPOR,  "e");
        finalFilterGroup = new FilterGroup(andFilterGroup, true);
        finalFilterGroup.add(orFilterGroupOut);
        finalFilterGroup.add(endWithFilter);
        System.out.println(finalFilterGroup.getEvaluation(record));
    }//End runFilterTest

}//Test
