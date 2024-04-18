/*
 * Class: JournalBuilder
 * Description: This class describes methods that can get and update a data file representing the journal
 * Author: Theodore B
 */
package com.mycompany.journal;
import java.io.*;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

public class JournalBuilder {
    /* Describes path to data file location */
    final public static String FILE_PATH = "./journal.JSON";

    /* Get journal data file */
    public Journal getJournal() throws FileNotFoundException {
        /* Uses Gson library read the file */
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

        //get Journal object from JSON file and return it
        Journal journal = gson.fromJson(reader, Journal.class);
        return journal;
    }

    /* Update journal data file */
    public void updateJournal(Journal journal) throws IOException {
        /* Uses Gson library write the file */
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(FILE_PATH);

        //write the new Journal object to the file
        writer.write(gson.toJson(journal));
        writer.close();
    }
}