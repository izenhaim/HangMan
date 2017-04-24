package qp_project2_HangMan.HangMan;

import java.io.IOException;
import java.io.ObjectInputStream;
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
			MainMenu.setAlwaysOnTop(true);
			MainMenu.setVisible(true);
			MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MainMenu.setResizable(false);
			MainMenu.setSize(550, 350);
			MainMenu.getContentPane().setLayout(null);
			
			JLabel welcomeLable = new JLabel("Welcome To HangMan-CostomMade!");
			welcomeLable.setHorizontalAlignment(SwingConstants.CENTER);
			welcomeLable.setForeground(Color.BLUE);
			welcomeLable.setBounds(162, 18, 233, 16);
			MainMenu.getContentPane().add(welcomeLable);
			
			JButton StartGameButton = new JButton("Start New GAME");
			StartGameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("button clicked! ");
					// to do:
					// start new game ...
				}
			});
			StartGameButton.setBackground(new Color(0, 255, 0));
			StartGameButton.setForeground(Color.RED);
			StartGameButton.setBounds(198, 82, 150, 58);
			MainMenu.getContentPane().add(StartGameButton);
			
			JTextField ScoreField = new JTextField();
			ScoreField.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
			ScoreField.setEditable(false);
			ScoreField.setBounds(198, 216, 150, 26);
			ScoreField.setColumns(10);
			ScoreField.setText("Your Score is : " + String.valueOf(score));
			ScoreField.setVisible(true);
			MainMenu.getContentPane().add(ScoreField);
			
			//for test
//			Thread.sleep(5000);
			
			
			System.out.println("pls enter a name !");
			while(true){
				String name = JOptionPane.showInputDialog("please enter a naem!",null);
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
