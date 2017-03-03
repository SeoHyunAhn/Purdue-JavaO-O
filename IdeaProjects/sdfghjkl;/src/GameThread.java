import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zhangjunyi on 16/11/30.
 */

public class GameThread extends Thread {


    HashMap<String, String> hashmapsuggestion = new HashMap<>();
    HashMap<String, String> hashmapchoice = new HashMap<>();
    //    HashMap<String, Integer> hashmapCumulativeScore= new HashMap<>();
//    HashMap<String, Integer> hashmapfoolOthers= new HashMap<>();
//    HashMap<String, Integer> hashmapfoolByOthers= new HashMap<>();
    HashMap<String, String> hashmapmsg = new HashMap<>();

//    File file = new File("WordleDeck.txt");
//    FileReader f = null;
////    Scanner sa = null;
//    BufferedReader br;
//    String line1 = null;
    // int cumulativeScore = 0;

    String correctanswer;


    String msg = "";
    String msg2 = "";

    ServerThread serverThread = null;
//    Socket socket = null;
//    Server listener = null;

    public GameThread(Server theServer, Socket clientSocket) {
        this.serverThread.listener = theServer;
        this.serverThread.socket = clientSocket;
    }
    File file = new File("WordleDeck.txt");
    FileReader f = null;
    BufferedReader br;
    String line1 = null;
    Scanner sa = null;
    boolean isOpen = true;
    int count = 0;
    String line2;

    public void run() {
        if (count == 0) {
            if (isOpen) {
                readfile();
                sendnewgameword();
                isOpen = false;
            }
            collectsuggestions();
            count++;
            sendroundoptions();
        }
        if (count == 1) {
            collectchoices();
            applygamelogicandsendresults();
            count++;
        }
        if (count == 2) {
            nexttound();
        }
        quitgame();


    }

