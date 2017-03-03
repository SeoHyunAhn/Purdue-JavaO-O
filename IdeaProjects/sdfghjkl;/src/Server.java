import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

/**
 * Created by student on 11/21/16.
 */
public class Server {
    static String gametoken;
//    public static Vector LEADER;
    public static HashMap<String, ServerThread> leader;
    public static HashMap<String,GameThread> gAME;
    static Vector LEADER = new Vector();
    static Vector loginUsers = new Vector<>();
    public static void main(String[] args) throws IOException {
        try {
            // Instantiate a single server object that you can pass into your connected clients
            Server myServer = new Server();
            ServerSocket server = new ServerSocket(9989);
            while (true) {
                try {
                    Socket socket = server.accept();
                    // Pass myServer into Echo Thread
                    new ServerThread(myServer, socket).start();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String gameTKN(String token){
        gametoken=token;
        return gametoken;
    }

}