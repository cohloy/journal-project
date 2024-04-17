package com.example;

public class JournalApp {
    public static void main(String[] args) {
        Journal journal = new Journal();
        journal.addEntry("Test entry 1");
        journal.addEntry("Test entry 2");
        journal.addEntry("Test entry 3");

        JournalFrame frame = new JournalFrame();
        frame.initialize(journal);
    }
}
