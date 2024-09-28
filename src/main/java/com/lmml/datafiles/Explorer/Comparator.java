package com.lmml.datafiles.Explorer;

import com.lmml.datafiles.Util.Logs;

public enum Comparator {

    IGUAL("==", true),
	MAYORQUE(">", true),
	MAYOROIGUALQUE(">=", true),
	MENORQUE("<", true),
	MENOROIGUALQUE("=<", true),
	DIFERENTE("<>", true),
	EMPIEZAPOR("Empieza por", false),
	TERMINAPOR("Termina por", false),
	NOEMPIEZAPOR("No empieza por", false),
	NOTERMINAPOR("No termina por", false),
	CONTIENE("Contiene", false),
	NOCONTIENE("No contiene", false),
	REGEX("Expresión regular",false);
	
	private String mask;
	private Boolean isNumeric;
	
	String getMask() {
		return mask;
	}//End getMask
	
	Boolean getIsNumeric() {
		return isNumeric;
	}//End getIsNumeric
	
	Boolean get(String _a, String _b) {
		try {
			Boolean returnValue;
			switch (this) {
				case IGUAL:
					returnValue = _a.equals(_b); break;
				case MAYORQUE:
					returnValue =  _a.compareTo(_b) > 0; break;
				case MAYOROIGUALQUE:
					returnValue =  _a.compareTo(_b) >= 0; break;
				case MENORQUE:
					returnValue = _a.compareTo(_b) < 0; break;
				case MENOROIGUALQUE:
					returnValue = _a.compareTo(_b) <= 0; break;
				case DIFERENTE:
					returnValue = !(_a.equals(_b)); break;
				case EMPIEZAPOR:
					returnValue = _a.startsWith(_b); break;
				case NOEMPIEZAPOR:
					returnValue = !(_a.startsWith(_b)); break;
				case TERMINAPOR:
					returnValue = _a.endsWith(_b); break;
				case NOTERMINAPOR:
					returnValue = !(_a.endsWith(_b)); break;
				case CONTIENE:
					returnValue = _a.contains(_b); break;
				case NOCONTIENE:
					returnValue = !(_a.contains(_b));  break;
				case REGEX:
					returnValue = _a.matches(_b);  break;
				default:
					returnValue = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Tipo de comparador '%s' no contemplado en el procedimiento get", this.getMask()));}
			}//End switch
			return returnValue;
		}catch (Error e) {
			Logs.critical(Thread.currentThread().getStackTrace(), "Error indeterminado", e);
			return null;
		}//End try
	}//End get para cadenas
	
	Boolean get(Double _a, Double _b) {
		try {
			Boolean returnValue;
			switch (this) {
				case IGUAL:
					returnValue = _a.equals(_b); break;
				case MAYORQUE:
					returnValue = _a.compareTo(_b) > 0; break;
				case MAYOROIGUALQUE:
					returnValue = _a.compareTo(_b) >= 0; break;
				case MENORQUE:
					returnValue = _a.compareTo(_b) < 0; break;
				case MENOROIGUALQUE:
					returnValue = _a.compareTo(_b) <= 0; break;
				case DIFERENTE:
					returnValue = !(_a.equals(_b)); break;
				case EMPIEZAPOR: case NOEMPIEZAPOR: case TERMINAPOR: case NOTERMINAPOR: case CONTIENE: case NOCONTIENE: case REGEX:
					returnValue = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Comparador '%s' incompatible con valores num�rcios: %d y %d",  this.getMask(), _a, _b));}
					break;
				default:
					returnValue = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Tipo de comparador '%s' no contemplado en el procedimiento get", this.getMask()));}
			}//End switch
			return returnValue;
		}catch (Error e) {
			Logs.critical(Thread.currentThread().getStackTrace(), "Error indeterminado", e);
			return null;
		}//End try
	}//End get para n�meros
	
	Comparator(String _mask, Boolean _isNumeric){
		mask = _mask;
		isNumeric = _isNumeric;
	}//End Constructor

}//End  Comparator
