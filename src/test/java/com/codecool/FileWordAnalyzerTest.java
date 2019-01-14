package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FileWordAnalyzerTest {

    private FileWordAnalyzer fileWordAnalyzer;

    @Mock
    private FilePartReader filePartReaderMock;


    @BeforeEach
    void prepare() {
        MockitoAnnotations.initMocks(this);
        fileWordAnalyzer = new FileWordAnalyzer(filePartReaderMock);
    }


    @Test
    void wordsByABC_WithAllDataValid() throws IOException{
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");
        expectedResult.add("co");
        expectedResult.add("hejo");
        expectedResult.add("tam");

        ArrayList<String> loadedWords;

        String defaultString = "hejo co tam\nbaooab";

        when(filePartReaderMock.readLines())
                .thenReturn(defaultString);

        loadedWords = fileWordAnalyzer.wordsByABC();

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void wordsContainingSubStringTest() throws IOException{
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");

        ArrayList<String> loadedWords;

        String defaultString = "hejo co tam\nbaooab";

        when(filePartReaderMock.readLines())
                .thenReturn(defaultString);

        loadedWords = fileWordAnalyzer.wordsContainingSubString("ab");

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void searchForPalindromes_ShouldReturnPalindrome() throws IOException{
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");

        ArrayList<String> loadedWords;

        String defaultString = "hejo co tam ło ja cie ale ładna pogoda ło ma gad\nbaooab";

        when(filePartReaderMock.readLines())
                .thenReturn(defaultString);

        loadedWords = fileWordAnalyzer.wordsArePalindrome();

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void returnEmptyList_WhenNoPalindromes() throws IOException{
        ArrayList<String> expectedResult = new ArrayList<>();
        ArrayList<String> loadedWords;

        String defaultString = "hejo co tam ło ja cie ale ładna pogoda ło ma gad";

        when(filePartReaderMock.readLines())
                .thenReturn(defaultString);

        loadedWords = fileWordAnalyzer.wordsArePalindrome();

        assertEquals(expectedResult, loadedWords);
    }

}
