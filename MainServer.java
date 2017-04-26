package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class MainServer {
	
	
	public static void main(String[] args) {
		ServerSocket server;
		
		try {
			server = new ServerSocket(1111);
			while(true){
				Socket client = server.accept();
				System.out.println("accepted a new client!");
				GameCenter gc = new GameCenter(client);
				gc.run();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	













