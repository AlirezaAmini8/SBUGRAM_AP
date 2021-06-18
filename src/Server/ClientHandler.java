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
                    case SET_PASSWORD:
                        answer=API.setpassword(income);
                    case ADD_POST:
                        answer=API.addpost(income);
                    case LIKE_POST:
                        answer=API.likepost(income);
                    case REPOST:
                        answer=API.repost(income);
                    case UPDATE_INFO:
                        answer=API.updateinfo(income);
                    case LOGOUT:
                        answer = API.logout(income);
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
        // after loggin out!
        try{
            socketIn.close();
            socketOut.close();
            userSocket.close();
        }catch(IOException e){}

    }
}
