package com.mycompany.journal;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Journal journal;

        JournalBuilder builder = new JournalBuilder();
        
        /* If JSON file exists, get its entries */
        if (new File("src/main/resources/journal.JSON").isFile()) {
            journal = builder.getJournal();
        }
        else {
            journal = new Journal();
        }

        JournalFrame frame = new JournalFrame();
        frame.initialize(journal);
    }
}