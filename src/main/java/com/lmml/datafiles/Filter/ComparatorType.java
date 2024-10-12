package com.lmml.datafiles.Filter;

public enum ComparatorType {

    IGUAL("=="),
	MAYORQUE(">"),
	MAYOROIGUALQUE(">="),
	MENORQUE("<"),
	MENOROIGUALQUE("=<"),
	DIFERENTE("<>"),
	EMPIEZAPOR("Empieza por"),
	TERMINAPOR("Termina por"),
	NOEMPIEZAPOR("No empieza por"),
	NOTERMINAPOR("No termina por"),
	CONTIENE("Contiene"),
	NOCONTIENE("No contiene"),
	REGEX("Expresión regular");

    String mask;



    ComparatorType(String _mask){
        mask = _mask;
    }//End Constructor

}//End ComparatorType
