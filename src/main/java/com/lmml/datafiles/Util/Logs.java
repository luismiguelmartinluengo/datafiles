package com.lmml.datafiles.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs{

	public final static int NO_LOGS_LEVEL = 0;
    public final static int INFO_LEVEL = 1;
    public final static int WARNING_LEVEL = 2;
    public final static int CRITICAL_LEVEL = 3;
	private static File logFile = null;
    private static PrintWriter pw;
	private static int nivel_log = 0;
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss_SSS");
	private static int id_log = -1;
    private static boolean started = false;
	private static String logString = "";

    public static String lastLogLineFromFile(){
        /*Defino el método para poder testear que se está escribiendo la información
		 * correta en el fichero de logs
		 */
		if (logFile != null){
            try{
                BufferedReader br = new BufferedReader(new FileReader(logFile));
                String lastLine = null;
                String line = null;
                do {
                    lastLine = line;
                    line = br.readLine();
                } while (line != null);
                br.close();
                return lastLine;
            }catch (Exception e){
                return String.format("Error en la recuperación de última linea: %s",e.getMessage());
            }//End try
        }else{
            return "Aún no se ha definido fichero de logs";
        }//End if
    }//End lastLogLine

	public static String lastLogLine(){
		//Defino este método a efectos de test
		return logString;
	}//End lastLogLine

	private static void start(Integer _nivel_log) throws IOException {
        if (!started && _nivel_log> NO_LOGS_LEVEL){
            File logFolder = new File("Logs");
            if (!logFolder.isDirectory()) logFolder.mkdir();
            logFile = new File(String.format("./Logs/log_%s.log",LocalDateTime.now().format(dtf)));
            pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile)));
            nivel_log = _nivel_log;
            started = true;
        }//End if
	}//End start

	public static void changeLevel(int _newLevel){
		if (nivel_log != _newLevel){
			String changeMessage;
			String bounded = "-".repeat(10);
            if (_newLevel == NO_LOGS_LEVEL){
				changeMessage = String.format("%s LOGS DESACTIVADOS %s", bounded, bounded);
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
				changeMessage = String.format("%s ESTABLECIDO NIVEL DE LOGS: %s %s", bounded,((nivel_log>2)?"CRITICAL":(nivel_log>1)?"WARNING":"INFO"), bounded);
            }//End if
			pw.println(changeMessage);
			pw.flush();
			System.out.println(changeMessage);
			logString = changeMessage;
		}//End if
    }//End changeLevel

	public static boolean asInfo() {
		return nivel_log == INFO_LEVEL; 
	}//End asInfo
	
	public static boolean asWarning() {
		return nivel_log > NO_LOGS_LEVEL && nivel_log < CRITICAL_LEVEL;
	}//End asWarning

    public static boolean asCritical() {
        return nivel_log > NO_LOGS_LEVEL;
    }//End as Critical
	
	private static void escritura(StackTraceElement[] _array_stack_trace, String _nivel, int _id_mensaje, String _mensaje) {
		String hora = LocalDateTime.now().format(dtf);
		String nombre_clase = _array_stack_trace[1].getClassName();
		int hash_clase = _array_stack_trace[1].getClass().hashCode();
		String nombre_metodo = _array_stack_trace[1].getMethodName();
		logString = String.format("%s;%s;%d;%s;%s;%d;%d;%s", hora, nombre_clase, hash_clase, nombre_metodo, _nivel, id_log, _id_mensaje,_mensaje);
		pw.println(logString);
		pw.flush();
		System.out.println(logString);
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
	
	public static void warning(StackTraceElement[] _array_stack_trace, String _mensaje, Throwable _throwable) {
		if (asWarning()) {
			id_log++;
			escritura(_array_stack_trace, "WARNING", 0, _mensaje);
			escritura(_array_stack_trace, "WARNING", 1, _throwable.getMessage());
		}//End if
	}//End warning
	
	public static void warning(StackTraceElement[] _array_stack_trace, String[] _mensajes, Throwable _throwable) {
		if (asWarning()) {
			id_log++;
			int i;
			for (i = 0; i< _mensajes.length;i++) {
				escritura(_array_stack_trace, "WARNING", i, _mensajes[i]);
			}//End for
			escritura(_array_stack_trace, "WARNING", i, _throwable.getMessage());
		}//End if
	}//End warning
	
	public static void critical(StackTraceElement[] _array_stack_trace, String _mensaje, Throwable _throwable) {
		if (asCritical()){
            id_log++;
            escritura(_array_stack_trace, "CRITICO", 0, _mensaje);
            escritura(_array_stack_trace, "CRITICO", 1, _throwable.getMessage());
        }//End if
	}//End info
	
	public static void critical(StackTraceElement[] _array_stack_trace, String[] _mensajes, Throwable _throwable) {
		if (asCritical()){
            id_log++;
            int i;
            for (i = 0;i< _mensajes.length;i++) {
                escritura(_array_stack_trace, "CRITICO", i, _mensajes[i]);
            }//End for
            escritura(_array_stack_trace, "CRITICO", i, _throwable.getMessage());
        }//End if
	}//End info

	public static void close() {
		if (pw != null) {
			Logs.changeLevel(Logs.NO_LOGS_LEVEL);
			pw.flush();
			pw.close();
		}//End if
		started = false;
	}//End close

}//End Logs