import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by student on 11/27/16.
 */
public class ServerThread extends Thread {
    //    protected Socket socket;
//    protected Server s;
//    protected DataInputStream in;x
//    protected DataOutputStream out;
    protected String line;
    protected String clientName;
    String username, password, userToken, fileusername, gametoken;
    int ServerPort = 9999;
    String ServerIP = "localhost";
    OutputStream out = null; // sent to clien
    BufferedReader in; // recieved from client
    Socket socket = null;
    Server listener = null;
    FileInputStream inFile = null;
    String token;
    Scanner scanner = null;
    int numcount = 0;
    int caseCount = 0;
    int cCount = 0;
    String a, actualusername, lastname = "", firstname = "", marks = "", filepassword, score1, score2, score3;

    boolean isLeader = false;
    String flag ="";

    int cumulativeScore = 0;
    int numTimesFooledOthers = 0;
    int numTimesFooledByOthers = 0;

    Vector loginUsers = new Vector<>();
    Vector gameTKN = new Vector<>();

    HashMap<String,String> hashmapsuggestion;
    HashMap<String,String> hashmapchoice;
    HashMap<String, Integer> hashmapCumulativeScore= new HashMap<>();
    HashMap<String, Integer> hashmapfoolOthers= new HashMap<>();
    HashMap<String, Integer> hashmapfoolByOthers= new HashMap<>();
    HashMap<String,String> hashmapmsg;

    BufferedReader fileReader;

    // This way, each EchoThread object knows about the server
    public ServerThread(Server theServer, Socket clientSocket) {
        this.listener = theServer;
        this.socket = clientSocket;
    }

