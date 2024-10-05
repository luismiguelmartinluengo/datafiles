package com.lmml.datafiles.Filter;

public enum ComparatorOld {

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
	
	private interface IInnerComparator {
	
		Boolean get(String _a, String _b);

		Boolean get(Double _a, Double _b);
		
	}//End IInnerComparator

	private class EqualComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.equals(_b);
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return _a.equals(_b);
		}//End get

	}//End EqualComparator

	private class GreaterComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.compareTo(_b) > 0;
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return _a.compareTo(_b) > 0;
		}//End get

	}//End GreaterComparator

	private class GreaterEqualComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.compareTo(_b) >= 0;
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return _a.compareTo(_b) >= 0;
		}//End get

	}//End GreaterEqualComparator

	private class LessComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.compareTo(_b) < 0;
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return _a.compareTo(_b) < 0;
		}//End get

	}//End LessComparator

	private class LessEqualComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.compareTo(_b) <= 0;
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return _a.compareTo(_b) <= 0;
		}//End get

	}//End LessEqualComparator

	private class DifferentComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return !(_a.equals(_b));
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return !(_a.equals(_b));
		}//End get

	}//End DifferentComparator

	private class StartWithComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.startsWith(_b);
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End StartWithComparator

	private class EndsWithComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.endsWith(_b);
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End EndsWithComparator

	private class NotStartWithComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return !(_a.startsWith(_b));
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End NotStartWithComparator

	private class NotEndsWithComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return !(_a.endsWith(_b));
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End NotEndsWithComparator

	private class ContainsComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.contains(_b);
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End ContainsComparator

	private class NotContainsComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return !(_a.contains(_b));
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End NotContainsComparator

	private class RegexComparator implements IInnerComparator{

		@Override
		public Boolean get(String _a, String _b) {
			return _a.matches(_b);
		}//End get

		@Override
		public Boolean get(Double _a, Double _b) {
			return false;
		}//End get

	}//End RegexComparator

	private String mask;
	private boolean isNumeric;
	private IInnerComparator innerComparator;
	
	String getMask() {
		return mask;
	}//End getMask
	
	Boolean getIsNumeric() {
		return isNumeric;
	}//End getIsNumeric

	Boolean get(String _a, String _b){
		return innerComparator.get(_a, _b);
	}//End get

	Boolean get(Double _a, Double _b){
		return innerComparator.get(_a, _b);
	}//End get
	/*
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
	 */

	ComparatorOld(String _mask, Boolean _isNumeric){
		mask = _mask;
		isNumeric = _isNumeric;
		switch (mask) {
			case "==":
				innerComparator = new EqualComparator(); break;
			case ">":
				innerComparator = new GreaterComparator(); break;
			case ">=":
				innerComparator = new GreaterEqualComparator(); break;
			case "<":
				innerComparator = new LessComparator(); break;
			case "=<":
				innerComparator = new LessEqualComparator(); break;
			case "<>":
				innerComparator = new DifferentComparator(); break;
			case "Empieza por":
				innerComparator = new StartWithComparator(); break;
			case "No empieza por":
				innerComparator = new NotStartWithComparator(); break;
			case "Termina por":
				innerComparator = new EndsWithComparator(); break;
			case "No termina por":
				innerComparator = new NotEndsWithComparator(); break;
			case "Contiene":
				innerComparator = new ContainsComparator(); break;
			case "No contiene":
				innerComparator = new NotContainsComparator();  break;
			case "Expresión regular":
				innerComparator = new RegexComparator();  break;
		}//End switch	
	}//End Constructor

}//End  Comparator
