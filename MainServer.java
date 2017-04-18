package qp_project2_HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MainServer {
	
	ArrayList<Player> players = new ArrayList<>();
	HashMap< Player , ArrayList<Response> > responses = new HashMap<>();
	public static void main(String[] args) {
		ServerSocket server;
		try {
			server = new ServerSocket(1111);
			while(true){
				Socket client = server.accept();
				reqHandler(client);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void reqHandler(Socket client){
		try {
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			Request req = (Request) in.readObject();
			switch (req.type) {
			case StartGame:
				
				break;
			case Read:
				
				break;
			case Write:
				
				break;
				
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}

class Request implements Serializable {
	Player sender;
	ReqType type;
	String GameID;
}

class Response implements Serializable{
	Player resiver;
	String GameID;
}

class GuesserResponse extends Response {
	
}

class GiverResponse extends Response {
	
}

class Player implements Serializable{
	boolean firstTimer = true;
	boolean gusser;
	String opponentName;
	String name;
	int opponentIP;
	@Override
	public String toString() {
		return this.name;
	}
	
	
}


class Game{
	Player p1;
	Player p2;
	String ID;
	String word;
	ArrayList<Character> gussedChars;
	
	public Game(Player p1 , Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.ID = p1.toString()+","+p2.toString();
	}
	
}