    public void readfile() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
//                int count = 0;
//                Scanner sa = null;
//                try {
//                    sa = new Scanner(file);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                while (sa.hasNextLine()) {
//                    count++;
//                    sa.nextLine();
//                }
                try {
                    f = new FileReader(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                br = new BufferedReader(f);

                try {
                    line1 = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                String[] parts = line1.split(":");
//                String SENDQUESTIONUNSWER = "NEWGAMEWORD--" + parts[0] + "--" + parts[1];
//                serverThread.out = serverThread.socket.getOutputStream();
//                writer.print(SENDQUESTIONUNSWER);
//                correctanswer = parts[1];

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendnewgameword() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
                String[] parts = line1.split(":");
                String SENDQUESTIONUNSWER = "NEWGAMEWORD--" + parts[0] + "--" + parts[1];
                serverThread.out = serverThread.socket.getOutputStream();
                writer.print(SENDQUESTIONUNSWER);
                correctanswer = parts[1];
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void collectsuggestions() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
                if (serverThread.a.startsWith("PLAYERSUGGESTION")) {
                    String[] parts = serverThread.a.split("--");
                    String suggestion = parts[3];
                    String token1 = parts[1];
                    String gametoken1 = parts[2];
                    if (!token1.equals(serverThread.token)) {
                        String USERNOTLOGGEDIN = "RESPONSE--PLAYERSUGGESTION--USERNOTLOGGEDIN--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(USERNOTLOGGEDIN);
                    } else if (!gametoken1.equals(serverThread.gametoken)) {
                        String INVALIDGAMETOKEN = "RESPONSE--PLAYERSUGGESTION--INVALIDGAMETOKEN--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(INVALIDGAMETOKEN);
                    } else if (serverThread.a.isEmpty() || serverThread.token.isEmpty() || serverThread.gametoken.isEmpty() || suggestion.isEmpty()) {
                        String INVALIDMESSAGEFORMAT = "RESPONSE--PLAYERSUGGESTION--INVALIDMESSAGEFORMAT--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(INVALIDMESSAGEFORMAT);

                    } else {
                        hashmapsuggestion.put(serverThread.username, suggestion);

                    }
                } else {
                    String UNEXPECTEDMESSAGETYPE = "RESPONSE--PLAYERSUGGESTION--UNEXPECTEDMESSAGETYPE--";
                    serverThread.out = serverThread.socket.getOutputStream();
                    writer.print(UNEXPECTEDMESSAGETYPE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendroundoptions() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
                String v = null;
                for (String value : hashmapsuggestion.values()) {
                    v = v + "--" + value;
                }
                String SENDROUNDOPTIONS = "ROUNDOPTIONS--" + v;
                serverThread.out = serverThread.socket.getOutputStream();
                writer.print(SENDROUNDOPTIONS);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectchoices() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
                if (serverThread.a.startsWith("PLAYERCHOICE")) {
                    String[] parts = serverThread.a.split("--");
                    String choice = parts[3];
                    String token1 = parts[1];
                    if (!token1.equals(serverThread.token)) {
                        String USERNOTLOGGEDIN = "RESPONSE--PLAYERCHOICE--USERNOTLOGGEDIN--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(USERNOTLOGGEDIN);
                    } else if (!token1.equals(serverThread.gametoken)) {
                        String INVALIDGAMETOKEN = "RESPONSE--PLAYERCHOICE--INVALIDGAMETOKEN--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(INVALIDGAMETOKEN);
                    } else if (serverThread.a.isEmpty() || serverThread.token.isEmpty() || serverThread.gametoken.isEmpty() || choice.isEmpty()) {
                        String INVALIDMESSAGEFORMAT = "RESPONSE--PLAYERCHOICE--INVALIDMESSAGEFORMAT--";
                        serverThread.out = serverThread.socket.getOutputStream();
                        writer.print(INVALIDMESSAGEFORMAT);

                    } else {
                        hashmapchoice.put(serverThread.username, choice);
                        serverThread.hashmapCumulativeScore.put(serverThread.username, serverThread.cumulativeScore);

                    }
                } else {
                    String UNEXPECTEDMESSAGETYPE = "RESPONSE--PLAYERCHOICE--UNEXPECTEDMESSAGETYPE--";
                    serverThread.out = serverThread.socket.getOutputStream();
                    writer.print(UNEXPECTEDMESSAGETYPE);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void applygamelogicandsendresults() {
        Integer a = 0;
        Integer b = 0;
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);

                for (String choice : hashmapchoice.values()) {
                    if (hashmapchoice.get(getKeyFromValue(hashmapchoice, choice)).equals(correctanswer)) {
                        msg = "You got it right!";
                        hashmapmsg.put((String) getKeyFromValue(hashmapchoice, choice), msg);
                        serverThread.hashmapCumulativeScore.put((String) getKeyFromValue(hashmapchoice, choice), serverThread.hashmapCumulativeScore.get(getKeyFromValue(hashmapchoice, choice)) + 10);
                    }
                }
                int y = 0;
                for (String suggestion : hashmapsuggestion.values()) {
                    for (String choice : hashmapchoice.values()) {
                        if (choice.equals(suggestion)) {
                            serverThread.hashmapCumulativeScore.put((String) getKeyFromValue(hashmapsuggestion, suggestion), serverThread.hashmapCumulativeScore.get(getKeyFromValue(hashmapsuggestion, suggestion)) + 5);
                            a = serverThread.hashmapfoolByOthers.get(getKeyFromValue(hashmapchoice, choice)) + 1;
                            serverThread.hashmapfoolByOthers.put((String) getKeyFromValue(hashmapchoice, choice), a);
                            b = serverThread.hashmapfoolOthers.get(getKeyFromValue(hashmapsuggestion, suggestion)) + 1;
                            serverThread.hashmapfoolOthers.put((String) getKeyFromValue(hashmapsuggestion, suggestion), b);
                            if (msg == null) {
                                msg = "You fooled player " + getKeyFromValue(hashmapchoice, choice);
                            } else {
                                msg = msg + "You fooled player " + getKeyFromValue(hashmapchoice, choice);
                            }
                            hashmapmsg.put((String) getKeyFromValue(hashmapsuggestion, suggestion), msg);
                            msg2 = "You were fooled by player " + getKeyFromValue(hashmapsuggestion, suggestion);
                            hashmapmsg.put((String) getKeyFromValue(hashmapchoice, choice), msg2);

                        }
                    }
                }
                String x = "";
                for (String m : hashmapmsg.values()) {
                    x = "ROUNDRESULT--" + getKeyFromValue(hashmapmsg, m) + "--" + m + "--" +
                            "--" + serverThread.hashmapCumulativeScore.get(getKeyFromValue(hashmapmsg, m)) + "--" +
                            serverThread.hashmapfoolOthers.get(getKeyFromValue(hashmapmsg, m)) + "--" +
                            serverThread.hashmapfoolByOthers.get(getKeyFromValue(hashmapmsg, m));
                }
                serverThread.out = serverThread.socket.getOutputStream();
                writer.print(a);


                FileReader fr = null;
                File f = new File("UserDatabase.txt");
                try {
                    fr = new FileReader(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BufferedReader br = new BufferedReader(fr);

                try {
                    line2 = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (!line2.equals(null)) {


                    String part[] = line2.split(":");
                    for (Integer cumulative : serverThread.hashmapCumulativeScore.values()) {
                        if (getKeyFromValue(serverThread.hashmapCumulativeScore, cumulative) == part[0]) {

                            line2.replace(part[2], cumulative.toString());
                            line2.replace(part[3], serverThread.hashmapfoolOthers.get(part[0]).toString());
                            line2.replace(part[4], serverThread.hashmapfoolByOthers.get(part[0]).toString());
                        }
                    }

                }


            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public void nexttound() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);

                line1 = null;
                try {
                    line1 = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line1 != null) {
                    count = 0;
                    isOpen = true;
                } else {
                    String GAMEOVER = "GAMEOVER";
                    serverThread.out = serverThread.socket.getOutputStream();
                    writer.print(GAMEOVER);
                    writer.close();
                    f.close();
                    br.close();
                    serverThread.fileReader.close();
                    serverThread.out.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitgame() {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(serverThread.socket.getInputStream()));
                writer = new PrintWriter(serverThread.socket.getOutputStream(), true);
                if (serverThread.a.startsWith("LOGOUT")) {

                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
