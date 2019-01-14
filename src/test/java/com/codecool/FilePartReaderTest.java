package com.codecool;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {

    @Test
    void testSetupToLineLTFromLine() {
        FilePartReader reader = new FilePartReader();
        int fromLine = 2;
        int toLine = 1;
        assertThrows(IllegalArgumentException.class, () ->
        {reader.setup("filePath", fromLine, toLine);});
    }

    @Test
    void testSetupFromLineLT1() {
        FilePartReader reader = new FilePartReader();
        int fromLine = 0;
        int toLine = 100;
        assertThrows(IllegalArgumentException.class, () ->
        {reader.setup("filePath", fromLine, toLine);});
    }

    @Test
    void IOExceptionWhenNoFileReaded() {
        int fromLine = 1;
        int toLine = 2;
        FilePartReader reader = new FilePartReader("anything/resources/default.txt", fromLine, toLine);
        assertThrows(IOException.class, () -> {reader.readLines();});
    }


    @Test
    void testReadLines1_2() {
        int fromLine = 1;
        int toLine = 2;
        FilePartReader reader = new FilePartReader("src/main/resources/default.txt", fromLine, toLine);
        String expectedResult = "hejo co tam"+"\n"
                +"baooab";
        String fileContent = "";
        try {
            fileContent = reader.readLines();
        } catch (IOException e) {}

        assertEquals(expectedResult, fileContent);
    }

    @Test
    void testReadLines2_4() {
        int fromLine = 2;
        int toLine = 4;
        FilePartReader reader = new FilePartReader("src/main/resources/test_data.txt", fromLine, toLine);
        String expectedResult = "2b 2a\n" +
                "3c 3b 3a\n" +
                "4d 4cr 4bb4 4a";
        String fileContent = "";
        try {
            fileContent = reader.readLines();
        } catch (IOException e) {}

        assertEquals(expectedResult, fileContent);

    }

    @Test
    void testReadLinesAll() {
        FilePartReader reader = new FilePartReader();
        String expectedResult = "hejo co tam\n" +
                "baooab";
        String fileContent = "";
        try {
            fileContent = reader.readLines();
        } catch (IOException e) {}

        assertEquals(expectedResult, fileContent);
    }

    @Test
    void testReadLinesPastEof() {
        int fromLine = 1;
        int toLine = 20;
        FilePartReader reader = new FilePartReader("src/main/resources/test_data.txt", fromLine, toLine);
        String expectedResult = "1a1\n" +
                "2b 2a\n" +
                "3c 3b 3a\n" +
                "4d 4cr 4bb4 4a\n" +
                "5e 5d 5c 5b 5ax\n" +
                "6f 6ea 6d 6ca 6bb 6a\n" +
                "7g 7f 7ea";
        String fileContent = "";
        try {
            fileContent = reader.readLines();
        } catch (IOException e) {}

        assertEquals(expectedResult, fileContent);
    }

}