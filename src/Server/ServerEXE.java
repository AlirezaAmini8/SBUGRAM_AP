package Server;

import Common.*;
import Model.Post;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;

public class ServerEXE {
    public static final int PORT = 2222;
    private static boolean isServerUp = true;

    public static Map<String,Profile> profiles=null;
    public static Set<Post> posts = null;

    public static boolean isServerUp(){
        return isServerUp;
    }

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

