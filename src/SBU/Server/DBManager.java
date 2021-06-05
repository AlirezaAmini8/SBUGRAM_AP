package SBU.Server;

import SBU.Client.Model.Post;
import SBU.Common.Profile;

import java.io.*;
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
    public synchronized void initializeServer(){
        try {
            FileInputStream fin=new FileInputStream(DBManager.PROFILES_FILE);
            ObjectInputStream inFromFile=new ObjectInputStream(fin);
            ServerEXE.profiles = new ConcurrentHashMap<>( (ConcurrentHashMap<String, Profile>) inFromFile.readObject());
            inFromFile.close();
            fin.close();
        }
        catch(EOFException | StreamCorruptedException e){
            ServerEXE.profiles = new ConcurrentHashMap<>();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileInputStream fin = new FileInputStream(DBManager.POST_FILE);
            ObjectInputStream inFromFile = new ObjectInputStream(fin);
            ServerEXE.posts = new ConcurrentSkipListSet<>( (ConcurrentSkipListSet<Post>) inFromFile.readObject());
            inFromFile.close();
            fin.close();
        }
        catch(EOFException | StreamCorruptedException e){
            ServerEXE.posts = new ConcurrentSkipListSet<>();
        }catch (Exception e){
            e.printStackTrace();
        }
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
            objToFile.writeObject(ServerEXE.posts); // writing mails
            objToFile.close();
            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}