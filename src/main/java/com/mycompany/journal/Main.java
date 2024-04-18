/*
 * Class: Main
 * Description: This is the driver class for the journal application
 * Author: Theodore B
 */
package com.mycompany.journal;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /* Create Journal and JournalBuilder */
        Journal journal;
        JournalBuilder builder = new JournalBuilder();
        
        /* If JSON file exists, get its entries */
        if (new File(JournalBuilder.FILE_PATH).isFile()) {
            journal = builder.getJournal();
        }
        //otherwise, create new empty journal
        else {
            journal = new Journal();
        }

        /* Create and initialze JournalFrame */
        JournalFrame frame = new JournalFrame();
        frame.initialize(journal);
    }
}