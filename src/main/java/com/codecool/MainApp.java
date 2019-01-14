package com.codecool;

public class MainApp {
    public static void main(String[] args) {
        FilePartReader fpr = new FilePartReader();
        FileWordAnalyzer fwa = new FileWordAnalyzer(fpr);
    }
}
