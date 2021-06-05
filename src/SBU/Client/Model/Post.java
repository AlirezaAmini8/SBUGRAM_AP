package SBU.Client.Model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

public class Post {



    private  Integer likesnum;



    private Integer repostsnum;
    private String name;
    private String title;
    private String description;
    private byte[] postimage;
    private LocalDate date;
    private LocalTime time;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
