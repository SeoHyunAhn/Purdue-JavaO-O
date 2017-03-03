import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.Map.Entry;


public class FoilMakerServer extends Thread{
	private boolean isCreated = false,isJoined = false, haveJoiners = false, pass = false;
	private String status, newuser, newpass, username, password, mainMessage = null,result,toClient,line,filePath;
	private PrintWriter opw,pr,opw1;
	private InputStreamReader isr;
	private BufferedReader in,br;
	private FileReader fr;
	private FileWriter fw;
	private boolean isReceived = true, isLogin = false, isLaunched = false;
	private Socket s; //important to know already login, joiner or leader
	private FoilMakerGame g; //important for multiple players
	private String[] messages;
	private FoilMakerDriver d;
	private static String token,key;
	private FoilMakerGame fmg;
	private int score;
	private int numTimesFooledOthers, numTimesFooledByOthers;
	//public static String userDBPath, gameQAPath;
	//for message sending and finding type
	public static enum MSG_TYPE {
		//Client messages to server

		CREATENEWUSER, // Tokens: userName  passWord
		LOGIN, // Tokens: userName password
		LOGOUT, // Tokens:  currentLoginToken?
		STARTNEWGAME, // No tokens?
		JOINGAME, // Tokens: currentLoginToken gameKey
		ALLPARTICIPANTSHAVEJOINED, // Send from leader to server; Tokens: currentLoginToken gameKey

		//Client message to server during a game
		PLAYERCHOICE, // Tokens: currentLoginToken gameKey user'sChoice
		PLAYERSUGGESTION, // Tokens: currentLoginToken gameKey user'sChoice

		// Server messages to client
		NEWPARTICIPANT, //From server to leader; Tokens: participantName cummulativeScore
		RESPONSE, // Server response to user request
		                    /* Tokens:
		                     * clientRequestMsgType -- the MSG_TYPE of the client's request
		                     * responseDetail -- the MSG_DETAIL_T of the server's response
		                     * <Other optional tokens specific to MSG_DETAIL_T>
		                     */


		//Server messages to clients during a game
		NEWGAMEWORD, // From server to players; Tokens: cardFrontText cardBackText
		ROUNDOPTIONS, // From server to players; Tokens: randomized list of user suggestions and true answer
		ROUNDRESULT, //From server to players; Tokens: uName1 score1 message1 uName2 score2 message2 ....
		GAMEOVER // From server to players: Tokens: MSG_DETAIL
	};

	public static enum MSG_DETAIL_T {
		SUCCESS, // Request was successful. For LOGIN: currentLoginToken;  For STARTNEWGAME: gameKey; For JOINGAME:
		// gameKey;
		INVALIDUSERNAME,
		INVALIDUSERPASSWORD,
		USERALREADYEXISTS,
		UNKNOWNUSER,
		USERALREADYLOGGEDIN,
		GAMEKEYNOTFOUND,
		NO_CONNECTION_TO_SERVER,
		ERROR_OPENING_NETWORK_CONNECTION,
		USERNOTLOGGEDIN,
		USERNOTGAMELEADER,
		INVALIDGAMETOKEN,
		UNEXPECTEDMESSAGETYPE,
		INVALIDMESSAGEFORMAT, //TODO received msg with tokens EXPECTING: expected format
		FAILURE // optional details of failure cause
	};

	//TODO Create error codes type and values
	public static final String SEPARATOR = "--";
	public static final int LOGIN_TOKEN_LENGTH = 10;
	public static final int GAME_KEY_LENGTH = 3;
	public static final String FILESEPARATOR = ":";
	public static final String GAMESEPARATOR = ",";


