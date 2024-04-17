package com.example;

import java.util.*;
public class Journal {
    private ArrayList<JournalEntry> entries;

    public Journal() {
        entries = new ArrayList<JournalEntry>();
    }

    public ArrayList<JournalEntry> getEntries() {
        return entries;
    }

    public void deleteEntry(JournalEntry entry) {
        entries.remove(entry);
    }

    public void addEntry(String text) {
        JournalEntry entry = new JournalEntry(text);
        entries.add(entry);
    }
}
