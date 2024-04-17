package com.example;
import java.io.*;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

public class JournalBuilder {
    public Journal getJournal() throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/journal.JSON"));

        Journal journal = gson.fromJson(reader, Journal.class);
        return journal;
    }

    public void updateJournal(Journal journal) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        FileWriter writer = new FileWriter("src/main/resources/journal.JSON");
        writer.write(gson.toJson(journal));
        writer.close();
    }
}