	public FoilMakerServer(FoilMakerDriver d, Socket s) throws IOException{
		this.d = d;
		this.s = s;
		opw = new PrintWriter(s.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		score = 0;
		numTimesFooledOthers = 0;
		numTimesFooledByOthers = 0;
	}

	public void run(){
		while(isReceived){

			//loop until receive the message from the client
			mainMessage = this.readMsg();

			messages = mainMessage.split(SEPARATOR);
			switch(MSG_TYPE.valueOf(messages[0])){
				case CREATENEWUSER:
					newuser = messages[1];
					newpass = messages[2];
					if(messages.length == 3){
						status = CreateNewUser();
						System.out.println(status);
						opw.println("RESPONSE"+ SEPARATOR + messages[0] + SEPARATOR + status + SEPARATOR);
					}
					else{
						opw.println("RESPONSE"+ SEPARATOR + messages[0] + SEPARATOR + "INVALIDMESSAGEFORMAT" + SEPARATOR);
					}
					opw.flush();
					break;

				case LOGIN:
					if(messages.length == 3){
						username = messages[1];
						password = messages[2];
						status = Login();
						if(isLogin){
							Random random = new Random();
							char[] tokens = new char[LOGIN_TOKEN_LENGTH];
							for (int i = 0; i < LOGIN_TOKEN_LENGTH; i++) {
								int result = 33 + random.nextInt(93);
								tokens[i] = (char) result;
							}
							token = new String(tokens);
							synchronized(d.loginList){
								d.loginList.put(username, token);
								synchronized(d.onlineUsers){
									d.onlineUsers.put(username,this);
								}
								System.out.println(username + token);
							}



							opw.println("RESPONSE"+ SEPARATOR + "LOGIN" + SEPARATOR + status + SEPARATOR + token);
						}else{
							opw.println("RESPONSE"+ SEPARATOR + "LOGIN" + SEPARATOR + status + SEPARATOR);
						}
					}
					else{
						opw.println("RESPONSE"+ SEPARATOR + "LOGIN" + SEPARATOR + "INVALIDMESSAGEFORMAT" + SEPARATOR);
					}
					opw.flush();
					break;

				case STARTNEWGAME:
					if(messages.length == 2){
						status = newGameStart();
						if(isCreated){
							key = generateGamekey();
							getQA();
							if(d.setGame(key)){
								synchronized(d.shareJoiners){
									d.shareJoiners.put(username, key);
									fmg = new FoilMakerGame(this);
									synchronized(d.multiGame){
										d.multiGame.put(key, fmg);
									}
									synchronized(d.leader){
										d.leader.put("LEADER", this);
									}
								}
							}
							synchronized(opw){
								opw.println("RESPONSE"+ SEPARATOR + "STARTNEWGAME" + SEPARATOR + status + SEPARATOR + key);
							}
						}

					}else{
						synchronized(this){
							opw.println("RESPONSE"+ SEPARATOR + messages[0] + SEPARATOR + "INVALIDMESSAGEFORMAT" + SEPARATOR);
						}
					}
					opw.flush();
					break;

				case JOINGAME:
					if(messages.length == 3){
						status = joinGame();
						System.out.println(status);
						if(isJoined){
							synchronized (d.shareJoiners) {
								d.shareJoiners.put(username, messages[2]);
								fmg = d.getGame(messages[2]);
								FoilMakerServer fmsLeader = d.leader.get("LEADER");
								if(fmsLeader != null && status == "SUCCESS"){
									sendLeaderMsg(fmsLeader);
									opw.println("RESPONSE"+ SEPARATOR + "JOINGAME" + SEPARATOR + status + SEPARATOR + messages[2]);
								}
							}
							synchronized(fmg){
								try{
									fmg.wait();
								}catch(InterruptedException e){
									e.printStackTrace();
								}
							}
						}
						opw.println("RESPONSE"+ SEPARATOR + "JOINGAME" + SEPARATOR + status + SEPARATOR + messages[2]);

					}else{
						opw.println("RESPONSE"+ SEPARATOR + messages[0] + SEPARATOR + "INVALIDMESSAGEFORMAT" + SEPARATOR);
					}
					opw.flush();
					break;
				case ALLPARTICIPANTSHAVEJOINED :
					if(messages.length == 3){
						lauchGame();
						if(!isLaunched){
							System.out.println(status);
						}

						if(isLaunched){
							fmg.start();
						}

						synchronized(fmg){
							try{
								fmg.wait();
							}catch(InterruptedException e){
								e.printStackTrace();
							}
						}
					}
					else{
						opw.println("RESPONSE"+ SEPARATOR + messages[0] + SEPARATOR + "INVALIDMESSAGEFORMAT" + SEPARATOR);
					}
					opw.flush();
					break;

				case LOGOUT:
					isReceived = false;
					break;
				default:
					opw.println("Unknown request from the client: " + "INVALIDMESSAGEFORMAT");
			}
		}
	}

	public synchronized String CreateNewUser(){
		if(newuser.length() == 0 || newuser.length() >= 10 || (!verifyNewUser(newuser))){
			return "INVALIDUSERNAME";
		}
		else if(newpass.length() == 0 || newpass.length() >= 10 || (!verifyNewPass(newpass))){
			return "INVALIDPASSWORD";
		}
		else{
			if(d.hasUser(newuser)){
				return "USERALREADYEXISTS";
			}
			else{
				pass = true;
			}
		}while(line != null);
		try {
			fw = new FileWriter(d.userDBPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pass){
			pr = new PrintWriter(fw);
			Iterator<Map.Entry<String, String>> i1 = d.getUsers().entrySet().iterator();
			while(i1.hasNext()){
				HashMap.Entry<String, String> entry = i1.next();
				pr.println(entry.getKey() + FILESEPARATOR + entry.getValue()+ FILESEPARATOR +"0" + FILESEPARATOR + "0" + FILESEPARATOR + "0");
				System.out.println(entry.getKey() + entry.getValue());
			}
			pr.println(newuser + FILESEPARATOR + newpass+ FILESEPARATOR +"0" + FILESEPARATOR + "0" + FILESEPARATOR + "0");
			d.setUser(newuser, newpass);
			pr.flush();
			return "SUCCESS";
		}
		return "";
	}


	public boolean verifyNewUser(String user){
		for(int i = 0; i < user.length();i++){
			char[] a = new char[user.length()];
			a[i] = user.charAt(i);
			if((a[i] >= 65 && a[i] <= 90) || (a[i] >= 97 && a[i]<= 122) || a[i] == 95){
				return true;
			}
		}
		return false;
	}

	public synchronized String Login(){

		if(d.hasUser(username)){
			System.out.println(d.verifyPassword(username));
			if(password.equals(d.verifyPassword(username))){
				if(getOnlineUsers().containsKey(username)){
					return "USERALREADYLOGGEDIN";
				}
				else{
					isLogin = true;
					return "SUCCESS";
				}
			}
			else{
				return "INVALIDPASSWORD";
			}
		}
		else{
			return "INVALIDUSERNAME";
		}
	}

	public synchronized String newGameStart(){
		if(d.onlineUsers.containsKey(username)){
			if(!d.shareJoiners.containsValue(username)){
				isCreated = true;
				return "SUCCESS";
			}
			else{
				return "FAILURE";
			}
		}
		else{
			return "USERNOTLOGGEDIN";
		}
	}

	public synchronized String joinGame(){
		if(d.onlineUsers.containsKey(username)){
			if(fmg == null){
				if(d.shareJoiners.containsValue(messages[2])){
					isJoined = true;
					return "SUCCESS";
				}else{
					return "GAMEKEYNOTFOUND";
				}
			}else{
				return "FAILURE";
			}
		}else{
			return "USERNOTLOGGEDIN";
		}
	}


	public boolean verifyNewPass(String password){
		int numUpper = 0;
		int numNum = 0;
		for(int i = 0; i < password.length(); i++){
			char c = password.charAt(i);
			if(c >= 65 && c <= 90){
				numUpper++;
			}
			else if(c >= 48 && c<= 57){
				numNum++;
			}
		}
		if(!password.isEmpty()||password.length()<10||password.matches("[A-Z]")||password.matches("[a-zA-Z]")||password.contains("#")||password.contains("&")||password.contains("$")||password.contains("*")||numUpper >=1 || numNum >=1){

			return true;
		}
		else{
			return false;
		}
	}


	public synchronized HashMap getOnlineUsers(){
		return d.onlineUsers;
	}

	public static String generateGamekey() {
		char[] var0 = new char[3];
		Random var1 = new Random(System.currentTimeMillis());

		for(int var2 = 0; var2 < 3; ++var2) {
			var0[var2] = (char)(97 + var1.nextInt(25));
		}

		return new String(var0);
	}

	public synchronized String getUsername(){
		return username;
	}

	public synchronized void getQA(){
		try {
			fr = new FileReader(d.gameQAPath);
			br = new BufferedReader(fr);
			String qa = "";
			while(qa != null){
				try {
					qa = br.readLine();
					if(qa != null && !qa.equals("")){
						String[] qAndA = qa.split(FILESEPARATOR);
						System.out.println("Question: " + qAndA[0] + "Answer: " + qAndA[1]);
						d.QA.put(qAndA[0], qAndA[1]);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized HashMap getSuggestion(){
		return d.suggestion;
	}

	public synchronized HashMap getChoice(){
		return d.choices;
	}

	public synchronized void sendLeaderMsg(FoilMakerServer fms){
		opw1 = fms.getOpw();
		opw1.println("NEWPARTICIPANT" + SEPARATOR + username + SEPARATOR + "0");
	}

	public BufferedReader getIn(){
		return in;
	}

	public PrintWriter getOpw(){
		return opw;
	}

	public synchronized FoilMakerServer getLeadername(String msg){
		return d.leader.get(msg);
	}
	public synchronized boolean isLeaderToken(String msg){
		for (Entry<String, FoilMakerServer> entry : d.onlineUsers.entrySet()) {
			if(d.leader.get("LEADER") == entry.getValue()){
				String a = entry.getKey();
				if(msg.equals(d.loginList.get(a))){
					return true;
				}else{
					return false;
				}
			}
		}

		return false;
	}

	public String getLeaderKey(){
		return key;
	}
	public void lauchGame(){
		if(!d.leader.get("LEADER").getLeaderKey().equals(messages[2])){
			status = "INVALIDGAMETOKEN";
		}else if(!isLeaderToken(messages[1])){
			status =  "USERNOTLOGGEDIN";
		}else if(this != d.leader.get("LEADER")){
			status =  "USERNOTGAMELEADER";
		}
		else{
			isLaunched = true;
		}
	}

	public HashMap listQA(){
		return d.QA;
	}

	public synchronized void sendMsg(String msg){
		opw.println(msg);
		System.out.println("Send Message to: " + username);
	}

	public synchronized String readMsg(){
		String msg = null;
		while(msg == null){
			try {
				msg = in.readLine();
				if(msg !=null){
					System.out.println("Read from the player/client: " + msg);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error reading from server for user: " + e.getMessage());
				System.exit(1);
			}
		}
		return msg;
	}

	public synchronized int getScore(int score){
		this.score += score;
		return this.score;
	}

	public synchronized int getFool(int fool){
		numTimesFooledOthers += fool;
		return numTimesFooledOthers;
	}

	public synchronized int getFooledByOthers(int fooled){
		numTimesFooledByOthers += fooled;
		return numTimesFooledByOthers;
	}
}