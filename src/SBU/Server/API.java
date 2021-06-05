package SBU.Server;

import SBU.Common.*;

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

}
