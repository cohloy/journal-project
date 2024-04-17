package com.mycompany.journal;
import java.io.*;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

public class JournalBuilder {
    //final public String FILE_PATH = "src/main/resources/journal.JSON";
    final public static String FILE_PATH = "./journal.JSON";

    public Journal getJournal() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

        Journal journal = gson.fromJson(reader, Journal.class);
        return journal;
    }

    public void updateJournal(Journal journal) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter(FILE_PATH);
        writer.write(gson.toJson(journal));
        writer.close();
    }
}