/*
 * Class: Journal
 * Description: This class describes the Journal object which can store entries
 * Author: Theodore B
 */
package com.mycompany.journal;

import java.io.IOException;
import java.util.*;
public class Journal {
    //create instance variables
    private ArrayList<JournalEntry> entries;
    private JournalBuilder journalBuilder;

    /* Defauly constructor */
    public Journal() {
        //intialize variables
        entries = new ArrayList<JournalEntry>();
        journalBuilder = new JournalBuilder();
    }

    /* Return ArrayList of journal entries */
    public ArrayList<JournalEntry> getEntries() {
        return entries;
    }

    /* Return and entry from journal */
    public void deleteEntry(JournalEntry entry) throws IOException {
        entries.remove(entry);
        //update data file
        journalBuilder.updateJournal(this);
    }

    /* Add entry to journal */
    public void addEntry(String text) throws IOException {
        JournalEntry entry = new JournalEntry(text);
        entries.add(entry);
        //update data file
        journalBuilder.updateJournal(this);
    }
}
