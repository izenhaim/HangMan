package qp_project2_HangMan.HangMan;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameClient {

	public static void main(String[] args) {
		int score = 0;
		final String[] playerName = new String[1];
		try {
			JFrame MainMenu = new JFrame("HangManMenu");
			MainMenu.setLocation(450, 150);
			MainMenu.setVisible(true);
			MainMenu.getContentPane().setLayout(null);
			MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MainMenu.setResizable(false);
			MainMenu.setSize(550, 350);
			MainMenu.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					Socket s;
					try {
						s = new Socket("127.0.0.1", 1111);
						System.out.println("socket created");
						Scanner sc = new Scanner(s.getInputStream());
						ObjectOutputStream oot = new ObjectOutputStream(s.getOutputStream());
						System.out.println("oot created");
						Request r = new Request();
						r.type = ReqType.DisConnect;
						r.msg = playerName[0];
						while (true) {
							System.out.println("in while true 2");
							oot.writeObject(r);
							oot.flush();
							if (sc.nextLine().equals("recived")) {
								System.out.println("serever recieved msg ... breaking whileTrue 2");
								s.close();
								sc.close();
								break;
							} else {
								System.out.println("server didnot resive anything");
							}
						}
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					super.windowClosing(e);
				}
			});

			JLabel welcomeLable = new JLabel("Welcome To HangMan-CostomMade!");
			welcomeLable.setBounds(161, 45, 233, 16);
			welcomeLable.setHorizontalAlignment(SwingConstants.CENTER);
			welcomeLable.setForeground(Color.BLUE);
			MainMenu.getContentPane().add(welcomeLable);

			JButton StartGameButton = new JButton("Start New GAME");
			StartGameButton.setBounds(210, 133, 144, 29);
			StartGameButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("button clicked! ");
					// to do:
					// start new game ...
				}
			});
			StartGameButton.setBackground(Color.WHITE);
			StartGameButton.setForeground(Color.RED);
			MainMenu.getContentPane().add(StartGameButton);

			JTextField ScoreField = new JTextField();
			MainMenu.getContentPane().add(ScoreField);
			ScoreField.setBounds(210, 218, 140, 26);
			ScoreField.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			ScoreField.setText("Your Score is : " + String.valueOf(score));
			ScoreField.setColumns(10);
			ScoreField.setEditable(false);
			ScoreField.setVisible(true);


			String name = JOptionPane.showInputDialog("please enter a name!", null);
			System.out.println("pls enter a name !");
			Socket s = new Socket("127.0.0.1", 1111);
			System.out.println("socket created");
			Scanner sc = new Scanner(s.getInputStream());
			ObjectOutputStream oot = new ObjectOutputStream(s.getOutputStream());
			System.out.println("oot created");
			
			while (true) {
				System.out.println("in while true 1");
				Request r = new Request();
				r.type = ReqType.Connect;
				r.msg = name;
				while (true) {
					System.out.println("in while true 2");
					oot.writeObject(r);
					oot.flush();
					if (sc.nextLine().equals("recived")) {
						System.out.println("serever recieved msg ... breaking whileTrue 2");
						break;
					} else {
						System.out.println("server didnot resive anything");
					}
				}
				System.out.println("checking name validity !");
				String res = sc.nextLine();
				if (res.equals("valid")) {
					System.out.println("was valid name!...breaking whileTrue 1");
					playerName[0] = name;
					s.close();
					sc.close();
					break;
				}else {
					System.out.println("invalid name ... getting another one!");
					name = JOptionPane.showInputDialog("sorry the name was taken pls enter a nother one!", null);
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