    public void run() {

        try {
//            socket = listener.accept();
            System.out.println("Connection is successful and waiting for command");
//
////            socket.getOutputStream();
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            a = in.readLine();
//            System.out.println("hello");

            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String a = reader.readLine();
                    if (a.equals(".") || a == null) {
                        System.out.println("breaking out");
                        break;
                    }
//                    writer.println("RESPONSE--LOGIN--SUCCESS--abcdefghij");
                    if (a.startsWith("CREATENEWUSER")) {
//                        System.out.println(a+" line 80");
                        String reading = "";
                        try {
                            BufferedReader bufferRead = new BufferedReader(new FileReader(new File("/Users/student/IdeaProjects/Week13 20161121/src/UserDatabase")));
//                            scanner = new Scanner(fileReader);
                            String lineread;
                            while ((lineread = bufferRead.readLine()) != null) {
                                reading += lineread;
                                reading += "\n";
                            }
                        } catch (FileNotFoundException e) {
                            writer.println("File does not exist");
                            System.out.println("File does not exist");
                            e.printStackTrace();
                        }

                        String[] readParts = reading.split("\n");

                        String[] parts = a.split("--");
                        username = parts[1];
                        password = parts[2];
                        if (a.isEmpty() || username.isEmpty() || password.isEmpty()) {
                            String INVALIDMESSAGEFORMAT = "RESPONSE--LOGIN--INVALIDMESSAGEFORMAT--";
                            out = socket.getOutputStream();
                            writer.println(INVALIDMESSAGEFORMAT);
                        }
                        System.out.println("line 90");
                        if ((readParts) != null) {
                            for (int i = 0; i < 5; i++) {
                                String[] fileParts = readParts[i].split(":");
                                fileusername = fileParts[0];
//                                        filepassword = fileParts[1];
//                                        score1 = fileParts[2];
//                                        score2 = fileParts[3];
//                                        score3 = fileParts[4];
                                System.out.println("line 101");
                                if (username.equals(fileusername)) {
                                    String USERALREADYEXISTS = "RESPONSE--CREATENEWUSER--USERALREADYEXISTS--";
                                    out = socket.getOutputStream();
                                    writer.println(USERALREADYEXISTS);
                                }
                            }
                            if (username.equals(null) || username.length() > 10 || username.matches("[a-zA-Z]")) {
                                System.out.println("line 107");
                                String INVALIDUSERNAME = "RESPONSE--CREATENEWUSER--INVALIDUSERNAME--";
                                out = socket.getOutputStream();
                                writer.println(INVALIDUSERNAME);
                            } else if (password.isEmpty() || password.length() > 10 || password.length() < 1) {
                                String INVALIDUSERPASSWORD = "RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--";
                                out = socket.getOutputStream();
                                writer.println(INVALIDUSERPASSWORD);

                            } else {
                                for (int j = 0; j < password.length(); j++) {
                                    char c = password.charAt(j);
                                    if (Character.isDigit(c))
                                        numcount++;
                                    else if (Character.isUpperCase(c))
                                        caseCount++;
                                    else if (Character.isLowerCase(c))
                                        continue;
                                    else if (c != '#' || c != '&' || c != '*' || c != '$') {
                                        cCount++;
                                    }
                                }
                                if (numcount < 1 || caseCount < 1 || cCount > 1) {
                                    String INVALIDUSERPASSWORD = "RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--";
                                    out = socket.getOutputStream();
                                    writer.println(INVALIDUSERPASSWORD);
                                } else {
                                    String success = "RESPONSE--CREATENEWUSER--SUCCESS--";
                                    out = socket.getOutputStream();
                                    writer.println(success);
                                    break;
                                }
                            }
                        }
                    }
                    if (a.startsWith("LOGIN")) {
                        String reading = "";
                        System.out.println(a+" line 160");

                        try {
                            BufferedReader bufferRead = new BufferedReader(new FileReader(new File("/Users/student/IdeaProjects/Week13 20161121/src/UserDatabase")));
//                            scanner = new Scanner(fileReader);
                            String lineread;
                            while ((lineread = bufferRead.readLine()) != null) {
                                reading += lineread;
                                reading += "\n";
                            }
                        } catch (FileNotFoundException e) {
                            writer.println("File does not exist");
                            System.out.println("File does not exist");
                            e.printStackTrace();
                        }

                        String[] readParts = reading.split("\n");

                        String[] parts = a.split("--");
                        username = parts[1];
                        password = parts[2];
                        if (a.isEmpty() || username.isEmpty() || password.isEmpty()) {
                            String INVALIDMESSAGEFORMAT = "RESPONSE--LOGIN--INVALIDMESSAGEFORMAT--";
                            out = socket.getOutputStream();
                            writer.println(INVALIDMESSAGEFORMAT);
                        }
                        if ((readParts) != null) {
                            for (int i = 0; i < 5; i++) {
                                String[] fileParts = readParts[i].split(":");
                                fileusername = fileParts[0];
                                filepassword = fileParts[1];
                                score1 = fileParts[2];
                                score2 = fileParts[3];
                                score3 = fileParts[4];
                                System.out.println("line 196");
                                if (!username.equals(fileusername)) {
                                    String UNKNOWNUSER = "RESPONSE--LOGIN--UNKNOWNUSER--";
                                    flag = UNKNOWNUSER;
//                                    out = socket.getOutputStream();
//                                    writer.println(UNKNOWNUSER);
                                } else if (!password.equals(filepassword)) {
                                    String INVALIDUSERPASSWORD = "RESPONSE--LOGIN--INVALIDUSERPASSWORD--";
                                    flag =INVALIDUSERPASSWORD;
//                                    out = socket.getOutputStream();
//                                    writer.println(INVALIDUSERPASSWORD);
                                }else if (loginUsers.contains(username)){
                                    String USERALREADYLOGGEDIN = "RESPONSE--LOGIN--INVALIDUSERPASSWORD--";
                                    flag=USERALREADYLOGGEDIN;
//                                    out = socket.getOutputStream();
//                                    writer.println(USERALREADYLOGGEDIN);
                                    //USERALREADYLOGGEDIN
                                } else {
                                    userToken = generateRandomString();
                                    String success = "RESPONSE--LOGIN--SUCCESS--" + userToken;
                                    flag= success;
//                                    out = socket.getOutputStream();
//                                    loginUsers.add(username);
//                                    System.out.println(success);
//                                    writer.println(success);
                                    break;
                                }
                            }
                            out = socket.getOutputStream();
                            loginUsers.add(0, username);
                            System.out.println(flag);
                            writer.println(flag);
                        }
                    }
                    if (a.contains("STARTNEWGAME")) {
                        System.out.println(a+" line 215");

                        String[] parts = a.split("--");
                        String loggedUSERtoken = parts[1];
                        try {
                            if (!userToken.equals(loggedUSERtoken)) {
                                String USERNOTLOGGEDIN = "RESPONSE--STARTNEWGAME--USERNOTLOGGEDIN--";
                                out = socket.getOutputStream();
                                writer.println(USERNOTLOGGEDIN);
                            }
                            else {
                                gametoken = randomGameToken();
                                Server.gameTKN(gametoken);
                                String SUCCESS = "RESPONSE--STARTNEWGAME--SUCCESS--" + gametoken;
                                out = socket.getOutputStream();
                                writer.println(SUCCESS);
                                isLeader=true;
                            }
                        } catch (Exception e) {
                            String FAILURE = "RESPONSE--STARTNEWGAME--FAILURE--";
                            out = socket.getOutputStream();
                            writer.println(FAILURE);
                            e.printStackTrace();
                        }
                    } if (a.startsWith("JOINGAME")){
                        System.out.println(a+" line 255");

                        String[] parts = a.split("--");
                        String loggedUSERtoken = parts[1];
                        String loggedGAMEtoken = parts[2];
                        System.out.println(Server.gametoken);
                        this.gametoken=Server.gametoken;
                        try {
                            if (!userToken.equals(loggedUSERtoken)) {
                                String USERNOTLOGGEDIN = "RESPONSE--STARTNEWGAME--USERNOTLOGGEDIN--";
                                out = socket.getOutputStream();
                                writer.println(USERNOTLOGGEDIN);
                            } else if (!gametoken.contains(loggedGAMEtoken)){
                                String GAMEKEYNOTFOUND ="RESPONSE--STARTNEWGAME--GAMEKEYNOTFOUND--";
                                out = socket.getOutputStream();
                                writer.println(GAMEKEYNOTFOUND);
                            } else {
                                String SUCCESS = "NEWPARTICIPANT--"+ username+"--"+score1;
                                //NEWPARTICIPANT--<username>--<score>
                                out = socket.getOutputStream();
                                writer.println(SUCCESS);
                                isLeader= false;
                                loginUsers.add(1,username);
                            }
                        } catch (Exception e) {
                            String FAILURE = "RESPONSE--STARTNEWGAME--FAILURE--";
                            out = socket.getOutputStream();
                            writer.println(FAILURE);
                            e.printStackTrace();
                        }
                    }else if (a.startsWith("ALLPARTICIPANTSHAVEJOINED")){
                        String[] parts = a.split("--");
                        String loggedUSERtoken = parts[1];
                        String loggedGAMEtoken = parts[2];
                        System.out.println(Server.gametoken);
                        this.gametoken=Server.gametoken;
                        try {
                            if (!userToken.equals(loggedUSERtoken)) {
                                String USERNOTLOGGEDIN = "RESPONSE--STARTNEWGAME--USERNOTLOGGEDIN--";
                                out = socket.getOutputStream();
                                writer.println(USERNOTLOGGEDIN);
                            } else if (!gametoken.equals(loggedGAMEtoken)) {
                                String GAMEKEYNOTFOUND = "RESPONSE--STARTNEWGAME--GAMEKEYNOTFOUND--";
                                out = socket.getOutputStream();
                                writer.println(GAMEKEYNOTFOUND);
                            } else if (loginUsers.size()==2){
                                new GameThread(listener, socket).start();
                            }
                        } catch (Exception e) {
                            String USERNOTGAMELEADER = "RESPONSE--STARTNEWGAME--USERNOTGAMELEADER--";
//            out = socket.getOutputStream();
                            writer.println(USERNOTGAMELEADER);
                        }
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHAR_LIST_GAME =
            "abcdefghijklmnopqrstuvwxyz";
    private static final int RANDOM_STRING_LENGTH = 10;

    public String generateRandomString(){

        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    public String randomGameToken(){

        StringBuffer strGame = new StringBuffer();
        for(int i=0; i<3; i++){
            int number = getRandomNumberGame();
            char ch = CHAR_LIST_GAME.charAt(number);
            strGame.append(ch);
        }
        return strGame.toString();
    }
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    private int getRandomNumberGame() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST_GAME.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

}
