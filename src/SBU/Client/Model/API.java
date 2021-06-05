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
}
