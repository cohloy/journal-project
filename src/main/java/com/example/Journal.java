package com.example;

import java.io.IOException;
import java.util.*;
public class Journal {
    private ArrayList<JournalEntry> entries;
    private JournalBuilder journalBuilder;

    public Journal() {
        entries = new ArrayList<JournalEntry>();
        journalBuilder = new JournalBuilder();
    }

    public ArrayList<JournalEntry> getEntries() {
        return entries;
    }

    public void deleteEntry(JournalEntry entry) throws IOException {
        entries.remove(entry);
        journalBuilder.updateJournal(this);
    }

    public void addEntry(String text) throws IOException {
        JournalEntry entry = new JournalEntry(text);
        entries.add(entry);
        journalBuilder.updateJournal(this);
    }
}
