package Server;


import Common.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {

    private Socket userSocket;
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;

    public ClientHandler(Socket socket){
        try{
            userSocket = socket;
            this.socketIn = new ObjectInputStream (userSocket.getInputStream() );
            this.socketOut = new ObjectOutputStream (userSocket.getOutputStream() );
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){

        while(true){
            Map<String,Object> income = null;

            try{
                income = (Map<String,Object>) socketIn.readObject();
                Map<String,Object> answer = null;
                Command command = (Command) income.get("command");
                switch(command){
                    case USERNAME_UNIQUE:
                        answer = API.isUserNameExists(income);
                        break;
                    case LOGIN:
                        answer = API.login(income);
                        break;
                    case SIGNUP:
                        answer = API.signUp(income);
                        break;
                    case FORGOT_PASSWORD:
                        answer=API.forgotpassword(income);
                        break;
                    case SET_PASSWORD:
                        answer=API.setpassword(income);
                        break;
                    case ADD_POST:
                        answer=API.addpost(income);
                        break;
                    case LIKE_POST:
                        answer=API.likepost(income);
                        break;
                    case REPOST:
                        answer=API.repost(income);
                        break;
                    case UPDATE_INFO:
                        answer=API.updateinfo(income);
                        break;
                    case LOGOUT:
                        answer = API.logout(income);
                        break;
                    case TIME_LINE:
                        answer=API.timeLine(income);
                        break;
                    case GET_PASSWORD:
                        answer=API.getpassword(income);
                        break;
                    case MY_POSTS:
                        answer=API.getmyposts(income);
                        break;
                    case DELETE_ACCOUNT:
                        answer=API.deleteaccount(income);
                        break;
                    case UPDATE_POST:
                        answer=API.updatepost(income);
                        break;
                    case VIEW_COMMENTS:
                        answer=API.viewcomments(income);
                        break;
                    case ADD_COMMENT:
                        answer=API.addcomment(income);
                        break;
                    case GET_PROFILES:
                        answer=API.getprofiles(income);
                        break;
                    case FOLLOW:
                        answer=API.follow(income);
                        break;
                    case UPDATE_PROFILE:
                        answer=API.updateprofile(income);
                        break;
                    case GET_POST:
                        answer=API.getpost(income);
                        break;
                    case GET_INFO:
                        answer=API.getinfo(income);
                        break;
                    case FOLLOWERS_LIST:
                        answer=API.followerslist(income);
                        break;
                    case FOLLOWINGS_LIST:
                        answer=API.followingslist(income);
                        break;
                    case UPDATE_PROF:
                        answer=API.updateprof(income);
                        break;
                }
                socketOut.writeObject(answer);
                socketOut.flush();
            }
            catch(ClassCastException | ClassNotFoundException e){
            }
            catch(EOFException e){
                break;
            }
            catch(IOException e){
                break;
            }

        }
        try{
            socketIn.close();
            socketOut.close();
            userSocket.close();
        }catch(IOException e){}

    }
}
