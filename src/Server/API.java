package Server;

import Common.*;
import Model.Message;
import Model.Post;

import java.util.*;



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
            System.out.println(  profile.getUsername() +" login." );
            System.out.println("time : "+Time.getTime());
        }
        return ans;
    }
    public static Map<String,Object> signUp(Map<String,Object> income){
        Profile newProfile = (Profile) income.get("profile");
        String path= (String) income.get("path");
        String username = newProfile.getUsername();
        ServerEXE.profiles.put(username,newProfile);
        DBManager.getInstance().updateDataBase(); // save to local file
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.SIGNUP);
        ans.put("answer",new Boolean(true));

        System.out.println( newProfile.getUsername()+" register "+path );
        System.out.println("time : "+Time.getTime());

        return ans;
    }
    public static Map<String,Object> forgotpassword(Map<String ,Object> income){
        String username = (String) income.get("username");
        Profile profile=ServerEXE.profiles.get(username);
        String favoritecolor= profile.getFavoriteColor();

        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.FORGOT_PASSWORD);
        ans.put("answer",favoritecolor);
        return ans;
    }
    public static Map<String,Object> getpassword(Map<String ,Object> income) {
        String username= (String) income.get("username");
        Profile profile= ServerEXE.profiles.get(username);
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.GET_PASSWORD);
        ans.put("answer",profile.getPassword());
        return ans;
    }
    public static Map<String,Object> setpassword(Map<String ,Object> income) {
        String username= (String) income.get("username");
        Profile profile= ServerEXE.profiles.get(username);
        String newpassword= (String) income.get("newpassword");
        profile.setPassword(newpassword);
        ServerEXE.profiles.replace(username,profile);
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.SET_PASSWORD);
        ans.put("answer",new Boolean(true));
        System.out.println(profile.getUsername()+" changed password.");
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> addpost(Map<String ,Object> income) {
        Post newpost= (Post) income.get("post");
        String path= (String) income.get("path");
        ServerEXE.posts.add(newpost);
        DBManager.getInstance().updateDataBase();
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.ADD_POST);
        ans.put("answer",new Boolean(true));
        System.out.println(newpost.getUsername()+" publish ["+newpost.getTitle()+"] ["+path+"] ["+newpost.getWriter()+"]");
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
            System.out.println(profile.getUsername() +" like :["+newpost.getWriter()+"] ["+newpost.getTitle()+"]");
        }else{
            ans.put("answer", new Boolean(false));
            System.out.println(profile.getUsername() +" dislike :["+newpost.getWriter()+"] ["+newpost.getTitle()+"]");
        }
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> repost(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Post post= (Post) income.get("repost");
        Post repost=post;
        ServerEXE.posts.add(repost);
        DBManager.getInstance().updateDataBase();

        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.REPOST);
        ans.put("answer", new Boolean(true));
        System.out.println(profile.getUsername() +" repost :["+post.getWriter()+"] ["+post.getTitle()+"]");
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> logout(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.LOGOUT);
        ans.put("answer",new Boolean(true));
        System.out.println("logged out and disconnected from server.");
        System.out.println("time :"+Time.getTime());
        return ans;
    }
    public static Map<String,Object> updateinfo(Map<String,Object> income){

        Profile newProfile = (Profile) income.get("profile");
        String path= (String) income.get("path");
        String username = newProfile.getUsername();
        ServerEXE.profiles.replace(username,newProfile);
        DBManager.getInstance().updateDataBase(); // save to local file

        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.UPDATE_INFO);
        ans.put("answer",new Boolean(true));
        System.out.println(newProfile.getUsername()+" update info.");
        if(path!=null) {
            System.out.println("message: " + path);
        }else{
            System.out.println("message: " + null);

        }
        System.out.println("time :"+Time.getTime());
        return ans;
    }
    public static Map<String,Object> deleteaccount (Map<String,Object> income){
        Profile newProfile = (Profile) income.get("profile");
        String username = newProfile.getUsername();
        for(int i=ServerEXE.posts.size()-1;i>=0;i--){
            if(ServerEXE.posts.get(i).getWriter().equals(username))
                ServerEXE.posts.remove(ServerEXE.posts.get(i));
        }
        ServerEXE.profiles.remove(username,newProfile);
        DBManager.getInstance().updateDataBase(); // save to local file
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.DELETE_ACCOUNT);
        ans.put("answer",new Boolean(true));
        System.out.println( username+"deleted account!");
        System.out.println("time :"+Time.getTime());
        return ans;
    }
    public static Map<String,Object> timeLine (Map<String,Object> income) {
        Collections.sort(ServerEXE.posts);
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.TIME_LINE);
        ans.put("answer",new ArrayList<>(ServerEXE.posts));
        return ans;
    }
    public static Map<String,Object> getmyposts(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Vector<Post> mypostslist=new Vector<>();
        for(Post post: ServerEXE.posts){
            if(post.getUsername().equals(profile.getUsername())){
                mypostslist.add(post);
            }
        }
        Collections.sort(mypostslist);
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.MY_POSTS);
        ans.put("answer",new ArrayList<>(mypostslist));
        System.out.println(profile.getUsername() +"get posts list.");
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> updatepost(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Post lastPost=(Post) income.get("lastpost");
        Post newPost=(Post) income.get("newpost");
        ServerEXE.posts.remove(lastPost);
        ServerEXE.posts.add(newPost);
        ServerEXE.profiles.replace(profile.getUsername(),profile);
        DBManager.getInstance().updateDataBase();

        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.UPDATE_POST);
        ans.put("answer",new Boolean(true));

        return ans;
     }
    public static Map<String,Object> viewcomments (Map<String,Object> income) {
        Post post= (Post) income.get("post");
        ServerEXE.posts.get(ServerEXE.posts.indexOf(post));

        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.VIEW_COMMENTS);
        ans.put("answer",new ArrayList<>(post.comments));
        return ans;
    }
    public static Map<String,Object> addcomment(Map<String,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Post post= (Post) income.get("post");
        Message message= (Message) income.get("comment");
        ServerEXE.posts.remove(post);
        post.comments.add(message);
        ServerEXE.posts.add(post);
        DBManager.getInstance().updateDataBase();
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.ADD_COMMENT);
        ans.put("answer",new Boolean(true));
        System.out.println(profile.getUsername()+" comment.");
        System.out.println("message :"+post.getTitle());
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> getprofiles(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.GET_PROFILES);
        ans.put("answer",ServerEXE.profiles.values());
        return ans;
    }
    public static Map<String,Object> follow(Map<String ,Object> income) {
        Profile profile= (Profile) income.get("profile");
        Profile followed= (Profile) income.get("followedProfile");
        Boolean hasfollow= (Boolean) income.get("follow");
        Map<String,Object> ans=new HashMap<>();
        ans.put("command",Command.FOLLOW);
        if(hasfollow) {
            ans.put("answer", new Boolean(true));
            System.out.println(profile.getUsername() +" follow : "+followed.getUsername());
        }else{
            ans.put("answer", new Boolean(false));
            System.out.println(profile.getUsername() +" unfollow :"+followed.getUsername());
        }
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public static Map<String,Object> updateprofile(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Profile followed= (Profile) income.get("followedProfile");
        ServerEXE.profiles.replace(profile.getUsername(),profile);
        ServerEXE.profiles.replace(followed.getUsername(),followed);
        DBManager.getInstance().updateDataBase();

        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.UPDATE_PROFILE);
        ans.put("answer",new Boolean(true));

        return ans;
    }
    public static Map<String,Object> getpost(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Post post= (Post) income.get("post");
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.GET_POST);
        if(ServerEXE.profiles.get(profile.getUsername()).likepost.contains(post)) {
            ans.put("answer", new Boolean(true));
        }else{
            ans.put("answer",new Boolean(false));
        }
        return ans;
    }
    public static Map<String,Object> getinfo(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Profile getprofile= (Profile) income.get("getprofile");
        String path= (String) income.get("path");
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.GET_INFO);
        ans.put("answer",new Boolean(true));
        System.out.println(profile.getUsername()+" get info "+getprofile.getUsername());
        System.out.println("message :["+getprofile.getUsername()+"] ["+path+"]");
        System.out.println("time :"+Time.getTime() );
        return ans;
    }
    public  static Map<String,Object> followerslist(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.FOLLOWERS_LIST);
        ans.put("answer",new ArrayList<>(profile.getFollowers()));
        return ans;
    }
    public  static Map<String,Object> followingslist(Map<String,Object> income){
        Profile profile= (Profile) income.get("profile");
        Map<String,Object> ans = new HashMap<>();
        ans.put("command",Command.FOLLOWINGS_LIST);
        ans.put("answer",new ArrayList<>(profile.getFolowings()));
        return ans;
    }
}
