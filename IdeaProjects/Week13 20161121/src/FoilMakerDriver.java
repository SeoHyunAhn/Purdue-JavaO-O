import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class FoilMakerDriver{
	private static int portNum = 9999;
	private boolean isConnected;
	private ServerSocket ss;
	//private ArrayList<FoilMakerServer> clientThread;
	public static String userDBPath, gameQAPath;
	public HashMap<Integer, Socket> onlineClients = new HashMap();
	private int serialNumber;
	private HashMap<String, String> users = new HashMap();
	public HashMap<String, FoilMakerServer> onlineUsers = new HashMap();
	public HashMap<String, String> shareJoiners = new HashMap();
	public HashMap<String, String> suggestion = new HashMap();
	public HashMap<String, String> QA = new HashMap();
	public HashMap<String, FoilMakerServer> leader = new HashMap();
	public HashMap<String, String> choices = new HashMap();
	public HashMap<String, FoilMakerGame> multiGame;
	public HashMap<String, String> loginList = new HashMap();

	public void MultiSetup(){
		try{
			System.out.println("Creating Socket...");
			ss = new ServerSocket(portNum);
			ss.setReuseAddress(true);
			System.out.println("Listening on: " + portNum);
			isConnected = true;

			while(true){
				FoilMakerServer fs = null;
				Socket s = ss.accept();
				synchronized(this){
					onlineClients.put(serialNumber, s);
					serialNumber++;

					fs = new FoilMakerServer(this, s);

					if(fs != null){
						fs.start();
					}
				}
			}
		}catch(IOException e){
			isConnected = false;
			System.out.println("Failed to setup socket :\n" + e.getMessage());
		}finally{
			if(isConnected){
				synchronized(this){
					if(ss != null){
						try {
							ss.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Iterator<Socket> i = onlineClients.values().iterator();

				while(true){
					if(i.hasNext()){
						Socket si;
						if((si= i.next()) != null && !si.isClosed()){
							try {
								si.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public synchronized void setUser(String newuser, String newpass){
		users.put(newuser, newpass);
	}
	public synchronized boolean hasUser(String username){
		return users.containsKey(username);
	}
	public HashMap getUsers(){
		return users;
	}

	public synchronized String verifyPassword(String username){
		return users.get(username);

	}

	public synchronized boolean setGame(String key){
		if(multiGame == null){
			multiGame = new HashMap();
			return true;
		}
		else if(multiGame.containsKey(key)){
			return false;
		}
		else{
			return true;
		}
	}

	public synchronized FoilMakerGame getGame(String key){
		return multiGame.get(key);
	}

	public static void main(String[] args){
		FoilMakerDriver d = new FoilMakerDriver();
		boolean isClosed = false;
		try{
			userDBPath = "/Users/student/IdeaProjects/Week13 20161121/src/UserDatabase";
			gameQAPath = "/Users/student/IdeaProjects/Week13 20161121/src/WordleDeck";

			HashMap<String, String> db = new HashMap();
			FileReader fr =null;
			BufferedReader br =null;
			String line = "";
			try {
				fr = new FileReader(userDBPath);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			br = new BufferedReader(fr);
			try {
				while(line != null){
					line = br.readLine();
					if(line != null){
						String[] info = line.split(":");
						String user = info[0];
						String pass = info[1];
						d.users.put(user, pass);
						System.out.println("Read in user: " + line);
						System.out.println("Added user: " + user);
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				isClosed = true;
				e.printStackTrace();
			}finally{
				if(!isClosed){
					if(br != null){
						br.close();
					}
				}
			}
			d.MultiSetup();


		}catch(Exception e){
			System.err.println("Server Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}