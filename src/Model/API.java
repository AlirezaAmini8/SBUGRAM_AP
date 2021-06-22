package Model;

import Common.Command;
import Common.Profile;

import java.util.*;


public class API{

    public static boolean isUserNameExists(String usernamecheck){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.USERNAME_UNIQUE);
        toSend.put("username",usernamecheck);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static Profile login(String usernamecheck, String passwordcheck){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.LOGIN);
        toSend.put("username",usernamecheck);
        toSend.put("password",passwordcheck);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        if ( recieved.get("answer") == null ) return null;
        return (Profile) recieved.get("answer");
    }
    public static Boolean signUp(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.SIGNUP);
        toSend.put("profile", profile);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        if ( recieved.get("answer") == null ) return null;
        return (Boolean) recieved.get("answer");
    }
    public static String forgotpassword(String username){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.FORGOT_PASSWORD);
        toSend.put("username",username);

        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (String) recieved.get("answer");
    }
    public static String getpassword(String username){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.GET_PASSWORD);
        toSend.put("username",username);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (String) recieved.get("answer");
    }
    public static boolean setpassword(String username,String password){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.SET_PASSWORD);
        toSend.put("username",username);
        toSend.put("newpassword",password);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static void addpost( Post post){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command", Command.ADD_POST);
        toSend.put("post",post);
        ClientNetworker.serve(toSend);
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
        toSend.put("command",Command.REPOST);
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
    public static void updateinfo(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.UPDATE_INFO);
        toSend.put("profile", profile);
       ClientNetworker.serve(toSend);
    }
    public static void deleteaccount(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.DELETE_ACCOUNT);
        toSend.put("profile", profile);
        ClientNetworker.serve(toSend);
    }
    public static List<Post> timeLine(){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.TIME_LINE);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        return (List<Post>)  recieved.get("answer");
    }
    public static List<Post> getmyposts(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.MY_POSTS);
        toSend.put("profile", profile);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        return (List<Post>) recieved.get("answer");
    }
    public static void updatepost(Profile profile,Post lastPost,Post newPost){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.UPDATE_POST);
        toSend.put("profile",profile);
        toSend.put("lastpost",lastPost);
        toSend.put("newpost",newPost);
        ClientNetworker.serve(toSend);
    }
}
