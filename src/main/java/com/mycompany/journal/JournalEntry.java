/*
 * Class: JournalEntry
 * Description: This class describes a journal entry with associated text and date
 * Author: Theodore B
 */
package com.mycompany.journal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
public class JournalEntry {
    /* Instance variables */
    private String journalText;
    private Calendar dateCreated;

    /* Constructor */
    public JournalEntry(String journalText) {
        this.journalText = journalText;
        //set dateCreated to current date
        this.dateCreated = Calendar.getInstance();
    }

    /* ----------------------------Getters---------------------------- */
    public String getText() {
        return journalText;
    }
    
    public Calendar getDate() {
        return dateCreated;
    }

    /* ----------------------------Setters---------------------------- */
    public void setText(String text) {
        journalText = text;
    }
    
    public void setDate(Calendar date) {
        dateCreated = date;
    }

    /* Return date in readable String format */
    public String getDateString() {
        String str = "";
        str += (new SimpleDateFormat("MMMM").format(dateCreated.getTime())) + " ";
        str += dateCreated.get(Calendar.DAY_OF_MONTH) + ", ";
        str += dateCreated.get(Calendar.YEAR);
        return str;
    }

    /* Return String describing journal entry */
    @Override
    public String toString() {
        String str = "";
        str += getDateString() + "\n";
        str += journalText + "\n";
        return str;
    }
}