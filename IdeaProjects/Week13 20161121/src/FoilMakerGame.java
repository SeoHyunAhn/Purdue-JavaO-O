import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Vector;

public class FoilMakerGame extends Thread {
	private String result,equestion = "",eanswer = "";
	private static final int rightAns = 10;
	private static final int foolOther = 5;
	private FoilMakerServer fms,fms2;
	private Vector<String> name;
	private Vector<FoilMakerServer> fms1;

	private String[] message;
	private Vector<String> splitMsg;
	private Vector<String> question;
	private Vector<String> answer;
	private PrintWriter opw;
	private BufferedReader br;
	private boolean isEnded;


	public FoilMakerGame(FoilMakerServer fms){
		this.fms = fms;
		fms1 = new Vector();
		name = new Vector();
		answer = new Vector();
		isEnded = false;

		question = new Vector();
	}



	public void run(){
		Iterator<Map.Entry<String, String>> i = fms.listQA().entrySet().iterator();
		synchronized(this){
			while(i.hasNext()){
				Map.Entry<String, String> entry1 = i.next();
				question.add(entry1.getKey());
				System.out.println("Added Game Question: " + entry1.getKey());
				answer.add(entry1.getValue());
				System.out.println("Added Game Answer: " + entry1.getValue());
			}
		}
		//To Store the question in the actual game;

		Iterator<Map.Entry<String, FoilMakerServer>> i1 = fms.getOnlineUsers().entrySet().iterator();
		while(i1.hasNext()){
			HashMap.Entry<String, FoilMakerServer> entry = i1.next();
			fms2 = entry.getValue();
			fms1.addElement(fms2);
			name.addElement(entry.getKey());
			System.out.println(entry.getKey());
		}

		synchronized(this){
			while(i1.hasNext() && i1.next() != fms.getLeadername(fms.getUsername())){
				this.notify();
			}
		}
		//To Know the Thread of the Game
		while(!isEnded){
			newGameWord();
		}
	}


	public void newGameWord(){
		Iterator i2 = fms1.iterator();
		Iterator i3 = name.iterator();
		Random random = new Random();
		int length = 10;
		if(!question.isEmpty()){
			int result=random.nextInt(length);
			equestion = question.get(result);
			eanswer = answer.get(result);
			question.remove(result);
			answer.remove(result);
			length--;

			while(i2.hasNext()){
				System.out.println("NEWGAMEWORD" + fms.SEPARATOR + equestion + fms.SEPARATOR + eanswer);
				((FoilMakerServer) i2.next()).sendMsg("NEWGAMEWORD" + fms.SEPARATOR + equestion + fms.SEPARATOR + eanswer);

			}
			playerSuggestion();
		}else{
			while(i2.hasNext()){
				((FoilMakerServer) i2.next()).getOpw().println("GAMEOVER--");
				this.stop();
			}
			isEnded = true;//out of cards and game over
		}

		System.out.println("wait for suggestion: ");
	}


	public synchronized void playerSuggestion(){
		Iterator i1 = fms1.iterator();
		Iterator i2 = name.iterator();
		boolean choice = true;
		splitMsg = new Vector();
		for(int i = 0;i1.hasNext(); i++){
			System.out.println("hey");

			splitMsg.add(((FoilMakerServer) i1.next()).readMsg());
			if(splitMsg.get(i).toString().contains("PLAYERSUGGESTION")){
				message = splitMsg.get(i).toString().split(fms.SEPARATOR);
				fms.getSuggestion().put(i2.next(), message[3]);
			}else{
				choice = false;
				((FoilMakerServer) i1.next()).getOpw().println("RESPONSE" + fms.SEPARATOR + "PLAYERSUGGESTION" + fms.SEPARATOR + "UNEXPECTEDMESSAGETYPE" + fms.SEPARATOR);
				return;//UNEXPECTEDMESSAGETYPE
			}
		}

		if(choice){
			Iterator i3 = fms.getSuggestion().values().iterator();
			ArrayList ab = new ArrayList();
			ab.add(eanswer);
			String send = "ROUNDOPTIONS";
			while(i3.hasNext()){
				ab.add(i3.next());
			}
			int length = ab.size();
			Random random = new Random();
			while(!ab.isEmpty()){
				int a = random.nextInt(length);
				send += fms.SEPARATOR + ab.get(a) ;
				System.out.println(send);
				ab.remove(a);
				length--;
			}

			Iterator i4 = fms1.iterator();
			while(i4.hasNext()){
				((FoilMakerServer) i4.next()).sendMsg(send);
			}
			playerChoice();
		}
	}

	public synchronized void playerChoice(){
		Iterator i1 = fms1.iterator();
		Iterator i2 = name.iterator();
		splitMsg = new Vector();
		boolean Result = false;
		for(int i = 0;i1.hasNext(); i++){
			splitMsg.add(((FoilMakerServer) i1.next()).readMsg());
			if(splitMsg.get(i).toString().contains("PLAYERCHOICE")){
				message = splitMsg.get(i).toString().split(fms.SEPARATOR);
				fms.getChoice().put(i2.next(), message[3]);
				Result = true;
			}else{
				Result = false;
				((FoilMakerServer) i1.next()).sendMsg("RESPONSE" + fms.SEPARATOR + "PLAYERCHOICE" + fms.SEPARATOR + "UNEXPECTEDMESSAGETYPE" + fms.SEPARATOR);
				return;//UNEXPECTEDMESSAGETYPE
			}
		}
		if(Result)
			gameLogic();

	}

	public synchronized void gameLogic(){
		String gameResult = "ROUNDRESULT";
		Iterator i1 = fms1.iterator();
		Iterator i2 = name.iterator();
		for(int i = 0; i2.hasNext(); i++){
			int score = 0;
			int numFool = 0;
			int numFooled = 0;
			String fooled = "";
			String fooledby = "";
			System.out.println(fms.getChoice().get(i2.next()));

			if(((String)fms.getChoice().get(i2.next())).equals(eanswer)){
				score += 10;
				System.out.println(eanswer);
			}else{
				numFooled++;
				Iterator i3 = fms.getSuggestion().keySet().iterator();
				System.out.println("nicenice");
				while(i3.hasNext()){
					if(fms.getSuggestion().get(i3.next()).equals(fms.getChoice().get(i2.next()))){
						fooledby = "You are fooled by";
					}
				}
			}


			((FoilMakerServer) i1.next()).getScore(score);
		}

		while(i1.hasNext()){
			((FoilMakerServer) i1.next()).sendMsg(gameResult);
		}
	}

}