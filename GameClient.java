package qp_project2_HangMan.HangMan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class GameClient {
	static ArrayList<SubGameClient> games = new ArrayList<>();
	
	private void gameClosing(String gameID , Boolean Guesser){
		// TODO : close from server ...
		
	}

	public static void main(String[] args) {
		int score = 0;
		final String[] playerName = new String[1];

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
						r.sender = playerName[0];
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
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Socket s;
					try {
						s = new Socket("127.0.0.1", 1111);
						System.out.println("socket created");
						Scanner sc = new Scanner(s.getInputStream());
						ObjectOutputStream oot = new ObjectOutputStream(s.getOutputStream());
						System.out.println("oot created");
						Request r = new Request();
						r.type = ReqType.StartGame;
						r.sender = playerName[0];
						while (true) {
							oot.writeObject(r);
							oot.flush();
							if (sc.nextLine().equals("recived")) {
								break;
							} else {
								System.out.println("server didnot resive anything");
							}
						}
						ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
						ArrayList<Player> players = ((ArrayList<Player>)oin.readObject());
						String[] pls = new String[players.size()-1];
						for(int i=0 ; i<players.size() ; i++)
							if(!players.get(i).name.equals( playerName[0]))
								pls[i] = players.get(i).name;
						
						
							
						String chosenName = pls[JOptionPane.showOptionDialog(null, "select opponent", null, JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, null, pls, "5")];

						int gLength = JOptionPane.showOptionDialog(null, "select Game Lenght", null, JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE, null, new String[] {"3","5","7","9"}, "5");
						
						r = new Request();
						r.type = ReqType.StartGame;
						r.sender = playerName[0];
						r.msg = chosenName;
						r.msg2 = String.valueOf(gLength);
						while (true) {
							oot.writeObject(r);
							oot.flush();
							if (sc.nextLine().equals("recived")) {
								break;
							} else {
								System.out.println("server didnot resive anything");
							}
						}
						System.err.println("waiting");
						JOptionPane.showMessageDialog(null, "waiting for "+chosenName + " to confirm");
						if(((Boolean)oin.readObject())){
							
							new SubGameClient(playerName[0]+"-"+chosenName , chosenName , playerName[0] , gLength , true);
							
							
						}else{
							//TODO
						}
						
						
						//TODO ??
						sc.close();
						s.close();
						//
						
						
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					//new SubGameClient("" , "oppo" , playerName[0]);
					// TODO :
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


			try {
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
				r.sender = name;
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
					MainMenu.setTitle("Welcome " + name);
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
			
		try{
			while (true) {
				Thread.sleep(1000);
				System.out.println("updating from server ...");
				Socket s = new Socket("127.0.0.1", 1111);
				System.out.println("socket created");
				Scanner sc = new Scanner(s.getInputStream());
				ObjectOutputStream oot = new ObjectOutputStream(s.getOutputStream());
				System.out.println("oot created");
				Request r = new Request();
				r.type = ReqType.Read;
				r.sender = playerName[0];
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
				if(sc.nextLine().equals("True")){
					//TODO 
					//bug : 
					ObjectInputStream oin = new ObjectInputStream(s.getInputStream());
					@SuppressWarnings("unchecked")
					ArrayList<Response> incomingReses = ((ArrayList<Response>)(oin.readObject()));
					System.err.println("got response");
					HashMap<String, Boolean> confs = new HashMap<>();
					for(Response i : incomingReses){
						if(i.startGame == true){
							int answer = JOptionPane.showConfirmDialog(MainMenu, "Player " + i.msg +" wants to start game with you for " + i.msg2);
							
							confs.put(i.msg+"-"+playerName[0], answer==0?true:false);
							
							new SubGameClient(i.msg+"-"+playerName[0] , i.msg , playerName[0] , Integer.parseInt(i.msg2) , false);
							
						}else{
							int index = games.indexOf(new SubGameClient(i.GameID));
							games.get(index).getRes(i);
						}
					}	
					// TODO send the confirms to server and read them also from there!
					//TODO start confirmed games
					
					oot.writeObject(confs);
					oot.flush();
					
					
					
				}else{
					System.err.println("no response");
				}
				
				s.close();
				sc.close();
			}
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
			
			

	}
}


