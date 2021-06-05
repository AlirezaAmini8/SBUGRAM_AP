package SBU.Client.Model;

import SBU.Common.Command;
import SBU.Common.Profile;
import java.util.*;


public class API{

    public static boolean isUserNameExists(String usernamecheck){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.USERNAME_UNIQUE);
        toSend.put("username",usernamecheck);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static boolean login(String usernamecheck,String passwordcheck){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.LOGIN);
        toSend.put("username",usernamecheck);
        toSend.put("password",passwordcheck);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static Boolean signUp(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.SIGNUP);
        toSend.put("profile", profile);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        if ( recieved.get("answer") == null ) return null;
        return (Boolean) recieved.get("answer");
    }
    public static boolean forgotpassword(Profile profile,String colorcheck){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.FORGOT_PASSWORD);
        toSend.put("profile",profile);
        toSend.put("color",colorcheck);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static boolean setpassword(Profile profile,String password){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.SET_PASSWORD);
        toSend.put("profile",profile);
        toSend.put("newpassword",password);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static boolean addpost(Profile profile,Post post){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.ADD_POST);
        toSend.put("profile",profile);
        toSend.put("post",post);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static boolean likepost(Profile profile,Post post,boolean haslike){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.LIKE_POST);
        toSend.put("profile",profile);
        toSend.put("post",post);
        toSend.put("like",haslike);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static boolean repost(Profile profile,Post post){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.LIKE_POST);
        toSend.put("profile",profile);
        toSend.put("repost",post);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static Boolean logout(){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.LOGOUT);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        if ( recieved.get("answer") == null ) return false;
        return (Boolean) recieved.get("answer");
    }
}
