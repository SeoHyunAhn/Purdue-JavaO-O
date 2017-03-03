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
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                Socket socket = server.accept();
                ServerThread serverThread = new ServerThread(myServer, socket);
                loginUsers.add(serverThread);
                new ServerThread(myServer, socket).start();
//                sockClient = ss.accept(); // 措检挫疙  毵 旒
//                ta.append(sockClient.getInetAddress().getHostAddress() + " ㄜn");
//                ChatHandle threadChat = new ChatHandle();// 氇 觳毽
//                vChatList.add(threadChat);// 毽ろ胳 措ぎ
//                threadChat.start(); // ChatHandle 半 
            }// while

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String gameTKN(String token){
        gametoken=token;
        return gametoken;
    }

}