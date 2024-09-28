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
	REGEX("Expresi�n regular",false);
	
	private String representacion;
	private Boolean es_numerico;
	
	String getRepresentacion() {
		return representacion;
	}//End getRepresentacion
	
	Boolean getEsNumerico() {
		return es_numerico;
	}//End getEsNumerico
	
	Boolean get(String _a, String _b) {
		try {
			Boolean vr;
			switch (this) {
				case IGUAL:
					vr = _a.equals(_b); break;
				case MAYORQUE:
					vr =  _a.compareTo(_b) > 0; break;
				case MAYOROIGUALQUE:
					vr =  _a.compareTo(_b) >= 0; break;
				case MENORQUE:
					vr = _a.compareTo(_b) < 0; break;
				case MENOROIGUALQUE:
					vr = _a.compareTo(_b) <= 0; break;
				case DIFERENTE:
					vr = !(_a.equals(_b)); break;
				case EMPIEZAPOR:
					vr = _a.startsWith(_b); break;
				case NOEMPIEZAPOR:
					vr = !(_a.startsWith(_b)); break;
				case TERMINAPOR:
					vr = _a.endsWith(_b); break;
				case NOTERMINAPOR:
					vr = !(_a.endsWith(_b)); break;
				case CONTIENE:
					vr = _a.contains(_b); break;
				case NOCONTIENE:
					vr = !(_a.contains(_b));  break;
				case REGEX:
					vr = _a.matches(_b);  break;
				default:
					vr = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Tipo de comparador '%s' no contemplado en el procedimiento get", this.getRepresentacion()));}
			}//End switch
			return vr;
		}catch (Error e) {
			Logs.critical(Thread.currentThread().getStackTrace(), "Error indeterminado", e);
			return null;
		}//End try
	}//End get para cadenas
	
	Boolean get(Double _a, Double _b) {
		try {
			Boolean vr;
			switch (this) {
				case IGUAL:
					vr = _a.equals(_b); break;
				case MAYORQUE:
					vr = _a.compareTo(_b) > 0; break;
				case MAYOROIGUALQUE:
					vr = _a.compareTo(_b) >= 0; break;
				case MENORQUE:
					vr = _a.compareTo(_b) < 0; break;
				case MENOROIGUALQUE:
					vr = _a.compareTo(_b) <= 0; break;
				case DIFERENTE:
					vr = !(_a.equals(_b)); break;
				case EMPIEZAPOR: case NOEMPIEZAPOR: case TERMINAPOR: case NOTERMINAPOR: case CONTIENE: case NOCONTIENE: case REGEX:
					vr = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Comparador '%s' incompatible con valores num�rcios: %d y %d",  this.getRepresentacion(), _a, _b));}
					break;
				default:
					vr = null;
					if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("Tipo de comparador '%s' no contemplado en el procedimiento get", this.getRepresentacion()));}
			}//End switch
			return vr;
		}catch (Error e) {
			Logs.critical(Thread.currentThread().getStackTrace(), "Error indeterminado", e);
			return null;
		}//End try
	}//End get para n�meros
	
	Comparator(String _representacion, Boolean _es_numerico){
		representacion = _representacion;
		es_numerico = _es_numerico;
	}//End Constructor

}//End  Comparator
