package com.lmml.datafiles.Explorer;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldsExtractor {

	private abstract class InnerFieldsExtractor{

		String separator;

        abstract String[] get(String _string);

    }//End InnerComparator

	private class InnerFieldsExtractorWithoutDelimiter extends InnerFieldsExtractor {

		InnerFieldsExtractorWithoutDelimiter(String _separator){
			separator = _separator;
		}//End Constructor
		
		@Override
		String[] get(String _string) {
			return _string.split(separator);
		}//End get

	}//End InnerFieldsExtractorWithoutDelimiter

	private class InnerFieldsExtractorWithDelimiter extends InnerFieldsExtractor {

		String delimiter;

		InnerFieldsExtractorWithDelimiter(String _separator, String _delimiter){
			separator = _separator;
			delimiter = _delimiter;
		}//End Constructor
		
		@Override
		String[] get(String _string) {
			ArrayList<String> listafinal = new ArrayList<String>();
			String linea_ampliada = separator + _string + separator;
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
		}//End get

	}//End InnerFieldsExtractorWithDelimiter


	private InnerFieldsExtractor innerFieldsExtractor;
	private String[] specialsStrings = {"|","\""};
    
    public String[] get(String _string){
		return innerFieldsExtractor.get(_string);
	}//End get

    private String sep_del_adapter(String _sd) {
		if (Arrays.asList(specialsStrings).contains(_sd)){
			return "\\" + _sd;
		}else {
			return _sd;
		}//End if
	}//End sep_del_adapter
    
    public FieldsExtractor(String _separator, String _delimiter){
		String adaptedDelimiter = sep_del_adapter(_delimiter);
		if (adaptedDelimiter.length()>0) {
			innerFieldsExtractor = new InnerFieldsExtractorWithDelimiter(sep_del_adapter(_separator), adaptedDelimiter);
		}else {
			innerFieldsExtractor = new InnerFieldsExtractorWithoutDelimiter(sep_del_adapter(_separator));
	}//End if
	}//End Constructor
	
	public FieldsExtractor(String _separator){
		innerFieldsExtractor = new InnerFieldsExtractorWithoutDelimiter(sep_del_adapter(_separator));
	}//End Constructor

}//End FileExtractor