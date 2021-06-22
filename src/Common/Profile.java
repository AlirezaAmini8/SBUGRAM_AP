package Common;

import Model.Post;

import java.io.Serializable;
import java.util.Vector;

public class Profile implements Serializable {

    private final String username;
    private String password;
    private String name;
    private String birthDate;
    private String lastName;
    private String location;
    private String favoriteColor;
    private byte[] profilePhoto;
    public Integer followersnum=0;
    public Integer followingsnum=0;
    public Vector<Profile> followers=new Vector<>();
    public Vector<Profile> folowings=new Vector<>();
    public Vector<Post> likepost=new Vector<>();

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
            return this.username.equals(((Profile)obj).getUserName());
        }
        catch(Exception e){
            return false;
        }
    }


    public String getUserName() {
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

    public Integer getBirthDate(){
        return Integer.parseInt(birthDate);
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

