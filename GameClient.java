package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameClient {
	
	public static void main(String[] args) {
		int score =0;
		try {
			JFrame MainMenu = new JFrame("HangManMenu");
			MainMenu.setLocation(450, 150);
			MainMenu.setVisible(true);
			MainMenu.getContentPane().setLayout(null);
			MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MainMenu.setResizable(false);
			MainMenu.setSize(550, 350);
			
			JLabel welcomeLable = new JLabel("Welcome To HangMan-CostomMade!");
			welcomeLable.setBounds(161, 45, 233, 16);
			welcomeLable.setHorizontalAlignment(SwingConstants.CENTER);
			welcomeLable.setForeground(Color.BLUE);
			MainMenu.getContentPane().add(welcomeLable);
			
			JButton StartGameButton = new JButton("Start New GAME");
			StartGameButton.setBounds(210, 133, 144, 29);
			StartGameButton.addActionListener(new ActionListener() {
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
			
			//for test
//			Thread.sleep(5000);
			
			
			System.out.println("pls enter a name !");
			String name = JOptionPane.showInputDialog("please enter a naem!",null);
			while(true){
				Request r = new Request();
				r.type=ReqType.Connect;
				r.msg=name;
				Scanner sc;
				Socket s = new Socket("MainServer", 1111);
				ObjectOutputStream oot = new ObjectOutputStream(s.getOutputStream());
				while(true){
					oot.writeObject(r);
					oot.flush();
					sc = new Scanner(s.getInputStream());
					if(sc.nextLine()=="resived"){
						break;
					}
				}
				if(sc.nextLine()=="valid"){
					break;
				}else{
					System.out.println("sorry this name was taken! pls enter a nother one!");
				}
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
