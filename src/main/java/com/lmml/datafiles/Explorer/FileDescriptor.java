package com.lmml.datafiles.Explorer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.google.gson.Gson;
import com.lmml.datafiles.Util.Logs;

public class FileDescriptor {

    private String nombre = "Sin nombre";
	private String ruta;
	private String separador_campos;
	private String delimitador_campos;
	private ArrayList<String> encabezados;
	private Integer saltar_lineas = 0;
	private Integer num_lineas;
	private LinkedHashSet<AutoEditableListener> auto_editable_listeners = new LinkedHashSet<AutoEditableListener>();
	
	void setNombre(String _nombre) {nombre = _nombre;}//End setNombre
	
	public String getNombre() {return nombre;}//End getNombre
	
	static String getNombreRepositorio(String _nombre) {
		return _nombre.trim().replaceAll(" ","_").replaceAll("[^a-zA-Z0-9_]", "").toLowerCase();
	}//End static getNombreRepositorio
	
	public String getNombreRepositorio() {
		return getNombreRepositorio(nombre);
	}//End getNombreRepositorio
	
	void setRuta(String _ruta) {ruta = _ruta;}//End setRuta
	
	String getRuta() {return ruta;}//End getRuta
	
	void setSeparadorCampos(String _separador_campos) {separador_campos = _separador_campos;}//End setSeparadorCampos
	
	String getSeparadorCampos() {return separador_campos;}//End getSeparadorCampos
	
	void setDelimitadorCampos(String _delimitador_campos) {delimitador_campos = _delimitador_campos;}//End setDelimitadorCampos
	
	String getDelimitadorCampos() {return delimitador_campos;}//End getDelimitadorCampos
	
	void setEncabezados(ArrayList <String> _encabezados) {encabezados = _encabezados;}//End setEncabezados
	
	ArrayList<String> getEncabezados(){return encabezados;}//End getEncabezados
	
	void setSaltarLineas(Integer _saltar_lineas) {saltar_lineas = _saltar_lineas;}//End setSaltarLineas
	
	Integer getSaltarLineas() {return saltar_lineas;}//End Integer
	
	void setNumLineas(Integer _num_lineas) {num_lineas = _num_lineas;}//End setNumLineas
	
	Integer getNumLineas() {return num_lineas;}//End getNumLineas
	
	String asJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}//End asJson
	
	FileDescriptor copy() {
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(this), FileDescriptor.class);
	}//End copy
	
	FieldsExtractor getFieldsExtractor() {
		try {
			FieldsExtractor vreturn;
			if (delimitador_campos != null) {
				vreturn = new FieldsExtractor(separador_campos, delimitador_campos);
			}else {
				vreturn = new FieldsExtractor(separador_campos);
			}//End if
			return vreturn;
		}catch (Error e) {
			Logs.warning(Thread.currentThread().getStackTrace(), String.format("Error indeterminado en la obtencion de un extractor de campos con separador '%s' y delimitador '%s'", separador_campos, delimitador_campos), e);
			return null;
		}//End try
	}//End getFieldsExtractor
	

	synchronized BufferedReader getFicheroLectura() throws IOException {
		BufferedReader vreturn = new BufferedReader(new FileReader (new File (ruta)));
		if (saltar_lineas > 0) {
			for(int i = 0; i < saltar_lineas; i++) {
				vreturn.readLine();
			}//End for
		}//End if
		return vreturn;
	}//End getFicheroLectura
	
	String getNombreFichero() {
		try {
			File archivo = new File(ruta);
			return archivo.getName();
		}catch (Exception e) {
			if (Logs.asWarning()) {Logs.warning(Thread.currentThread().getStackTrace(), String.format("No se puede recuperar el nombre del fichero de la ruta: %s", ruta), e);}
			return "desconocido";
		}//End try
	}//End getNombreFichero
	
	boolean isIdentico(FileDescriptor _otro) {
		int valor_comparacion = 0;
		valor_comparacion = Math.abs(this.getNombre().compareTo(_otro.getNombre()));
		valor_comparacion = valor_comparacion + Math.abs(this.getRuta().compareTo(_otro.getRuta()));
		valor_comparacion = valor_comparacion + Math.abs(this.getDelimitadorCampos().compareTo(_otro.getDelimitadorCampos()));
		valor_comparacion = valor_comparacion + Math.abs(this.getSeparadorCampos().compareTo(_otro.getSeparadorCampos()));
		valor_comparacion = valor_comparacion + ((this.getEncabezados().equals(_otro.getEncabezados()))?0:1);
		valor_comparacion = valor_comparacion + Math.abs(this.getSaltarLineas().compareTo(_otro.getSaltarLineas()));
		return (valor_comparacion > 0)?false:true;
	}//End isIdentico
	
	public FileDescriptor(String _ruta, String _separador_campos, String _delimitador_campos) throws IOException{
		ruta = _ruta;
		separador_campos = _separador_campos;
		delimitador_campos = _delimitador_campos;
		FieldsExtractor e = getFieldsExtractor();
		BufferedReader r = getFicheroLectura();
		encabezados = e.get(r.readLine());
		r.close();
		saltar_lineas = 1;
	}//End Constructor
	
	public FileDescriptor(String _ruta, String _separador_campos, String _delimitador_campos, ArrayList<String> _encabezados, Integer _saltar_lineas){
		ruta = _ruta;
		separador_campos = _separador_campos;
		delimitador_campos = _delimitador_campos;
		encabezados = _encabezados;
		saltar_lineas = _saltar_lineas;
	}//End Constructor

	//implements comparable
	public int compareTo(DescriptorFichero _otro) {
		return this.toString().compareTo(_otro.toString());
	}//End compareTo

	@Override
	public String toString() {
		return nombre;
	}//End toString


}//End FileDescriptor
