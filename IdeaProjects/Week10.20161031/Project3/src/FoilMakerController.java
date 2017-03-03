
import org.apache.commons.net.SocketClient;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Vector;

/**
 * Created by student on 10/25/16.
 */
public class FoilMakerController {
    PrintWriter out;
    InputStreamReader isr;
    BufferedReader in;
    String serverMessage;
    FoilMakerModel model;
    FoilMakerView view;
    String msg, requestUser, USERTOKEN, requestNewGame, GAMETOKEN, requestJoinGame, participantGame, submissionString, username;
    public String[] parts = null;
    public String[] hello = null;
    Socket socket;
//    boolean isRegister = false;

    public FoilMakerController() {
        this.view = new FoilMakerView(this);
        this.model = new FoilMakerModel(this);
        connecttoServer();
    }
    String serverIP = "localhost";
    int serverPort = 9999;
    public void connecttoServer() {

        try {
            socket = new Socket(serverIP, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
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
        String received1 = receiveMessage();//ろ胳 瓣舶 method  赴 瓣舶措 瓴
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
        String received2 = receiveMessage();//ろ胳 瓣舶 method  赴 瓣舶措 瓴
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

    public void newGameLeader() throws IOException {
        msg = "STARTNEWGAME";
        GameRequest(msg, USERTOKEN);
        sendMessage(requestNewGame);
        String received1 = receiveMessage();//ろ胳 瓣舶 method  赴 瓣舶措 瓴
        System.out.println(received1);
        hello = received1.split("--");
//        if (hello[2].equals("USERNOTLOGGEDIN")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Token");
//        } else if (hello[2].equals("FAILURE")) {
//            view.start_JoinGameView_BottomLabel.setText("User already taken, or failed to make game");
//        } else
        if (hello[2].equals("SUCCESS")) {
            //change button able to see here
            view.possibleSuccess(hello[2]);
            view.start_A_NewGame_KeyView_TopLabel.setText(username);
            bottom = view.setStatusMessage("Created Game. Press <StartGame> to Start");
//
//            view.start_A_NewGame_KeyView_KeyTextField.setText(hello[3]);
//            view.start_A_NewGame_KeyView_Botton.setEnabled(false);
//            view.setStatusMessage("Created Game. Press <StartGame> to Start");
            model.GameToken(hello[3]);
        }
    }
    public void responseNewParticipant() throws IOException {
        socket.setSoTimeout(5);
        String output = null;
        while(output==null){
            try {
                output = in.readLine();
            }catch (SocketTimeoutException ste){}
            view.repaint();
        }
        try{
            Thread.sleep(6);
        }catch (InterruptedException sdf){
            sdf.printStackTrace();
        }
        socket.setSoTimeout(0);
        if (output.contains("NEWPARTICIPANT")){
            System.out.println(output);
            int firstIndex="NEWPARTICIPANT--".length();
            int lastIndea=receiveMessage().lastIndexOf("-");
            String newParticipant= receiveMessage().substring(firstIndex, lastIndea-1);
            String totalscore= receiveMessage().substring(lastIndea+1);
            view.setParticipantsText(newParticipant + "-"+ totalscore);
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
        String received1 = receiveMessage();//ろ胳 瓣舶 method  赴 瓣舶措 瓴
        System.out.println(received1);
        String[] parts = received1.split("--");
//        if (parts[2].equals("USERNOTLOGGEDIN")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Token");
//        } else if (parts[2].equals("GAMEKEYNOTFOUND")) {
//            view.start_JoinGameView_BottomLabel.setText("Invalid Game Token");
//        } else if (parts[2].equals("FAILURE")) {
//            view.start_JoinGameView_BottomLabel.setText("Game already started");
//        } else
        if (parts[2].equals("SUCCESS")) {
            view.start_JoinGameView_BottomLabel.setText("Game joined: waiting for leader");
            view.start_A_NewGame_KeyView_TextArea.append(username);
                //NEWPARTICIPANT--<username>--<score>
            }
        }

    public String sendMessage(Socket socket, String message) throws IOException {
        // Create stream writer/reader
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Send message to server
        out.println(message);

        // Set socket timeout to 1 msec before attempting to read response
        socket.setSoTimeout(1);

        String output = null;

        // Loop until the response is received
        while (output == null) {
            try {
                output = in.readLine();
            } catch (SocketTimeoutException e) {
                // Do nothing
            }

            // Update UI (maybe this is not required but it won't hurt)
            view.start_A_NewGame_KeyView_TextArea.append(username);

            // Wait 1 msec before retrying
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Make socket blocking
        socket.setSoTimeout(0);

        // Return server response
        return output;
    }

    public void JoinGameRequest(String msg, String tk, String Gametk) {
        requestJoinGame = msg + "--" + tk + "--" + Gametk;
    }

    public void launchNewGame() {
        msg = "ALLPARTICIPANTSHAVEJOINED";
        AllParticipantRequest(msg, USERTOKEN, GAMETOKEN);
        sendMessage(participantGame);
        String recieved1 = receiveMessage();
        System.out.println(recieved1);
        if (recieved1.contains("GAMEOVER")){
            System.exit(99);
        }
        String[] parts = recieved1.split("--");
//        if (parts[2].equals("USERNOTLOGGEDIN")) {
//            view.setStatusMessage("Invalid\tuser");
//        } else if (parts[2].equals("INVALIDGAMETOKEN")) {
//            view.setStatusMessage("Invalid\tGame Token");
//        } else if (parts[2].equals("USERNOTGAMELEADER")) {
//            view.setSt
// atusMessage("Game already started");
//        } else {
        if (!parts[2].equals("USERNOTLOGGEDIN")&& !parts[2].equals("INVALIDGAMETOKEN")&&!parts[2].equals("USERNOTGAMELEADER")){
//            view.questionMaker(parts[1], parts[2]);
            view.launchGame_WhatIsTheWordForView_TextArea.append(parts[1]);
            view.layout.show(view.mainPanel,view.stringlaunchGame_WhatIsTheWordForView_MainPanel);
        }
    }

    public void AllParticipantRequest(String msg, String tk, String Gametk) {
        participantGame = msg + "--" + tk + "--" + Gametk;
    }

    public void sendSubmission(String suggestion) {
        msg = "PLAYERSUGGESTION";
        sendSubmissionMessage(msg, USERTOKEN, GAMETOKEN, suggestion);
        sendMessage(submissionString);
        String recieved1 = receiveMessage();
        String[] parts = recieved1.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("Invalid\tuser\ttoken");
        } else if (parts[2].equals("INVALIDGAMETOKEN")) {
            view.setStatusMessage("Invalid\tgame\ttoken");
        } else if (parts[2].equals("UNEXPECTEDMESSAGETYPE")) {
            view.setStatusMessage("Wrong message type");
        } else if (parts[2].equals("INVALIDMESSAGEFORMAT")) {
            view.setStatusMessage("Wrong message format");
        } else {
//            for (int i=0; i<parts.length; i++)
//                if (parts[i]!="ROUNDOPTIONS")
            view.getuseranswer(parts[1], parts[2], parts[3]);

        }
    }

    public void sendSubmissionMessage(String msg, String usertk, String gametk, String suggestion) {
        submissionString = msg + "--" + usertk + "--" + gametk + "--" + suggestion;
    }

    public void sendPlayerChoice(String choice) {
        msg = "PLAYERCHOICE";
        sendPlayerMessage(msg, USERTOKEN, GAMETOKEN, choice);
        sendMessage(submissionString);
        String recieved1 = receiveMessage();
        String[] parts = recieved1.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("Invalid\tuser\ttoken");
        } else if (parts[2].equals("INVALIDGAMETOKEN")) {
            view.setStatusMessage("Invalid\tgame\ttoken");
        } else if (parts[2].equals("UNEXPECTEDMESSAGETYPE")) {
            view.setStatusMessage("Wrong message type");
        } else if (parts[2].equals("INVALIDMESSAGEFORMAT")) {
            view.setStatusMessage("Wrong message format");
        } else {
            int count = 0;
            for (int i=1; i< parts.length; i++){
                if (!parts[i].equals(null) && !parts[i].isEmpty())
                    count++;
                if (count==5)
                    for (int j=0; j<parts.length; j++)
                    String[] asd = parts
            }


            //ROUNDRESULT--Bob--You	were	fooled	by	Alice.--0--0--1--Alice--You	got	it	right!.You
            //fooled	Bob.--15--1ü0
            view.recievedResults(parts[2], parts[3], parts[4], parts[5], parts[7], parts[8], parts[9], parts[10]);
            //                  statement, score, fooled?, fooledcount, clientname, statement, score, fooled?, fooledcount
        }
    }

    public void sendPlayerMessage(String msg, String usertk, String gametk, String suggestion) {
        submissionString = msg + "--" + usertk + "--" + gametk + "--" + suggestion;
    }

    public void quitGame() {
        sendMessage("LOGOUT--");
        String recieved = receiveMessage();
        String[] parts = recieved.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("User\tcurrently\tnot\tlogged\tin");
        } else if (parts[2].equals("SUCCESS")) {
            view.setStatusMessage("Logout Successful");
            System.exit(0);
        }
    }
}
