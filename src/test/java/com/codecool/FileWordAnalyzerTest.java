package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileWordAnalyzerTest {

    private FilePartReader filePartReader;

    @BeforeEach
    void setUp() {
        filePartReader = new FilePartReader();
    }

    @Test
    void wordsByABC_WithAllDataValid() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");
        expectedResult.add("co");
        expectedResult.add("hejo");
        expectedResult.add("tam");

        ArrayList<String> loadedWords = new ArrayList<>();

        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            loadedWords = fileWordAnalyzer.wordsByABC();
        } catch (IOException e) {}

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void wordsContainingSubStringTest() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");

        ArrayList<String> loadedWords = new ArrayList<>();

        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            loadedWords = fileWordAnalyzer.wordsContainingSubString("ab");
        } catch (IOException e) {}

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void searchForPalindromes_ShouldReturnPalindrome() {
        ArrayList<String> expectedResult = new ArrayList<>();
        expectedResult.add("baooab");

        ArrayList<String> loadedWords = new ArrayList<>();
        filePartReader.setup("src/main/resources/default.txt", 1, 2);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        try {
            loadedWords = fileWordAnalyzer.wordsArePalindrome();
        } catch (IOException e) {}

        assertEquals(expectedResult, loadedWords);
    }

    @Test
    void returnEmptyList_WhenNoPalindromes() {
        ArrayList<String> expectedResult = new ArrayList<>();
        ArrayList<String> loadedWords = new ArrayList<>();

        filePartReader.setup("src/main/resources/default.txt", 1, 1);
        FileWordAnalyzer analyzer = new FileWordAnalyzer(filePartReader);
        try {
            loadedWords = analyzer.wordsArePalindrome();
        } catch (IOException e) {}

        assertEquals(expectedResult, loadedWords);
    }

}
