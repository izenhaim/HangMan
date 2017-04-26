package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GameCenter extends Thread {
	Socket client;
	static ArrayList<Player> players = new ArrayList<>();
	static HashMap< Player , ArrayList<Response> > responses = new HashMap<>();
	static ArrayList<Game> games = new ArrayList<>();
	
	public GameCenter(Socket client) {
		this.client=client;
	}
	@Override
	public void run() {
		System.out.println("run begun");
		this.reqHandler();
	}
	private void reqHandler(){
		try {
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			Request req = (Request) in.readObject();
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.println("resived");
			pw.flush();
			switch (req.type) {
			case StartGame:
				//give the available players
				//get the opponent name
				//request(send response over the read request to the player) opponent
				// if accepted :
				//	create game 
				//	get the word
				//	give the opponent response about the word 
				//end
				break;
			case DisConnect:
				//remove from the available list
				break;
			case Read:
				//check the to do hashMap
				//if anything there , give the response to the player 
				//
				break;
			case Write:
				//get the letter / word ... 
				//hand it to the related game
				//get the response from the game
				//put the response in the to do hash map
				break;
			case Connect:
				//put the player in the available list
				while(true){
					Player p = new Player(req.msg);
					if(!players.contains(p)){
						pw.print("valid");
						pw.flush();
						players.add(p);
						break;
					}else{
						pw.print("inValid");
						pw.flush();
						req = (Request)in.readObject();
					}
				}
				break;
				
			default:
				client.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}