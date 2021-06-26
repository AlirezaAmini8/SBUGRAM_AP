package Model;

import java.io.Serializable;

public class Message implements Serializable {
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

}
