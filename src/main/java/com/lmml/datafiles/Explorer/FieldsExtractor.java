package com.lmml.datafiles.Explorer;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class FieldsExtractor {

	private abstract class InnerFieldsExtractor{

		abstract String[] get(String _string);

    }//End InnerComparator

	private class InnerFieldsExtractorWithoutDelimiter extends InnerFieldsExtractor {

		char separator;

		InnerFieldsExtractorWithoutDelimiter(char _separator){
			separator = _separator;
		}//End Constructor
		
		@Override
		String[] get(String _string) {
			ArrayList<String> secuences = new ArrayList<String>();
			StringBuffer currentSecuence = new StringBuffer();
			for(char c: _string.toCharArray()){
				if (c==separator){
					secuences.add(currentSecuence.toString());
					currentSecuence = new StringBuffer();
				}else{
					currentSecuence.append(c);
				}//End if
			}//End for
			secuences.add(currentSecuence.toString());
			return secuences.toArray(new String[secuences.size()]);
		}//End get

	}//End InnerFieldsExtractorWithoutDelimiter

	private class InnerFieldsExtractorWithDelimiter extends InnerFieldsExtractor {

		char separator;
		char delimiter;

		InnerFieldsExtractorWithDelimiter(char _separator, char _delimiter){
			separator = _separator;
			delimiter = _delimiter;
		}//End Constructor
		
		@Override
		String[] get(String _string) {
			ArrayList<String> secuences = new ArrayList<String>();
			StringBuffer chars = new StringBuffer(separator);
			chars.append(_string);
			chars.append(separator);
			StringBuffer currentSecuence = new StringBuffer();
			char currentChar;
			boolean isDelimiterOpen = false;
			for(int i = 1; i<chars.length()-1; i++){
				currentChar = chars.charAt(i);
				if(currentChar==separator){
					if(isDelimiterOpen){
						currentSecuence.append(currentChar);
					}else{
						secuences.add(currentSecuence.toString());
						currentSecuence = new StringBuffer();
					}//End if
				}else if (currentChar==delimiter){
					if(chars.charAt(i-1)==separator){
						isDelimiterOpen = true;
					}else if (chars.charAt(i+1) == separator){
						isDelimiterOpen = false;
					}else{
						currentSecuence.append(currentChar);
					}//End if
				}else{
					currentSecuence.append(currentChar);
				}//End if
			}//End for
			secuences.add(currentSecuence.toString());
			return secuences.toArray(new String[secuences.size()]);
		}//End get

	}//End InnerFieldsExtractorWithDelimiter


	private InnerFieldsExtractor innerFieldsExtractor;
	
    public String[] get(String _string){
		return innerFieldsExtractor.get(_string);
	}//End get
    
    public FieldsExtractor(char _separator, char _delimiter){
		innerFieldsExtractor = new InnerFieldsExtractorWithDelimiter(_separator, _delimiter);
	}//End Constructor
	
	public FieldsExtractor(char _separator){
		innerFieldsExtractor = new InnerFieldsExtractorWithoutDelimiter(_separator);
	}//End Constructor

}//End FileExtractor