package com.lmml.datafiles.Util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LogTest {


    @Test
    public void testLastLogLine() {
        String testMessage = "testLastLogLine";
        Logs.changeLevel(Logs.INFO_LEVEL);
        Logs.info(Thread.currentThread().getStackTrace(), testMessage);
        String classLastLogLine = Logs.lastLogLine();
        String fileLastLogLine = Logs.lastLogLineFromFile();
        assertTrue(classLastLogLine.endsWith(testMessage));
        assertTrue(fileLastLogLine.endsWith(testMessage));
        assertEquals(classLastLogLine, fileLastLogLine);
    }//End test

    @Test
    public void testChangeLevelToNoLogsLevel() {
        String logMessage = "No log message";
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        Logs.critical(Thread.currentThread().getStackTrace(), logMessage, new Throwable());
        String classLastLogLine = Logs.lastLogLine();
        String fileLastLogLine = Logs.lastLogLineFromFile();
        assertFalse(Logs.asCritical());
        assertFalse(classLastLogLine.endsWith(logMessage));
        assertFalse(fileLastLogLine.endsWith(logMessage));
        assertEquals(classLastLogLine, fileLastLogLine);
    }//End test

    @Test
    public void testChangeLevelToInfoLevel() {
        String logMessage = "Info message";
        Logs.changeLevel(Logs.INFO_LEVEL);
        Logs.info(Thread.currentThread().getStackTrace(), logMessage);
        String classLastLogLine = Logs.lastLogLine();
        String fileLastLogLine = Logs.lastLogLineFromFile();
        assertTrue(Logs.asInfo());
        assertTrue(classLastLogLine.endsWith(logMessage));
        assertTrue(fileLastLogLine.endsWith(logMessage));
        assertEquals(classLastLogLine, fileLastLogLine);
    }//End test

    @Test
    public void testChangeLevelToWarningLevel() {
        String logMessage = "Warning message";
        Logs.changeLevel(Logs.WARNING_LEVEL);
        Logs.warning(Thread.currentThread().getStackTrace(), logMessage);
        String classLastLogLine = Logs.lastLogLine();
        String fileLastLogLine = Logs.lastLogLineFromFile();
        assertTrue(Logs.asWarning());
        assertTrue(classLastLogLine.endsWith(logMessage));
        assertTrue(fileLastLogLine.endsWith(logMessage));
        assertEquals(classLastLogLine, fileLastLogLine);
    }//End test

    @Test
    public void testChangeLevelToCriticalLevel() {
        String logMessage = "Critical message";
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        Logs.critical(Thread.currentThread().getStackTrace(), logMessage, new Throwable());
        String classLastLogLine = Logs.lastLogLine();
        String fileLastLogLine = Logs.lastLogLineFromFile();
        assertTrue(Logs.asCritical());
        assertTrue(classLastLogLine.matches(".*CRITICO.*null"));
        assertEquals(classLastLogLine, fileLastLogLine);
    }//End test

    @Test
    public void testAsInfo() {
        Logs.changeLevel(Logs.INFO_LEVEL);
        assertTrue(Logs.asInfo());
        assertTrue(Logs.asWarning());
        assertTrue(Logs.asCritical());
    }//End test

    @Test
    public void testAsWarning() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        assertFalse(Logs.asInfo());
        assertTrue(Logs.asWarning());
        assertTrue(Logs.asCritical());
    }//End test

    @Test
    public void testAsCritical() {
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        assertFalse(Logs.asInfo());
        assertFalse(Logs.asWarning());
        assertTrue(Logs.asCritical());
    }//End test

    @Test
    public void testAsNoLogsLevel() {
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        assertFalse(Logs.asInfo());
        assertFalse(Logs.asWarning());
        assertFalse(Logs.asCritical());
    }//End test

    @Test
    public void testInfoOneLineOnNoLogsLevelResultNoLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL); //para iniciar logs y que haya una primera linea con la que comparar
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testInfoOneLineOnNoLogsLevel";
        Logs.info(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
    }//End test

    @Test
    public void testInfoOneLineOnInfoLevelResultLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL); 
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testInfoOneLineOnInfoLevel";
        Logs.info(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(infoMessage));
    }//End test

    @Test
    public void testInfoOneLineOnWarningLevelResultLogNotIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testInfoOneLineOnWarningLevel";
        Logs.info(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
        assertFalse(lastLogLine.endsWith(infoMessage));
    }//End test

    @Test
    public void testInfoOneLineOnCritialLevelResultLogNotIncluded() {
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testInfoOneLineOnCritialLevel";
        Logs.info(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
        assertFalse(lastLogLine.endsWith(infoMessage));
    }//End test

    @Test
    public void testInfoTowLinesOnInfoLevelResultLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String[] infoMessages = {"infoMessage_1_testInfoTowLinesOnInfoLevel","infoMessage_2_testInfoTowLinesOnInfoLevel"};
        Logs.info(Thread.currentThread().getStackTrace(), infoMessages);
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(infoMessages[1]));
    }//End test

    @Test
    public void testWarningOneLineOnNoLogsLevelResultNoLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL);
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testWarningOneLineOnNoLogsLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
        assertFalse(lastLogLine.endsWith(infoMessage));
    }//End test
    
    @Test
    public void testWarningOneLineOnInfoLevelResultLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testWarningOneLineOnInfoLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(infoMessage));
    }//End test
    
    @Test
    public void testWarningOneLineOnWarningLevelResultLogIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testWarningOneLineOnWarningLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(infoMessage));
    }//End test

    @Test
    public void testWarningOneLineOnCritialLevelResultLogNotIncluded() {
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testWarningOneLineOnCritialLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessage);
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
        assertFalse(lastLogLine.endsWith(infoMessage));
    }//End test

    @Test
    public void testWarningOneLineWithExceptionOnWarningLevelResultLogIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testWarningOneLineWithExceptionOnWarningLevel";
        String errorMessage = "errorMessage_testWarningOneLineWithExceptionOnWarningLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessage, new Throwable(errorMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(errorMessage));
    }//End test

    @Test
    public void testWarningTwoLinesOnWarningLevelResultLogIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String[] infoMessages = {"infoMessage_0_testWarningTwoLinesOnWarningLevel","infoMessage_1_testWarningTwoLinesOnWarningLevel"};
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessages);
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(infoMessages[1]));
    }//End test

    @Test
    public void testWarningTwoLinesWithExceptionOnWarningLevelResultLogIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String[] infoMessages = {"infoMessage_0_testWarningTwoLinesWithExceptionOnWarningLevel","infoMessage_1_testWarningTwoLinesWithExceptionOnWarningLevel"};
        String exceptionMessage = "exceptionMessage_testWarningTwoLinesWithExceptionOnWarningLevel";
        Logs.warning(Thread.currentThread().getStackTrace(), infoMessages, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(exceptionMessage));
        assertTrue(lastLogLine.contains(";2;")); //2 (0 y 1 son los índices de los mensajes y 2 es el índice que le corresponde al mensaje de error) es el índice de mensaje que se incluye con la excepción
    }//End test

    @Test
    public void testCriticalOneLineOnNoLogsLevelResultNoLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL); //to start
        Logs.changeLevel(Logs.NO_LOGS_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testCriticalOneLineOnNoLogsLevel";
        String exceptionMessage = "exceptionMessage_testCriticalOneLineOnNoLogsLevel";
        Logs.critical(Thread.currentThread().getStackTrace(), infoMessage, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertEquals(previousLogLine, lastLogLine);
        assertFalse(lastLogLine.endsWith(exceptionMessage));
        assertFalse(lastLogLine.contains(";1;"));
    }//End test

    @Test
    public void testCriticalOneLineOnInfoLevelResultLogIncluded() {
        Logs.changeLevel(Logs.INFO_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testCriticalOneLineOnInfoLevel";
        String exceptionMessage = "exceptionMessage_testCriticalOneLineOnInfoLevel";
        Logs.critical(Thread.currentThread().getStackTrace(), infoMessage, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(exceptionMessage));
        assertTrue(lastLogLine.contains(";1;"));
    }//End test

    @Test
    public void testCriticalOneLineOnWarningLevelResultLogIncluded() {
        Logs.changeLevel(Logs.WARNING_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testCriticalOneLineOnWarningLevel";
        String exceptionMessage = "exceptionMessage_testCriticalOneLineOnWarningLevel";
        Logs.critical(Thread.currentThread().getStackTrace(), infoMessage, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(exceptionMessage));
        assertTrue(lastLogLine.contains(";1;"));
    }//End test

    @Test
    public void testCriticalOneLineOnCriticalLevelResultLogIncluded() {
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String infoMessage = "infoMessage_testCriticalOneLineOnCriticalLevel";
        String exceptionMessage = "exceptionMessage_testCriticalOneLineOnCriticalLevel";
        Logs.critical(Thread.currentThread().getStackTrace(), infoMessage, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(exceptionMessage));
        assertTrue(lastLogLine.contains(";1;"));
    }//End test

    @Test
    public void testCriticalTwoLinesOnCriticalLevelResultLogIncluded() {
        Logs.changeLevel(Logs.CRITICAL_LEVEL);
        String previousLogLine = Logs.lastLogLine();
        String[] infoMessages = {"infoMessage_0_testCriticalTwoLinesOnCriticalLevel","infoMessage_1_testCriticalTwoLinesOnCriticalLevel"};
        String exceptionMessage = "exceptionMessage_testCriticalOneLineOnCriticalLevel";
        Logs.critical(Thread.currentThread().getStackTrace(), infoMessages, new Throwable(exceptionMessage));
        String lastLogLine = Logs.lastLogLine();
        assertNotEquals(previousLogLine, lastLogLine);
        assertTrue(lastLogLine.endsWith(exceptionMessage));
        assertTrue(lastLogLine.contains(";2;"));
    }//End test





}//End LogTest
