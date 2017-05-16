package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GameCenter extends Thread {
	Socket client;
	static ArrayList<Player> players = new ArrayList<>();
	static HashMap<Player, ArrayList<Response>> responses = new HashMap<>();
	static HashMap<String , Game> games = new HashMap<>();

	public GameCenter(Socket client) {
		System.out.println("new GC created!");
		this.client = client;
	}

	@Override
	public void run() {
		System.out.println("run begun");
			this.reqHandler();
	}

	private void reqHandler() {
		System.out.println("reqHandler Begun working ...");
		try {
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			Request req = (Request) in.readObject();
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.println("recived");
			pw.flush();
			System.out.println("told client RECIVED msg!");
			System.out.println("before Switch statement:");
			switch (req.type) {
			case StartGame:
				// give the available players
				// get the opponent name
				// request(send response over the read request to the player)
				// opponent
				// if accepted :
				// create game
				// get the word
				// give the opponent response about the word
				// end
				
				ObjectOutputStream oout = new ObjectOutputStream(client.getOutputStream());
				oout.writeObject(players);
				oout.flush();
				req = (Request) in.readObject();
				pw.println("recived");
				Response res = new Response();
				res.startGame = true;
				res.msg = req.sender;
				res.msg2 = req.msg2;
				res.resiver = new Player(req.msg);
				responses.get(new Player(req.msg)).add(res);//TODO
				
				break;
				
			case DisConnect:
				//delete games with this player!
				// ... 
				System.out.println("in DisConnect Case: ...");
				Player p = new Player(req.sender);
				int toRemoveIndex = players.lastIndexOf(p);
				players.remove(toRemoveIndex);
				System.out.println(players);
				break;
				
			case Read:
				// check the to do hashMap
				// if anything there , give the response to the player
				//		
				System.out.println("in Read Case: ...");
				if(responses.containsKey(new Player(req.sender))){
					pw.println("True");
					pw.flush();
					oout = new ObjectOutputStream(client.getOutputStream());
					oout.writeObject(responses.get(new Player(req.sender)));
					responses.remove(new Player(req.sender));
					System.gc();
				}else{
					pw.println("Flase");
					pw.flush();
				}
				
				break;
			case Write:
				// get the letter / word ...
				// hand it to the related game
				// get the response from the game
				// put the response in the to do hash map
				break;
			case Connect:
				System.out.println("in connect Case: ...");
				while (true) {
					p = new Player(req.sender);
					if (!players.contains(p)) {
						System.out.println("was a vaild user");
						pw.println("valid");
						pw.flush();
						players.add(p);
						break;
					} else {
						System.out.println("invalid user!");
						pw.println("notValid");
						pw.flush();
						req = (Request) in.readObject();
						System.out.println("got new req");
						pw.println("recived");
						pw.flush();
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
		try {
			System.out.println(players);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}