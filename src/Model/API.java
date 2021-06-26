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
    public static Boolean signUp(Profile profile,String path){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.SIGNUP);
        toSend.put("profile", profile);
        toSend.put("path",path);
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
    public static void addpost( Post post,String path){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command", Command.ADD_POST);
        toSend.put("post",post);
        toSend.put("path",path);
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
    public static void updateinfo(Profile profile,String path){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.UPDATE_INFO);
        toSend.put("profile", profile);
        toSend.put("path",path);
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
    public static List<Message> viewcomments(Post post){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.VIEW_COMMENTS);
        toSend.put("post",post);
        Map<String,Object> recieved = ClientNetworker.serve(toSend);
        return (List<Message>)  recieved.get("answer");
    }
    public static void addcomment(Profile profile,Post post, Message message){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command", Command.ADD_COMMENT);
        toSend.put("profile",profile);
        toSend.put("post",post);
        toSend.put("comment",message);
        ClientNetworker.serve(toSend);
    }
    public static Collection<Profile> getprofiles(){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command", Command.GET_PROFILES);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        if ( recieved.get("answer") == null ) return null;
        return (Collection<Profile>) recieved.get("answer");
    }
    public static boolean follow(Profile profile,Profile followed,boolean hasfollow){
        Map<String,Object> toSend=new HashMap<>();
        toSend.put("command",Command.FOLLOW);
        toSend.put("profile",profile);
        toSend.put("followedProfile",followed);
        toSend.put("follow",hasfollow);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static void updateprofile(Profile profile1,Profile followed){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.UPDATE_PROFILE);
        toSend.put("profile",profile1);
        toSend.put("followedProfile",followed);
        ClientNetworker.serve(toSend);
    }
    public static boolean getpost(Profile profile,Post post){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.GET_POST);
        toSend.put("profile",profile);
        toSend.put("post",post);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }
    public static void getinfo(Profile profile,Profile getprofile,String path){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.GET_INFO);
        toSend.put("profile",profile);
        toSend.put("getprofile",getprofile);
        toSend.put("path",path);
        ClientNetworker.serve(toSend);
    }
    public static List<Profile> followerslist(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.FOLLOWERS_LIST);
        toSend.put("profile",profile);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (List<Profile>) recieved.get("answer");
    }
    public static List<Profile> followingslist(Profile profile){
        Map<String,Object> toSend = new HashMap<>();
        toSend.put("command", Command.FOLLOWINGS_LIST);
        toSend.put("profile",profile);
        Map<String,Object> recieved=ClientNetworker.serve(toSend);
        return (List<Profile>) recieved.get("answer");
    }
}
