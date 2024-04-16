import java.text.SimpleDateFormat;
import java.util.Calendar;
public class JournalEntry {
    private String journalText;
    private Calendar dateCreated;

    public JournalEntry(String journalText) {
        this.journalText = journalText;
        dateCreated = Calendar.getInstance();
    }

    public String getText() {
        return journalText;
    }
    
    public Calendar getDate() {
        return dateCreated;
    }

    public String getDateString() {
        String str = "";
        str += (new SimpleDateFormat("MMMM").format(dateCreated.getTime())) + " ";
        str += dateCreated.get(Calendar.DAY_OF_MONTH) + ", ";
        str += dateCreated.get(Calendar.YEAR);
        return str;
    }
}