package Model;

import Common.Time;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Post implements Comparable,Serializable {
    private  Integer likesnum=0;
    private Integer repostsnum=0;
    private String username;
    private String title;
    private String description;
    private byte[] postimage;
    private String time;
    private String writer;
    public ArrayList<Message> comments=new ArrayList<>();

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setRepostsnum(Integer repostsnum) {
        this.repostsnum = repostsnum;
    }

    public void setLikesnum(Integer likesnum) {
        this.likesnum = likesnum;
    }

    public Integer getLikesnum() {
        return likesnum;
    }

    public Integer getRepostsnum() {
        return repostsnum;
    }

    public byte[] getPostimage() {
        return postimage;
    }

    public void setPostimage(byte[] postimage) {
        this.postimage = postimage;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }


    @Override
    public int compareTo(Object o) {
        Post post= (Post) o;
        return this.getTime().compareTo(post.getTime());
    }
}
