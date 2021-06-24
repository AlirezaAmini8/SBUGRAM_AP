package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Message implements Comparable,Serializable {
    private String writer;
    private String description;
    private String time;

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getWriter() {
        return writer;
    }
    public String getDescription() {
        return description;
    }

    public  void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        Message message= (Message) o;
        return this.getTime().compareTo(message.getTime());
    }
}
