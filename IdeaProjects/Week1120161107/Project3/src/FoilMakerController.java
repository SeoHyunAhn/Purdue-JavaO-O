
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by student on 10/25/16.
 */
public class FoilMakerController {
    PrintWriter out;
    InputStreamReader isr;
    BufferedReader in;
    String serverMessage;
    private FoilMakerModel model;
    private FoilMakerView view;
    String msg, requestUser, USERTOKEN, requestNewGame, GAMETOKEN, requestJoinGame, participantGame, submissionString, username, participant, question, answer;
    public String[] parts = null;
    public String[] hello = null;
    //    public String message2[] =null;
    private boolean isLeader = false;
    private messageThread m;
    String toview, message23, toview2;
    Socket socket;
//    boolean isRegister = false;

    public FoilMakerController(FoilMakerView n) {
        this.view = n;
        this.model = new FoilMakerModel(this);

        connecttoServer();
    }

    public String participantGame() {
        return participant;
    }

    public void connecttoServer() {
        String serverIP = "localhost";
        int serverPort = 9999;
        try {
            socket = new Socket(serverIP, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            m = new messageThread(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String receiveMessage() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    String doit = null;

    public void loginUser(String name, String password) {
        msg = "LOGIN";
        userRequest(msg, name, password);
        sendMessage(requestUser);
        String received1 = receiveMessage();//筹ü ｈ method  璧 ｈ舵
        System.out.println("Received" + received1);
        parts = received1.split("--");
//        System.out.println("parts"+parts);
        view.possibleSuccess(parts[2]);
//        if (parts[2].equals("INVALIDMESSAGEFPRMAT")) {
//            view.login_OR_RegisterView_BottomLabel.setText("Invalid format");
//        } else if (parts[2].equals("UNKNOWNUSER")) {
//            view.login_OR_RegisterView_BottomLabel.setText("Invalid user");
//        } else if (parts[2].equals("INVALIDUSERPASSWORD")) {
//            view.login_OR_RegisterView_BottomLabel.setText("Invalid Password");
//        } else if (parts[2].equals("USERALREADYLOGGEDIN")) {
//            view.login_OR_RegisterView_BottomLabel.setText("User already logged in");
//        } else
        if (parts[2].equals("SUCCESS")) {
            username = name;

            doit = view.setStatusMessage(name + " logging in");
            model.userCookie(parts[3]);
            model.getUser(name, password);
            view.start_JoinGameView_TopLabel.setText(name);
            USERTOKEN = parts[3];
        }
    }

    public void registerUser(String name, String password) {
        msg = "CREATENEWUSER";
        userRequest(msg, name, password);
        sendMessage(requestUser);
        String received2 = receiveMessage();//筹ü ｈ method  璧 ｈ舵
        String[] parts = received2.split("--");
        view.possibleSuccess(parts[2]);
//        if (parts[2].equals("INVALIDMESSAGEFPRMAT")) {
//            view.login_OR_RegisterView_BottomLabel.setText("Invalid format");
//        } else if (parts[2].equals("INVALIDUSERNAME"))
//            view.login_OR_RegisterView_BottomLabel.setText("Username is empty");
//        else if (parts[2].equals("INVALIDUSERPASSWORD"))
//            view.login_OR_RegisterView_BottomLabel.setText("Password\tempty");
//        else if (parts[2].equals("USERALREADYEXISTS"))
//            view.login_OR_RegisterView_BottomLabel.setText("User already exist");
//        else
        if (parts[2].equals("SUCCESS")) {
            view.login_OR_RegisterView_BottomLabel.setText(name + " logging in");
            USERTOKEN = parts[3];
            model.userCookie(parts[3]);
            model.getUser(name, password);
        }
    }

    public void userRequest(String msg, String username, String password) {
        requestUser = msg + "--" + username + "--" + password;
    }

    String bottom = null;

    public void newGameLeader() {
        msg = "STARTNEWGAME";
        GameRequest(msg, USERTOKEN);
        sendMessage(requestNewGame);
        String received1 = receiveMessage();//筹ü ｈ method  璧 ｈ舵
        System.out.println(received1);
        hello = received1.split("--");
//        if (hello[2].equals("USERNOTLOGGEDIN")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Token");
//        } else if (hello[2].equals("FAILURE")) {
//            view.start_JoinGameView_BottomLabel.setText("User already taken, or failed to make game");
//        } else
        if (hello[2].equals("SUCCESS")) {
            //change button able to see here
            isLeader = true;
            m.start();
            m.getString();
            view.possibleSuccess(hello[2]);
            view.start_A_NewGame_KeyView_TopLabel.setText(username);
            bottom = view.setStatusMessage("Created Game. Press <StartGame> to Start");
//            view.start_A_NewGame_KeyView_KeyTextField.setText(hello[3]);
//            view.start_A_NewGame_KeyView_Botton.setEnabled(false);
//            view.setStatusMessage("Created Game. Press <StartGame> to Start");
            GAMETOKEN = hello[3];
            model.GameToken(hello[3]);
        }
    }

    public void GameRequest(String msg, String tk) {
        requestNewGame = msg + "--" + tk;
    }

    public void joinGame(String tk) {
        msg = "JOINGAME";
        GAMETOKEN = tk;
        JoinGameRequest(msg, USERTOKEN, GAMETOKEN);
        sendMessage(requestJoinGame);
        String received1 = receiveMessage();//筹ü ｈ method  璧 ｈ舵
        System.out.println(received1);
        parts = received1.split("--");
//        if (parts[2].equals("USERNOTLOGGEDIN")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Token");
//        } else if (parts[2].equals("GAMEKEYNOTFOUND")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Game Token");
//        } else if (parts[2].equals("FAILURE")) {
//            view.start_JoinGameView_BottomLabel.setText("Game already started");
//        } else
        if (parts[2].equals("SUCCESS")) {
            view.start_JoinGameView_BottomLabel.setText("Game joined: waiting for leader");

            //NEWPARTICIPANT--<username>--<score>
        }
    }

    public void JoinGameRequest(String msg, String tk, String Gametk) {
        requestJoinGame = msg + "--" + tk + "--" + Gametk;
    }

    public void launchNewGame() {
        msg = "ALLPARTICIPANTSHAVEJOINED";
        AllParticipantRequest(msg, USERTOKEN, GAMETOKEN);
        sendMessage(participantGame);
        String recieved1 = receiveMessage();
        System.out.println(recieved1 + "    hjk");
        toview = recieved1;
        parts = recieved1.split("--");

        view.launchGame_WhatIsTheWordForView_TextArea.setText(parts[1]);
    }

    public void AllParticipantRequest(String msg, String tk, String Gametk) {
        participantGame = msg + "--" + tk + "--" + Gametk;
    }

    public void sendSubmission(String suggestion) {
        msg = "PLAYERSUGGESTION";
        sendSubmissionMessage(msg, USERTOKEN, GAMETOKEN, suggestion);
        sendMessage(submissionString);
        String recieved1 = receiveMessage();
        parts = recieved1.split("--");
//        if (parts[2].equals("USERNOTLOGGEDIN")) {
//            view.setStatusMessage("Invalid\tuser\ttoken");
//        } else if (parts[2].equals("INVALIDGAMETOKEN")) {
//            view.setStatusMessage("Invalid\tgame\ttoken");
//        } else if (parts[2].equals("UNEXPECTEDMESSAGETYPE")) {
//            view.setStatusMessage("Wrong message type");
//        } else if (parts[2].equals("INVALIDMESSAGEFORMAT")) {
//            view.setStatusMessage("Wrong message format");
//        } else {
//            for (int i=0; i<parts.length; i++)
//                if (parts[i]!="ROUNDOPTIONS")
        view.getuseranswer(parts[1], parts[2], parts[3]);
//
    }

    public void sendSubmissionMessage(String msg, String usertk, String gametk, String suggestion) {
        submissionString = msg + "--" + usertk + "--" + gametk + "--" + suggestion;
    }

    public void sendPlayerChoice(String choice) {
        msg = "PLAYERCHOICE";
        sendPlayerMessage(msg, USERTOKEN, GAMETOKEN, choice);
        sendMessage(submissionString);
        String recieved1 = receiveMessage();
        String recieved2 = receiveMessage();
        String[] parts = recieved1.split("--");
        toview=recieved1;
        toview2=recieved2;
        System.out.println(recieved1 + "   re1");
        System.out.println(recieved2+ "    re2");
        //ROUNDRESULT--Bob--You	were	fooled	by	Alice.--0--0--1--Alice--You	got	it	right!.You
        //fooled	Bob.--15--1眉0
        view.recievedResults(parts[2], parts[3], parts[4], parts[5], parts[7], parts[8], parts[9], parts[10]);
        //                  statement, score, fooled?, fooledcount, clientname, statement, score, fooled?, fooledcount

    }

    public void sendPlayerMessage(String msg, String usertk, String gametk, String suggestion) {
        submissionString = msg + "--" + usertk + "--" + gametk + "--" + suggestion;
    }

    public void quitGame() {
        sendMessage("LOGOUT--");
        String recieved = receiveMessage();
        System.out.println(recieved);
        String[] parts = recieved.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("User\tcurrently\tnot\tlogged\tin");
        } else if (parts[2].equals("SUCCESS")) {
            view.setStatusMessage("Logout Successful");
            System.exit(0);
        }
    }

    class messageThread extends Thread {
        int count = 0;
        private BufferedReader reader;
        private String message23 = "2";
        public String message2[];
        Vector vChatList = new Vector();
        BufferedReader br = null; // 입력 담당
        PrintWriter pw = null; // 출력 담당

        public messageThread(BufferedReader in) throws IOException {
            this.reader = in;
            OutputStream os = socket.getOutputStream();// 출력
            pw = new PrintWriter(new OutputStreamWriter(os));
        }

        public String getString() {
            return message23;
        }

        public void run() {
            try {
                if (isLeader == true) {
//                    while (true) {
                    message23 = reader.readLine();
                    if (message23.contains("NEWPARTICIPANT")) {
                        System.out.println(message23 + " ygtfds");
                        message2 = message23.split("--");
                        participant = message2[1];
                        System.out.println(participant + "   asdfghjkl");
                        view.start_A_NewGame_KeyView_TextArea.setText(participant);
                        isLeader = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}