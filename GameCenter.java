package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class GameCenter extends Thread {
	Socket client;
	static ArrayList<Player> players = new ArrayList<>();
	static HashMap<String, ArrayList<Response>> responses = new HashMap<>();
	static HashMap<String , Game> games = new HashMap<>();
	static HashMap<String, Boolean> confirms = new HashMap<>();

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
				// give the available players :DONE
				// get the opponent name : DONE
				// request(send response over the read request to the player) : DONE
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
				pw.flush();
				Response res = new Response();
				res.startGame = true;
				res.msg = req.sender;
				res.msg2 = req.msg2;
				res.resiver = new Player(req.msg);
				if(responses.containsKey((req.msg))){
					responses.get((req.msg)).add(res);//TODO
				}else{
					ArrayList<Response> a = new ArrayList<>();
					a.add(res);
					responses.put(req.msg, a);
				}
				System.err.println("put in response");
				while(true){
					Thread.sleep(500);
					if(confirms.containsKey(req.sender+"-"+req.msg)){
						oout.writeObject(confirms.get(req.sender+"-"+req.msg));
						oout.flush();
						System.err.println("confirmed");
						break;
					}
				}
				if(confirms.remove(req.sender+"-"+req.msg)){
					//TODO
					Player p1 = new Player(req.sender);
					Player p2 = new Player(req.msg);
					p1.opponentName = p2.name;
					p2.opponentName = p1.name;
					p1.gusser = true;
					games.put(req.sender+"-"+req.msg, new Game(p1,p2,Integer.parseInt(req.msg2)));
					
					System.err.println("removed");
				}else{
					//TODO
				}
				
				
				break;
				
			case DisConnect:
				//TODO delete games with this player!
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
				client.setSoTimeout(0);
				oout = new ObjectOutputStream(client.getOutputStream());
				
				while(true){
					System.out.println("in Read Case for "+ req.sender +": ...");
					System.out.println(responses);
					if(responses.containsKey((req.sender))){
						oout.writeObject(responses.remove((req.sender)));
						oout.flush();
						System.gc();
						System.err.println("gave responses to player");
						@SuppressWarnings("unchecked")
						HashMap<String, Boolean>confs = ((HashMap<String, Boolean>)(in.readObject()));
						confirms.putAll(confs);
						//TODO check if done right
					}else{
						oout.writeObject(new ArrayList<Response>());
						oout.flush();
						@SuppressWarnings("unchecked")
						HashMap<String, Boolean>confs = ((HashMap<String, Boolean>)(in.readObject()));
						confirms.putAll(confs);
					}
					Thread.sleep(1000);
					req = (Request) in.readObject();
					System.out.println("got new req");
					pw.println("recived");
					pw.flush();
				}
			
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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