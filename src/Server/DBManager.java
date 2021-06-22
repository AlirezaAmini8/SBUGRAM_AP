package Server;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.*;

public class DBManager {

    public static final String FILE_PREFIX = "./db/";
    public static final String PROFILES_FILE = FILE_PREFIX + "ProfilesDB";
    public static final String POST_FILE =  FILE_PREFIX + "PostDB";


    private static DBManager ourInstance = new DBManager();


    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {/* do nothing! */ }

    @SuppressWarnings("unchecked")
    public synchronized void initializeServer() {
        ServerEXE.profiles = new ConcurrentHashMap<>();
        ServerEXE.posts = new Vector<>();
    }

    public synchronized void updateDataBase(){
        try {
            FileOutputStream fout = new FileOutputStream(PROFILES_FILE);
            ObjectOutputStream objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(ServerEXE.profiles); //writing profiles
            objToFile.close();
            fout.close();

            fout = new FileOutputStream(POST_FILE);
            objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(ServerEXE.posts); // writing posts
            objToFile.close();
            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}