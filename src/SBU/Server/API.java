package SBU.Server;

import SBU.Client.Model.Post;
import SBU.Common.*;
import javafx.geometry.Pos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class API {
    public static Map<String,Object> isUserNameExists(Map<String,Object> income){
        String usernamecheck = (String) income.get("username");
        Profile profile = ServerEXE.profiles.get(usernamecheck);
        Boolean exists = (profile != null);

        Map<String,Object> ans = new HashMap<>();
        ans.put("answer",exists);
        ans.put("command",Command.USERNAME_UNIQUE);

        return ans;
    }
    public static Map<String,Object> login(Map<String,Object> income){
        String username = (String) income.get("username");
        String password = (String) income.get("password");

        Boolean isNullProfile = (ServerEXE.profiles.get(username) == null);
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.LOGIN);
        ans.put("exists",!isNullProfile);
        if(isNullProfile){
            return ans;
        }
        Profile profile = ServerEXE.profiles.get(username).authenticate(username, password);
        ans.put("answer",profile);

        if(profile != null){
            System.out.println(  "login :" + profile.getUserName());
            System.out.println("time : "+Time.getTime());
        }
        return ans;
    }
    public static Map<String,Object> signUp(Map<String,Object> income){
        Profile newProfile = (Profile) income.get("profile");
        String username = newProfile.getUserName();
        ServerEXE.profiles.put(username,newProfile);
        DBManager.getInstance().updateDataBase(); // save to local file
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.SIGNUP);
        ans.put("answer",new Boolean(true));

        System.out.println( " signup :" + newProfile.getUserName());
        System.out.println("time : "+Time.getTime());

        return ans;
    }
    public static Map<String,Object> forgotpassword(Map<String ,Object> income){
        Profile profile = (Profile) income.get("profile");
        String favoritecolor= profile.getFavoriteColor();
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.FORGOT_PASSWORD);
        ans.put("answer",favoritecolor);
        System.out.println("forgotten password: "+ profile.getPassword());
        System.out.println("time :"+Time.getTime());
        return ans;
    }
    public static Map<String,Object> setpassword(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        String newpassword= (String) income.get("newpassword");
        profile.setPassword(newpassword);
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.SET_PASSWORD);
        ans.put("answer",new Boolean(true));
        System.out.println(profile.getUserName()+"password changed to:"+ newpassword);
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> addpost(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Post newpost= (Post) income.get("post");
        ServerEXE.eachuserpost.put(profile,newpost);
        ServerEXE.posts.add(newpost);
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.ADD_POST);
        ans.put("answer",new Boolean(true));
        System.out.println(profile.getUserName()+" added post");
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> likepost(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Post newpost= (Post) income.get("post");
        Boolean haslike= (Boolean) income.get("like");
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.LIKE_POST);
        if(haslike) {
            ans.put("answer", new Boolean(true));
            System.out.println("liked :"+newpost.getTitle());
        }else{
            ans.put("answer", new Boolean(false));
            System.out.println("disliked :"+newpost.getTitle());
        }
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> repost(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Post repost= (Post) income.get("repost");
        ServerEXE.eachuserpost.put(profile,repost);
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.REPOST);
        ans.put("answer", new Boolean(true));
        System.out.println("reposted :"+repost.getTitle());
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> logout(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.LOGOUT);
        ans.put("answer",new Boolean(true));
        return ans;
    }
}
