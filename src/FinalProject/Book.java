package FinalProject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book {

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isPossibleUse() {
        return possibleUse;
    }

    public void setPossibleUse(boolean possibleUse) {
        this.possibleUse = possibleUse;
    }

    String bookName;
    boolean possibleUse = true;
    String publisher;
    Date publishDate;

    public void setPublishDate(String publishDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.publishDate = formatter.parse(publishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }



    @Override
    public String toString() {
        return "Book [name =" + bookName + "]";
    }

    public String toCSV(){
        return bookName;
    }

}
