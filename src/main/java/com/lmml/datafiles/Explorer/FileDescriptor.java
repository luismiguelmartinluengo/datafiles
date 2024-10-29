package com.lmml.datafiles.Explorer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.lmml.datafiles.Util.Logs;

public class FileDescriptor {

    private String name = "Sin nombre";
	private String path;
	private Character fieldsSeparator;
	private Character fieldsDelimiter = null;
	private String[] heads;
	private Integer skipLines = 0;
	private Integer numLines;
	
	void setName(String _name) {name = _name;}//End setname
	
	public String getName() {return name;}//End getname
	
	static String getRepositoryName(String _name) {
		return _name.trim().replaceAll(" ","_").replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
	}//End static getRepositoryName
	
	public String getRepositoryName() {
		return getRepositoryName(name);
	}//End getRepositoryName
	
	void setPath(String _path) {path = _path;}//End setpath
	
	String getPath() {return path;}//End getpath
	
	void setFieldsSeparator(Character _fieldsSeparator) {fieldsSeparator = _fieldsSeparator;}//End setFieldsSeparator
	
	Character getFieldsSeparator() {return fieldsSeparator;}//End getFieldsSeparator
	
	void setFieldsDelimiter(Character _fieldsDelimiter) {fieldsDelimiter = _fieldsDelimiter;}//End setFieldsDelimiter
	
	Character getFieldsDelimiter() {return fieldsDelimiter;}//End getFieldsDelimiter
	
	void setHeads(String[] _heads) {heads = _heads;}//End setheads
	
	String[] getHeads(){return heads;}//End getheads
	
	void setSkipLines(Integer _skipLines) {skipLines = _skipLines;}//End setSkipLines
	
	Integer getSkipLines() {return skipLines;}//End Integer
	
	void setNumLines(Integer _numLines) {numLines = _numLines;}//End setNumLines
	
	Integer getNumLines() {return numLines;}//End getNumLines
	
	String asJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}//End asJson
	
	FileDescriptor copy() {
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(this), FileDescriptor.class);
	}//End copy
	
	FieldsExtractor getFieldsExtractor() {
		FieldsExtractor returnValue;
		if (fieldsDelimiter != null) {
			returnValue = new FieldsExtractor(fieldsSeparator, fieldsDelimiter);
		}else {
			returnValue = new FieldsExtractor(fieldsSeparator);
		}//End if
		return returnValue;
	}//End getFieldsExtractor
	
	synchronized BufferedReader getReader() throws IOException {
		BufferedReader returnValue = new BufferedReader(new FileReader (new File (path)));
		if (skipLines > 0) {
			for(int i = 0; i < skipLines; i++) {
				returnValue.readLine();
			}//End for
		}//End if
		return returnValue;
	}//End getReader
	
	String getFileName() {
		try {
			File file = new File(path);
			return file.getName();
		}catch (Exception e) {
			if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("No se puede recuperar el name del fichero de la path: %s", path), e);}
			return "desconocido";
		}//End try
	}//End getFileName
	
	boolean CompareToAbsolute(FileDescriptor _otro) {
		int compareValue = 0;
		boolean endsComparation = false;
		int comparationIndex = 0;
		do{
			switch(comparationIndex){
			case 0:
				compareValue = this.getName().compareTo(_otro.getName());
				break;
			case 1:
				compareValue = this.getPath().compareTo(_otro.getPath());
				break;
			case 2:
				compareValue = this.getFieldsDelimiter().compareTo(_otro.getFieldsDelimiter());
				break;
			case 3:
				compareValue = this.getFieldsSeparator().compareTo(_otro.getFieldsSeparator());
				break;
			case 4:
				compareValue = (Arrays.equals(this.getHeads(),_otro.getHeads()))?0:1;
				break;
			case 5:
				compareValue = this.getSkipLines().compareTo(_otro.getSkipLines());
				break;
			default:
				endsComparation = true;
			}//End wwitch
			comparationIndex++;
		}while ((compareValue == 0 )&& (endsComparation == false));
		return compareValue == 0;
	}//End CompareToAbsolute
	
	public FileDescriptor(String _path, Character _fieldsSeparator, Character _fieldsDelimiter) throws IOException{
		path = _path;
		fieldsSeparator = _fieldsSeparator;
		fieldsDelimiter = _fieldsDelimiter;
		FieldsExtractor fe = getFieldsExtractor();
		BufferedReader br = getReader();
		heads = fe.get(br.readLine());
		br.close();
		skipLines = 1;
	}//End Constructor
	
	public FileDescriptor(String _path, Character _fieldsSeparator, Character _fieldsDelimiter, String[] _heads, Integer _skipLines){
		path = _path;
		fieldsSeparator = _fieldsSeparator;
		fieldsDelimiter = _fieldsDelimiter;
		heads = _heads;
		skipLines = _skipLines;
	}//End Constructor

	//implements comparable
	public int compareTo(FileDescriptor _otro) {
		return this.toString().compareTo(_otro.toString());
	}//End compareTo

	@Override
	public String toString() {
		return name;
	}//End toString

}//End FileDescriptor
