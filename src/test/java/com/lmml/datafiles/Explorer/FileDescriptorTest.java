package com.lmml.datafiles.Explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FileDescriptorTest {

    private FileDescriptor fileDescriptor;
    private FileDescriptor otherFileDescriptor;

    @BeforeEach
    public void createFileDescriptors(){
        String filePath = "./TestResources/TableFile.txt";
        Character separator = ';';
        Character delimiter = '\"';
        String[] fields = {"Campo1","Campo2","Campo3","Campo4","Campo5"};
        int skipLines = 1;
        fileDescriptor = new FileDescriptor(filePath ,separator, delimiter, fields, skipLines);
        try {
            otherFileDescriptor = new FileDescriptor(filePath, separator, delimiter);
        } catch (IOException e) {
            e.printStackTrace();
        }//End try
    }//End createFiledDescriptors

    @Test
    public void testCompareToReturnsTrue(){
        assertTrue(fileDescriptor.compareTo(otherFileDescriptor)==0);
    }//End test

    @Test
    public void testCompareToReturnsFalse(){
        otherFileDescriptor.setName("Otro nombre");
        assertTrue(fileDescriptor.compareTo(otherFileDescriptor)!=0);
    }//End test

    @Test
    public void testCompareToAbsoluteReturnTrue(){
        assertTrue(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseByName(){
        otherFileDescriptor.setName("Otro nombre");
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseByPath(){
        otherFileDescriptor.setPath("./Logs/Logs.txt");
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseBySeparator(){
        otherFileDescriptor.setFieldsSeparator(',');
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseByDelimiter(){
        otherFileDescriptor.setFieldsDelimiter(',');
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseByFields(){
        otherFileDescriptor.setHeads(new String[]{"CampoA","CampoB","CampoC"});
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testCompareToAbsoluteReturnFalseBySkipLines(){
        otherFileDescriptor.setSkipLines(5);
        assertFalse(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testGetFileNameReturnNewName(){
        fileDescriptor.setPath("./TestResources/NewName.txt");
        String expectedResult = "NewName.txt";
        String result = fileDescriptor.getFileName();
        assertEquals(expectedResult, result);
    }//End test

    @Test
    public void testGetReaderReturnsBufferedReader() throws IOException {
        try (BufferedReader reader = fileDescriptor.getReader()) {
            assertNotNull(reader);
            assertTrue(reader.ready());
        }//End try
    }//End test

    @Test
    public void testGetReaderSkipsLines() throws IOException {
        try (BufferedReader reader = fileDescriptor.getReader()) {
            String lineExpected = "valor1_1;valor1_2;valor1_3;valor1_4;valor1_5";
            String lineResult = reader.readLine();
            assertEquals(lineExpected, lineResult);
        }//End try
    }//Ent test

    @Test
    public void testGetReaderHandlesIOException() {
        fileDescriptor.setPath("./TestResources/FicheroNoExiste.text");
        assertThrows(IOException.class, () -> {fileDescriptor.getReader();});
    }//End test

    @Test
    public void testGetFieldsExtractorWithDelimiter() {
        FieldsExtractor fieldsExtractor = fileDescriptor.getFieldsExtractor();
        String testLine = "\"a\";\"b\""; 
        String[] expedtedExtractedValues = {"a","b"};
        String[] resultExtractedValues = fieldsExtractor.get(testLine);
        assertNotNull(fieldsExtractor);
        assertTrue(Arrays.equals(expedtedExtractedValues, resultExtractedValues));
        // Aquí puedes agregar más aserciones específicas para FieldsExtractor
    }//End Test

    @Test
    public void testGetFieldsExtractorWithoutDelimiter() {
        fileDescriptor.setFieldsDelimiter(null);
        FieldsExtractor fieldsExtractor = fileDescriptor.getFieldsExtractor();
        String testLine = "a;b"; 
        String[] expedtedExtractedValues = {"a","b"};
        String[] resultExtractedValues = fieldsExtractor.get(testLine);
        assertNotNull(fieldsExtractor);
        assertTrue(Arrays.equals(expedtedExtractedValues, resultExtractedValues));
    }//End test

    @Test 
    public void testStaticGetRepositoryName() {
        String paramFileDescriptorName = "Test nAme-n*a@m$e";
        String expectedRepoName = "test_name-name"; 
        String resultRepoName = FileDescriptor.getRepositoryName(paramFileDescriptorName);
        assertEquals(expectedRepoName, resultRepoName);
    }//End test

    @Test 
    public void testInstanceGetRepositoryName() {
        String paramFileDescriptoName = "Test nAme-n*a@m$e";
        fileDescriptor.setName(paramFileDescriptoName);
        String expectedRepoName = "test_name-name"; 
        String resultRepoName = fileDescriptor.getRepositoryName();
        assertEquals(expectedRepoName, resultRepoName);
    }//End test

    @Test 
    public void testSetNumLinesAndGetNumLines() {
        int newNumLines = 500;
        fileDescriptor.setNumLines(newNumLines);
        int result = fileDescriptor.getNumLines();
        assertEquals(newNumLines, result);
    }//End test

    @Test
    public void testCopyReturnIdenticalFileDescriptor(){
        otherFileDescriptor = fileDescriptor.copy();
        assertTrue(fileDescriptor.CompareToAbsolute(otherFileDescriptor));
    }//End test

    @Test
    public void testAsJsonReturnValidatedJson(){
        String fileDescriptorJson = fileDescriptor.asJson();
        JsonElement jsonElement = JsonParser.parseString(fileDescriptorJson);
        assertTrue(jsonElement.isJsonObject() || jsonElement.isJsonArray());
    }//End test

}//End FileDescriptorTest