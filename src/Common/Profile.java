package Common;

import Model.Post;

import java.io.Serializable;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Profile implements Serializable {
    private final String username;
    private String password;
    private String name;
    private String birthDate;
    private String lastName;
    private String location;
    private String favoriteColor;
    private byte[] profilePhoto;
    public AtomicInteger followersnum=new AtomicInteger(0);
    public AtomicInteger followingsnum=new AtomicInteger(0);



    public Vector<Profile> followers=new Vector<>();
    public Vector<Profile> folowings=new Vector<>();
    public Vector<Post> likepost=new Vector<>();
    public String wasWhere;
    public String path;

    public Vector<Profile> getFollowers() {
        return followers;
    }

    public void setFollowers(Vector<Profile> followers) {
        this.followers = followers;
    }

    public Vector<Profile> getFolowings() {
        return folowings;
    }

    public void setFolowings(Vector<Profile> folowings) {
        this.folowings = folowings;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AtomicInteger getFollowersnum() {
        return followersnum;
    }

    public void setFollowersnum(Integer followersnum) {
        this.followersnum = new AtomicInteger(followersnum);
    }

    public AtomicInteger getFollowingsnum() {
        return followingsnum;
    }

    public void setFollowingsnum(Integer followingsnum) {
        this.followingsnum=new AtomicInteger(followingsnum)
        ;
    }
    public String getWasWhere() {
        return wasWhere;
    }

    public void setWasWhere(String wasWhere) {
        this.wasWhere = wasWhere;
    }




    public Profile(String username){
        this.username = username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        try{
            return this.username.equals(((Profile)obj).getUsername());
        }
        catch(Exception e){
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }
    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Profile authenticate(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)) return this;
        return null;
    }



}

