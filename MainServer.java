package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

	public static void main(String[] args) {
		ServerSocket server;

		try {
			server = new ServerSocket(1111);
			while (true) {
				Socket client = server.accept();
				System.out.println("accepted a new client!");
				new GameCenter(client).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
