package SBU.Server;

import SBU.Client.Model.Post;
import SBU.Common.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ServerEXE {
    public static final int PORT = 2222;
    private static boolean isServerUp = true;

    public static Map<String,Profile> profiles=null;
    public static Set<Post> posts = null;
    public static Map<Profile, Post> eachuserpost=new HashMap<>();

    public static boolean isServerUp(){
        return isServerUp;
    }

    /*public static void checkValidMail(Mail mail){
        String recieverName = mail.getReciever();
        if (recieverName.equals("mailerdaemon")) return; // loop!
        Profile recieverProf = profiles.get(recieverName);
        if (recieverProf != null) return; //its ok

        String message = "Dear user\nwe are afraid that tell you we can't handle your recent request\nthe address you requested is not valid in our servers\n";
        Mail answer = new Mail("mailerdaemon", mail.getSender() , "Mail Delivery Subsystem" , message);

        Map<String,Object> outcome =  new HashMap<>();
        outcome.put("mail",answer);
        outcome.put("command",Command.SEND_MAIL);
        API.sendMail(outcome);
        ServerEXE.mails.remove(mail); // delete invalid mail from server!
    }*/

    public static void main(String[] args) {
        DBManager.getInstance().initializeServer();

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            System.exit(12);
        }

        while ( isServerUp() ){
            Socket currentUserSocket = null;
            try {
                currentUserSocket = serverSocket.accept();
                ClientHandler clientHandler=new ClientHandler(currentUserSocket);
                new Thread( clientHandler ).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

