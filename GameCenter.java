package qp_project2_HangMan.HangMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
		System.out.println("new GC created!");
		this.client=client;
	}
	@Override
	public void run() {
		System.out.println("run begun");
		try {
			this.reqHandler();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean reqHandler() throws IOException{
		File f = new File("/Users/alireza_khojastehfar/Documents/workspace/AP/test.txt");
		FileWriter fout = new FileWriter(f);
		fout.write("5 \n");
		System.out.println("5");
		try {
			fout.write("hi " + players + " \n");
			System.out.println("hi " + players);
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			Request req = (Request) in.readObject();
			PrintWriter pw = new PrintWriter(client.getOutputStream());
			pw.println("resived");
			pw.flush();
			System.out.println("before Switch statement:");
			fout.write("before switch  \n");
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
				fout.write("in Connect \n");
				while(true){
					Player p = new Player(req.msg);
					if(!players.contains(p)){
						fout.write("valid user \n");
						System.out.println("was a vaild user");
						pw.print("valid");
						pw.flush();
						players.add(p);
						break;
					}else{
						fout.write("invalid user! \n");
						System.out.println("invalid user!");
						pw.print("inValid");
						pw.flush();
						req = (Request)in.readObject();
						fout.write("got new name  \n");
						System.out.println("got new req");
						pw.print("resived");
						pw.flush();
					}
				}
				break;
				
			default:
				fout.write("in Default statement!  \n");
				client.close();
				System.out.println(req);
				System.out.printf("\n\n");
				System.out.println(req.type);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		fout.write("after everything  " + players + "  \n");
		System.out.println("this is the player array in default of ..." + players);
		return true;
	}
}