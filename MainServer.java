package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainServer {
	
	
	public static void main(String[] args) {
		ServerSocket server;
		
		try {
			server = new ServerSocket(1111);
			while(true){
//				System.out.println(server.getLocalSocketAddress());
				Socket client = server.accept();
				System.out.println("accepted a new client!1");
				GameCenter GC = new GameCenter(client);
				GC.start();
				//new GameCenter(client).start();
//				while(true){
//					System.out.println(new Scanner(client.getInputStream()).nextLine());
//					PrintWriter pw = new PrintWriter(client.getOutputStream());
//					pw.println(new Scanner(System.in).nextLine());
//					pw.flush();
//				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

	













