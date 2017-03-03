
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    String msg, requestUser, USERTOKEN, requestNewGame, GAMETOKEN, requestJoinGame, participantGame, submissionString;

    public FoilMakerController() {
        this.view = new FoilMakerView(this);
        this.model = new FoilMakerModel(this);
        connecttoServer();
    }

    public void connecttoServer() {
        String serverIP = "localhost";
        int serverPort = 9999;
        try {
            Socket socket = new Socket(serverIP, serverPort);
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

    public void loginUser(String name, String password) {
        msg = "LOGIN";
        userRequest(msg, name, password);
        sendMessage(requestUser);
        String received1 = receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
        System.out.print(received1);
        String[] parts = received1.split("--");
        view.possibleSuccess(parts[2]);
        if (parts[2].equals("INVALIDMESSAGEFPRMAT")) {
            view.setStatusMessage("Invalid format");
        } else if (parts[2].equals("UNKNOWNUSER")) {
            view.setStatusMessage("Invalid user");
        } else if (parts[2].equals("INVALIDUSERPASSWORD")) {
            view.setStatusMessage("Invalid Password");
        } else if (parts[2].equals("USERALREADYLOGGEDIN")) {
            view.setStatusMessage("User already logged in");
        } else if (parts[2].equals("SUCCESS")) {
            view.setStatusMessage(name + " logging in");
            model.userCookie(parts[3]);
            model.getUser(name, password);
        }
    }

    public void registerUser(String name, String password) {
        msg = "CREATENEWUSER";
        userRequest(msg, name, password);
        sendMessage(requestUser);
        String received2 = receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
        String[] parts = received2.split("--");
        view.possibleSuccess(parts[2]);
        if (parts[2].equals("INVALIDMESSAGEFPRMAT")) {
            view.setStatusMessage("Invalid format");
        } else if (parts[2].equals("INVALIDUSERNAME"))
            view.setStatusMessage("Username is empty");
        else if (parts[2].equals("INVALIDUSERPASSWORD"))
            view.setStatusMessage("Password\tempty");
        else if (parts[2].equals("USERALREADYEXISTS"))
            view.setStatusMessage("User already exist");
        else if (parts[2].equals("SUCCESS")) {
            view.setStatusMessage(name + " logging in");
            model.userCookie(parts[3]);
            model.getUser(name, password);
        }
    }

    public void userRequest(String msg, String username, String password) {
        requestUser = msg + "--" + username + "--" + password;
    }

    public void newGameLeader() {
        msg = "STARTNEWGAME";
        GameRequest(msg, USERTOKEN);
        sendMessage(requestNewGame);
        String received1 = receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
        String[] parts = received1.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("Invalid Token");
        } else if (parts[2].equals("FAILURE")) {
            view.setStatusMessage("User already taken, or failed to make game");
        } else if (parts[2].equals("SUCCESS")) {
            //change button able to see here
            view.start_A_NewGame_KeyView_Botton.setEnabled(true);
            view.setStatusMessage("Created Game. Press <StartGame> to Start");
            model.GameToken(parts[3]);
        }
    }

    public void GameRequest(String msg, String tk) {
        requestNewGame = msg + "--" + tk;
    }

    public void joinGame() {
        msg = "JOINGAME";
        JoinGameRequest(msg, USERTOKEN, GAMETOKEN);
        sendMessage(requestJoinGame);
        String received1 = receiveMessage();//네트워크랑 연결하는 method 수현식 여기다 연결해놓을 것
        String[] parts = received1.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("Invalid Token");
        } else if (parts[2].equals("GAMEKEYNOTFOUND")) {
            view.setStatusMessage("Invalid Game Token");
        } else if (parts[2].equals("FAILURE")) {
            view.setStatusMessage("Game already started");
        } else if (parts[2].equals("SUCCESS")) {
            view.setStatusMessage("Game joined: waiting for leader");
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
        String[] parts = recieved1.split("--");
        if (parts[2].equals("USERNOTLOGGEDIN")) {
            view.setStatusMessage("Invalid\tuser");
        } else if (parts[2].equals("INVALIDGAMETOKEN")) {
            view.setStatusMessage("Invalid\tGame Token");
        } else if (parts[2].equals("USERNOTGAMELEADER")) {
            view.setStatusMessage("Game already started");
        } else {
            view.questionMaker(parts[1], parts[2]);
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
            //ROUNDRESULT--Bob--You	were	fooled	by	Alice.--0--0--1--Alice--You	got	it	right!.You
            //fooled	Bob.--15--1—0
            view.recievedResults(parts[2], parts[3], parts[4], parts[5], parts[7], parts[8], parts[9], parts[10]);
            //                  statement, score, fooled?, fooledcount, clientname, statement, score, fooled?, fooledcount
        }
    }
    public void sendPlayerMessage(String msg, String usertk, String gametk, String suggestion) {
        submissionString = msg + "--" + usertk + "--" + gametk + "--" + suggestion;
    }

    public void quitGame(){
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


//    public static void main(String[] args) {
////        FoilMakerView es =new FoilMakerView();
//        FoilMakerController control = new FoilMakerController();
//        control.startGame();
//        es.FoilMakerLogin();
//        es.pack();
//        es.setVisible(true);
//        es.setSize(400,600);
    }


//    public void startNewGame(){
//        model.startNewGame();
//        view.startNewGame();
//        showNextPlayerMessage();
//    }
//