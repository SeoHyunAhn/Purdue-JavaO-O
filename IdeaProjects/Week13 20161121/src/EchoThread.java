//import com.intellij.vcs.log.Hash;
//
//import javax.swing.*;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.*;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
//import static javax.swing.JOptionPane.showMessageDialog;
//
///**
// * Created by student on 11/27/16.
// */
//public class ServerThread extends Thread {
//    //    protected Socket socket;
////    protected Server s;
////    protected DataInputStream in;x
////    protected DataOutputStream out;
//    protected String line;
//    int score;
//    PrintWriter writer, writer1;
//    public Vector LEADER;
//    int coutnques;
//    protected String clientName;
//    String username, password, userToken, fileusername, gametoken, SENDQUESTIONUNSWER,SENDquestion;
//    int ServerPort = 9999;
//    String ServerIP = "localhost";
//    OutputStream out = null; // sent to clien
//    BufferedReader in; // recieved from client
//    Socket socket = null, socket1 = null;
//    Server listener = null;
//    Server listener1 = null;
//    FileInputStream inFile = null;
//    String token;
//    Scanner scanner = null;
//    int numcount = 0;
//    int caseCount = 0;
//    int cCount = 0;
//    String a, actualusername, lastname = "", firstname = "", marks = "", filepassword, score1, score2, score3;
//    final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
//
//    boolean isLeader = false;
//    String flag = "";
//
//    ServerThread serverleader, serverclient;
//    int cumulativeScore = 0;
//    int numTimesFooledOthers = 0;
//    int numTimesFooledByOthers = 0;
//    GameThread gameThread=null;
////
////    Vector loginUsers = new Vector<>();
////    Vector gameTKN = new Vector<>();
//
//    HashMap<String, Integer> hashmapCumulativeScore = new HashMap<>();
//    HashMap<String, Integer> hashmapfoolOthers = new HashMap<>();
//    HashMap<String, Integer> hashmapfoolByOthers = new HashMap<>();
//    HashMap<String, String> hashmapmsg;
//    HashMap QAlist = new HashMap();
//    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
//
//    BufferedReader fileReader;
//    BufferedReader reader;
//
//    // This way, each EchoThread object knows about the server
//    public ServerThread(Server theServer, Socket clientSocket) {
//        this.listener = theServer;
//        this.socket = clientSocket;
//        reader = null;
//        writer = null;
//
//        System.out.println("Connection is successful and waiting for command");
//        try {
//            InputStream is = socket.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            writer = new PrintWriter(socket.getOutputStream(), true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
////    public ChatHandle() {
////        try {
////            // 입력
////            OutputStream os = sockClient.getOutputStream();// 출력
////            pw = new PrintWriter(new OutputStreamWriter(os));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }// catch
////    }// 내부 클래스의 기본 생성자
//
//    public void run() {
//
//        try {
////            socket = listener.accept();
////
//////            socket.getOutputStream();
////            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
////
////            a = in.readLine();
////            System.out.println("hello");
//
//
//            try {
//
//                while (true) {
//                    String a = reader.readLine();
//                    if (a.equals(".") || a == null) {
//                        System.out.println("breaking out");
//                        break;
//                    }
////                    writer.println("RESPONSE--LOGIN--SUCCESS--abcdefghij");
//                    if (a.startsWith("CREATENEWUSER")) {
////                        System.out.println(a+" line 80");
//                        String reading = "";
//                        try {
//                            BufferedReader bufferRead = new BufferedReader(new FileReader(new File("/Users/student/IdeaProjects/Week13 20161121/src/UserDatabase")));
////                            scanner = new Scanner(fileReader);
//                            String lineread;
//                            while ((lineread = bufferRead.readLine()) != null) {
//                                reading += lineread;
//                                reading += "\n";
//                            }
//                            System.out.println(reading);
//                        } catch (FileNotFoundException e) {
//                            writer.println("File does not exist");
//                            System.out.println("File does not exist");
//                            e.printStackTrace();
//                        }
//
//                        String[] readParts = reading.split("\n");
//
//                        String[] parts = a.split("--");
//                        username = parts[1];
//                        password = parts[2];
//                        if (a.isEmpty() || username.isEmpty() || password.isEmpty()) {
//                            String INVALIDMESSAGEFORMAT = "RESPONSE--LOGIN--INVALIDMESSAGEFORMAT--";
//                            out = socket.getOutputStream();
//                            writer.println(INVALIDMESSAGEFORMAT);
//                        }
//                        System.out.println("line 90");
//                        if ((readParts) != null) {
//                            for (int i = 0; i < 5; i++) {
//                                String[] fileParts = readParts[i].split(":");
//                                fileusername = fileParts[0];
////                                        filepassword = fileParts[1];
////                                        score1 = fileParts[2];
////                                        score2 = fileParts[3];
////                                        score3 = fileParts[4];
//                                System.out.println("line 101");
//                                if (username.equals(fileusername)) {
//                                    String USERALREADYEXISTS = "RESPONSE--CREATENEWUSER--USERALREADYEXISTS--";
//                                    out = socket.getOutputStream();
//                                    writer.println(USERALREADYEXISTS);
//                                }
//                            }
//                            if (username.equals(null) || username.length() > 10 || username.matches("[a-zA-Z]")) {
//                                System.out.println("line 107");
//                                String INVALIDUSERNAME = "RESPONSE--CREATENEWUSER--INVALIDUSERNAME--";
//                                out = socket.getOutputStream();
//                                writer.println(INVALIDUSERNAME);
//                            } else if (password.isEmpty() || password.length() > 10 || password.length() < 1) {
//                                String INVALIDUSERPASSWORD = "RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--";
//                                out = socket.getOutputStream();
//                                writer.println(INVALIDUSERPASSWORD);
//
//                            } else {
//                                for (int j = 0; j < password.length(); j++) {
//                                    char c = password.charAt(j);
//                                    if (Character.isDigit(c))
//                                        numcount++;
//                                    else if (Character.isUpperCase(c))
//                                        caseCount++;
//                                    else if (Character.isLowerCase(c))
//                                        continue;
//                                    else if (c != '#' || c != '&' || c != '*' || c != '$') {
//                                        cCount++;
//                                    }
//                                }
//                                if (numcount < 1 || caseCount < 1 || cCount > 1) {
//                                    String INVALIDUSERPASSWORD = "RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--";
//                                    out = socket.getOutputStream();
//                                    writer.println(INVALIDUSERPASSWORD);
//                                } else {
//                                    String success = "RESPONSE--CREATENEWUSER--SUCCESS--";
//                                    out = socket.getOutputStream();
//                                    writer.println(success);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    if (a.startsWith("LOGIN")) {
//                        String reading = "";
//                        System.out.println(a + " line 160");
//
//                        try {
//                            BufferedReader bufferRead = new BufferedReader(new FileReader(new File("/Users/student/IdeaProjects/Week13 20161121/src/UserDatabase")));
////                            scanner = new Scanner(fileReader);
//                            String lineread;
//                            while ((lineread = bufferRead.readLine()) != null) {
//                                reading += lineread;
//                                reading += "\n";
//                            }
//                            System.out.println(reading);
//                        } catch (FileNotFoundException e) {
//                            writer.println("File does not exist");
//                            System.out.println("File does not exist");
//                            e.printStackTrace();
//                        }
//
//                        String[] readParts = reading.split("\n");
//
//                        String[] parts = a.split("--");
//                        username = parts[1];
//                        password = parts[2];
//                        if (a.isEmpty() || username.isEmpty() || password.isEmpty()) {
//                            String INVALIDMESSAGEFORMAT = "RESPONSE--LOGIN--INVALIDMESSAGEFORMAT--";
//                            out = socket.getOutputStream();
//                            writer.println(INVALIDMESSAGEFORMAT);
//                        }
//                        if ((readParts) != null) {
//                            for (int i = 0; i < 5; i++) {
//                                String[] fileParts = readParts[i].split(":");
//                                fileusername = fileParts[0];
//                                filepassword = fileParts[1];
//                                score1 = fileParts[2];
//                                score2 = fileParts[3];
//                                score3 = fileParts[4];
//                                System.out.println("line 228");
//                                if (!username.equals(fileusername)) {
//                                    String UNKNOWNUSER = "RESPONSE--LOGIN--UNKNOWNUSER--";
//                                    flag = UNKNOWNUSER;
////                                    out = socket.getOutputStream();
////                                    writer.println(UNKNOWNUSER);
//                                } else if (!password.equals(filepassword)) {
//                                    String INVALIDUSERPASSWORD = "RESPONSE--LOGIN--INVALIDUSERPASSWORD--";
//                                    flag = INVALIDUSERPASSWORD;
////                                    out = socket.getOutputStream();
////                                    writer.println(INVALIDUSERPASSWORD);
//                                } else if (Server.loginUsers.contains(username)) {
//                                    String USERALREADYLOGGEDIN = "RESPONSE--LOGIN--INVALIDUSERPASSWORD--";
//                                    flag = USERALREADYLOGGEDIN;
////                                    out = socket.getOutputStream();
////                                    writer.println(USERALREADYLOGGEDIN);
//                                    //USERALREADYLOGGEDIN
//                                } else {
//                                    userToken = generateRandomString();
//                                    String success = "RESPONSE--LOGIN--SUCCESS--" + userToken;
//                                    flag = success;
//                                }
//
////                                    out = socket.getOutputStream();
////                                    loginUsers.add(username);
////                                    System.out.println(success);
////                                    writer.println(success);
//                                break;
//
//                            }
//                            out = socket.getOutputStream();
//                            Server.loginUsers.add(0, username);
//                            System.out.println(flag);
//                            writer.println(flag);
//
//                            synchronized(listener.onlineUsers){
//                                listener.onlineUsers.put(username,this);
//                            }
//                            System.out.println(username + userToken);
//                        }
//                    }
//                    if (a.contains("STARTNEWGAME")) {
//                        System.out.println(a + " line 215");
//
//                        String[] parts = a.split("--");
//                        String loggedUSERtoken = parts[1];
//                        try {
//                            if (!userToken.equals(loggedUSERtoken)) {
//                                String USERNOTLOGGEDIN = "RESPONSE--STARTNEWGAME--USERNOTLOGGEDIN--";
//                                out = socket.getOutputStream();
//                                writer.println(USERNOTLOGGEDIN);
//                            } else {
//                                gametoken = randomGameToken();
//                                Server.gameTKN(gametoken);
//                                String SUCCESS = "RESPONSE--STARTNEWGAME--SUCCESS--" + gametoken;
//                                out = socket.getOutputStream();
//                                writer.println(SUCCESS);
////                                new GameThread(this).start();
//                                Server.LEADER.add(0, this);
//                                SENDquestion=sendnewgameword();
//                                gameThread = new GameThread(this);
//                                //Q&A 만들기
//                            }
//                        } catch (Exception e) {
//                            String FAILURE = "RESPONSE--STARTNEWGAME--FAILURE--";
//                            out = socket.getOutputStream();
//                            writer.println(FAILURE);
//                            e.printStackTrace();
//                        }
//                    }
//
//                    if (a.startsWith("JOINGAME")) {
//                        System.out.println(a + " line 265");
//
//                        String[] parts = a.split("--");
//                        String loggedUSERtoken = parts[1];
//                        String loggedGAMEtoken = parts[2];
//                        System.out.println(Server.gametoken);
//                        this.gametoken = Server.gametoken;
//                        System.out.println(Server.LEADER + " leader?");
//                        try {
//                            if (!userToken.equals(loggedUSERtoken)) {
//                                String USERNOTLOGGEDIN = "RESPONSE--JOINGAME--USERNOTLOGGEDIN--";
//                                out = socket.getOutputStream();
//                                writer.println(USERNOTLOGGEDIN);
//                            } else if (!gametoken.contains(loggedGAMEtoken)) {
//                                String GAMEKEYNOTFOUND = "RESPONSE--JOINGAME--GAMEKEYNOTFOUND--";
//                                out = socket.getOutputStream();
//                                writer.println(GAMEKEYNOTFOUND);
//                            } else {
//                                String success = "RESPONSE--JOINGAME--SUCCESS--" + gametoken;
//                                serverleader = (ServerThread) Server.LEADER.get(0);
//                                System.out.println(serverleader);
//                                if (serverleader != null) {
//                                    sendLeaderMsg(serverleader);
//                                }
//                                out = socket.getOutputStream();
//                                writer.println(success);
//                                isLeader = false;
//                                Server.loginUsers.add(1, username);
//                                break;
//                            }
//                        } catch (Exception e) {
//                            String FAILURE = "RESPONSE--STARTNEWGAME--FAILURE--";
//                            out = socket.getOutputStream();
//                            writer.println(FAILURE);
//                            e.printStackTrace();
//                        }
//                    } else if (a.startsWith("ALLPARTICIPANTSHAVEJOINED")) {
//                        String[] parts = a.split("--");
//                        String loggedUSERtoken = parts[1];
//                        String loggedGAMEtoken = parts[2];
//                        System.out.println(Server.gametoken);
//                        this.gametoken = Server.gametoken;
//                        System.out.println(Server.loginUsers);
//                        try {
//                            if (!userToken.equals(loggedUSERtoken)) {
//                                String USERNOTLOGGEDIN = "RESPONSE--ALLPARTICIPANTSHAVEJOINED--USERNOTLOGGEDIN--";
//                                out = socket.getOutputStream();
//                                writer.println(USERNOTLOGGEDIN);
//                            } else if (!gametoken.equals(loggedGAMEtoken)) {
//                                String GAMEKEYNOTFOUND = "RESPONSE--ALLPARTICIPANTSHAVEJOINED--GAMEKEYNOTFOUND--";
//                                out = socket.getOutputStream();
//                                writer.println(GAMEKEYNOTFOUND);
//                            } else if (Server.gametoken != loggedGAMEtoken) {
////                                String USERNOTGAMELEADER = "RESPONSE--ALLPARTICIPANTSHAVEJOINED--USERNOTGAMELEADER--";
//                                gameThread.start();
//                                System.out.println(SENDquestion);
//                            }
//
//                        } catch (Exception e) {
//                            String USERNOTGAMELEADER = "RESPONSE--ALLPARTICIPANTSHAVEJOINED--USERNOTGAMELEADER--";
//                            writer.println(USERNOTGAMELEADER);
//                        }
//                    }
//                }
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Server.loginUsers.remove(this);// 사용자 한 명 지우기
//            try {
//                reader.close();
//                writer.close();
//                socket.close();
//            } catch (Exception e) {
//            }
//        }
//    }
//
//    private static final String CHAR_LIST =
//            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//    private static final String CHAR_LIST_GAME =
//            "abcdefghijklmnopqrstuvwxyz";
//    private static final int RANDOM_STRING_LENGTH = 10;
//
//    public String generateRandomString() {
//
//        StringBuffer randStr = new StringBuffer();
//        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
//            int number = getRandomNumber();
//            char ch = CHAR_LIST.charAt(number);
//            randStr.append(ch);
//        }
//        return randStr.toString();
//    }
//
//    public String randomGameToken() {
//
//        StringBuffer strGame = new StringBuffer();
//        for (int i = 0; i < 3; i++) {
//            int number = getRandomNumberGame();
//            char ch = CHAR_LIST_GAME.charAt(number);
//            strGame.append(ch);
//        }
//        return strGame.toString();
//    }
//
//    private int getRandomNumber() {
//        int randomInt = 0;
//        Random randomGenerator = new Random();
//        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
//        if (randomInt - 1 == -1) {
//            return randomInt;
//        } else {
//            return randomInt - 1;
//        }
//    }
//
//    private int getRandomNumberGame() {
//        int randomInt = 0;
//        Random randomGenerator = new Random();
//        randomInt = randomGenerator.nextInt(CHAR_LIST_GAME.length());
//        if (randomInt - 1 == -1) {
//            return randomInt;
//        } else {
//            return randomInt - 1;
//        }
//    }
//
//    public synchronized void sendLeaderMsg(ServerThread fms) {
//        writer1 = fms.getWriter();
//        String NEWPARTICIPANT = "NEWPARTICIPANT--" + username + "--" + score1;
//        writer1.println(NEWPARTICIPANT);
//    }
//    public synchronized void sendGameMsg(ServerThread fms) {
//        writer1 = fms.getWriter();
//        writer1.println(SENDquestion);
//    }
//
//    public PrintWriter getWriter() {
//        return writer;
//    }
//
//    File file = new File("/Users/student/IdeaProjects/Week13 20161121/src/WordleDeck");
//    FileReader f = null;
//    BufferedReader br;
//    String line1 = null;
//    Scanner sa = null;
//    boolean isOpen = true;
//    int count = 0;
//    String line2;
//    HashMap wordlist = new HashMap();
////    Hashtable wordsList = new Hashtable();
//
//    public void readfile() {
//        try {
//
//            try {
//                BufferedReader bufferRead = new BufferedReader(new FileReader(new File("/Users/student/IdeaProjects/Week13 20161121/src/WordleDeck")));
//                String lineread;
//                String reading = "";
//                while ((lineread = bufferRead.readLine()) != null) {
//                    reading += lineread;
//                    reading += "\n";
//                }
//                String[] readParts = reading.split("\n");
//                for (int i = 0; i < readParts.length; i++) {
//                    wordlist.put(i, readParts[i]);
//                }
//            } catch (FileNotFoundException e) {
//                writer.println("File does not exist");
//                System.out.println("File does not exist");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String sendnewgameword() {
//        BufferedReader reader = null;
//        PrintWriter writer = null;
//        try {
//            readfile();
//            String as = (String) wordlist.get(coutnques);
//            if (as != null) {
//                String[] qa = as.split(":");
//                QAlist.put(qa[0], qa[1]);
//                coutnques++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return SENDQUESTIONUNSWER;
//    }
//    public synchronized int getScore(int score){
//        this.score += score;
//        return this.score;
//    }
//    public synchronized HashMap getOnlineUsers(){
//        return listener.onlineUsers;
//    }
//    public synchronized ServerThread getLeadername(String msg){
//        return listener.leader.get(msg);
//    }
//
//    public synchronized int getFool(int fool){
//        numTimesFooledOthers += fool;
//        return numTimesFooledOthers;
//    }
//
//    public synchronized int getFooledByOthers(int fooled){
//        numTimesFooledByOthers += fooled;
//        return numTimesFooledByOthers;
//    }
//    public HashMap listQA(){
//        return wordlist;
//    }
//
//    public synchronized void sendMsg(String msg){
//        writer.println(msg);
//        System.out.println("Send Message to: " + username);
//    }
//    public synchronized String getUsername(){
//        return username;
//    }
//    public synchronized String readMsg(){
//        String msg = null;
//        while(msg == null){
//            try {
//                msg = in.readLine();
//                if(msg !=null){
//                    System.out.println("Read from the player/client: " + msg);
//                }
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                System.err.println("Error reading from server for user: " + e.getMessage());
//                System.exit(1);
//            }
//        }
//        return msg;
//    }
//    public synchronized HashMap getChoice(){
//        return GameThread.hashmapchoice;
//    }
//
//    public synchronized HashMap getSuggestion(){
//        return GameThread.hashmapsuggestion;
//    }
//
//
////    public void sendAllClient(String msg) {// 모두에게 채팅 내용 전송
////        try {
////            int size = Server.loginUsers.size();// 채팅 사용자의 수를 얻는다.
////            for (int i = 0; i < size; i++) {
////                // 반복문을 돌면서 한 사람씩 채팅 내용을 보낸다.
////                ServerThread chr = new ServerThread(listener, socket);
////                chr.writer.println(msg); // 사용자 한 사람의 채팅 내용 전송
////                chr.writer.flush(); // 버퍼의 내용을 즉시 전송하라.
////            }// for
////        } catch (Exception e) {
////            e.printStackTrace();
////        }// catch
//}// send_All
//
///*
//
//        private void sendMsg(String msg) {
//
//            // clients의 Key값을 받아서 String 배열로선언
//            Iterator<String> it = clients.keySet().iterator();
//
//            // Return 할 key값이 없을때까지
//            while (it.hasNext()) {
//                try {
//                    OutputStream dos = clients.get(it.next());
//                    // System.out.println(msg);
//                    DataOutputStream output = new DataOutputStream(dos);
//                    output.writeUTF(msg);
//
//                } catch (IOException e) {
//                    System.out.println(e);
//                }
//            }
//        }
// */