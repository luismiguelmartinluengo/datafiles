package com.lmml.datafiles.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs implements AutoCloseable{

	public final static int NO_LOGS_LEVEL = 0;
    public final static int INFO_LEVEL = 1;
    public final static int WARNING_LEVEL = 2;
    public final static int CRITICAL_LEVEL = 3;
    private static PrintWriter pw;
	private static int nivel_log = 3;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss_SSS");
	private static int id_log = -1;
    private static boolean started = false;
	
	public static void start(Integer _nivel_log) throws IOException {
        if (!started && _nivel_log> NO_LOGS_LEVEL){
            File archivo = new File("Logs");
            if (!archivo.isDirectory()) {archivo.mkdir();}//End if
            archivo = new File(String.format("./Logs/log_%s.csv",LocalDateTime.now().format(dtf)));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
            nivel_log = _nivel_log;
            started = true;
        }//End if
	}//End start

	public static void changeLevel(int _newLevel){
		String changeMessage;
        if (nivel_log != _newLevel){
            if (_newLevel == NO_LOGS_LEVEL){
				changeMessage = String.format("{0} LOGS DESACTIVADOS {0}", "-".repeat(10));
                nivel_log = NO_LOGS_LEVEL;
            }else{
                if (!started){
					try{
						start(_newLevel);
					}catch(IOException e){
						System.out.println("Error en el inicio de escritura de logs");
						System.out.println(e.getMessage());
					}//End try
                }else{
                    nivel_log = _newLevel;
                }//End if
				changeMessage = String.format("{0} ESTABLECIDO NIVEL DE LOGS: {1} {0}", "-".repeat(10),(nivel_log>2)?"CRITICAL":(nivel_log>1)?"WARNING":"INFO");
            }//End if
			pw.println(changeMessage);
			pw.flush();
			System.out.println(changeMessage);
        }//End if
    }//End changeLevel

	public static boolean asInfo() {
		return nivel_log > NO_LOGS_LEVEL; 
	}//End asInfo
	
	public static boolean asWarning() {
		return nivel_log > INFO_LEVEL;
	}//End asWarning

    public static boolean asCritical() {
        return nivel_log > WARNING_LEVEL;
    }//End as Critical
	
	private static void escritura(StackTraceElement[] _array_stack_trace, String _nivel, int _id_mensaje, String _mensaje) {
		String hora = LocalDateTime.now().format(dtf);
		String nombre_clase = _array_stack_trace[1].getClassName();
		int hash_clase = _array_stack_trace[1].getClass().hashCode();
		String nombre_metodo = _array_stack_trace[1].getMethodName();
		String cadena = String.format("%s;%s;%d;%s;%s;%d;%d;%s", hora, nombre_clase, hash_clase, nombre_metodo, _nivel, id_log, _id_mensaje,_mensaje);
		pw.println(cadena);
		pw.flush();
		System.out.println(cadena);
	}//End if
	
	public static void info(StackTraceElement[] _array_stack_trace, String _mensaje) {
		if (asInfo()) {
			id_log++;
			escritura(_array_stack_trace, "INFO", 0, _mensaje);
		}//End if
	}//End info
	
	public static void info(StackTraceElement[] _array_stack_trace, String[] _mensajes) {
		if (asInfo()) {
			id_log++;
			for(int i = 0; i < _mensajes.length; i++) {
				escritura(_array_stack_trace, "INFO", i, _mensajes[i]);
			}//End for
		}//End if
	}//End info
	
	public static void warning(StackTraceElement[] _array_stack_trace, String _mensaje) {
		if (asWarning()) {
			id_log++;
			escritura(_array_stack_trace, "WARNING", 0, _mensaje);
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String[] _mensajes) {
		if (asWarning()) {
			id_log++;
			for (int i = 0; i< _mensajes.length; i++) {
				escritura(_array_stack_trace, "WARNING", i, _mensajes[i]);
			}//end for
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String _mensaje, Exception _exc) {
		if (asWarning()) {
			id_log++;
			escritura(_array_stack_trace, "WARNING", 0, _mensaje);
			escritura(_array_stack_trace, "WARNING", 1, _exc.getMessage());
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String[] _mensajes, Exception _exc) {
		if (asWarning()) {
			id_log++;
			int i;
			for (i = 0; i< _mensajes.length;i++) {
				escritura(_array_stack_trace, "WARNING", i, _mensajes[i]);
			}//End for
			i++;
			escritura(_array_stack_trace, "WARNING", i, _exc.getMessage());
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String _mensaje, Error _err) {
		if (asWarning()) {
			id_log++;
			escritura(_array_stack_trace, "WARNING", 0, _mensaje);
			escritura(_array_stack_trace, "WARNING", 1, _err.getMessage());
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String[] _mensajes, Error _err) {
		if (asWarning()) {
			id_log++;
			int i;
			for (i = 0; i< _mensajes.length;i++) {
				escritura(_array_stack_trace, "WARNING", i, _mensajes[i]);
			}//End for
			i++;
			escritura(_array_stack_trace, "WARNING", i, _err.getMessage());
		}//End if
	}//End warning
	
	public static void critical(StackTraceElement[] _array_stack_trace, String _mensaje, Exception _exc) {
		if (asCritical()){
            id_log++;
            escritura(_array_stack_trace, "CRITICO", 0, _mensaje);
            escritura(_array_stack_trace, "CRITICO", 1, _exc.getMessage());
        }//End if
	}//End info
	
	public static void critical(StackTraceElement[] _array_stack_trace, String[] _mensajes, Exception _exc) {
		if (asCritical()){
            id_log++;
            int i;
            for (i = 0;i< _mensajes.length;i++) {
                escritura(_array_stack_trace, "CRITICO", i, _mensajes[i]);
            }//End for
            i++;
            escritura(_array_stack_trace, "CRITICO", i, _exc.getMessage());
        }//End if
	}//End info
	
	public static void critical(StackTraceElement[] _array_stack_trace, String _mensaje, Error _err) {
		if (asCritical()){
            id_log++;
            escritura(_array_stack_trace, "CRITICO", 0, _mensaje);
            escritura(_array_stack_trace, "CRITICO", 1, _err.getMessage());
        }//End if
    }//End info
	
	public static void critical(StackTraceElement[] _array_stack_trace, String[] _mensajes, Error _err) {
		if(asCritical()){
            id_log++;
            int i;
            for (i = 0;i< _mensajes.length; i++) {
                escritura(_array_stack_trace, "CRITICO", i, _mensajes[i]);
            }//End for
            i++;
            escritura(_array_stack_trace, "CRITICO", i, _err.getMessage());
        }//End if
	}//End info
	
    @Override
	public void close() throws Exception {
		if (pw != null) {
			pw.flush();
			pw.close();
		}//End if
	}//End finalize

}