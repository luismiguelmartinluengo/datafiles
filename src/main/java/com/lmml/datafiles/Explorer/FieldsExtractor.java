package com.lmml.datafiles.Explorer;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldsExtractor {

    private String separator;
	private String delimiter;
    private Boolean withDelimiter = false;
	private String[] specialsStrings = {"|","\""};

    private String[] getWithoutDelimiter(String _cadena){
		return _cadena.split(separator);
	};//End 
    
    private String[] getWithDelimiter(String _cadena){
		ArrayList<String> listafinal = new ArrayList<String>();
		String linea_ampliada = separator + _cadena + separator;
		String[] listadel = linea_ampliada.split(delimiter);
		for (int i = 0;i<listadel.length;i++) {
			if (!listadel[i].equals(separator)) {
				String valordel = listadel[i];
				if (valordel.startsWith(separator) & valordel.endsWith(separator)) {
					valordel = valordel.substring(1, valordel.length()-1);
					String[] listasep = valordel.split(separator);
					for (int j=0;j<listasep.length;j++ ) {
						listafinal.add(listasep[j]);
					}//End for
				}else {
					listafinal.add(listadel[i]);
				}//End if
			}//End if
		}//End for
		return listafinal.toArray(new String[listafinal.size()]);
	}//End getWithDelimiter

    public String[] get(String _cadena){
		if (!withDelimiter) {
			return getWithoutDelimiter(_cadena);
		}else {
			return getWithDelimiter(_cadena);
		}//End if
	}//End get

    private String sep_del_adapter(String _sd) {
		ArrayList<String> specials = new ArrayList<String>(Arrays.asList(specialsStrings));
		/*No debería hacer falta crear el ArrayList. Se debería poder evaluar así:
		Arrays.AsList(specials).contains(_sd)*/
		if (specials.contains(_sd)) {
			return "\\" + _sd;
		}else {
			return _sd;
		}//End if
	}//End sep_del_adapter
    
    public FieldsExtractor(String _separator, String _delimitador){
		separator = sep_del_adapter(_separator);
		if (_delimitador.length()>0) {
			delimiter = sep_del_adapter(_delimitador);
			withDelimiter = true;
		}else {
			withDelimiter = false;
		}//End if
	}//End Constructor
	
	public FieldsExtractor(String _separator){
		separator = sep_del_adapter(_separator);
		withDelimiter = false;
	}//End Constructor

}//End FileExtractor