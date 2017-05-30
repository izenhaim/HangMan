package qp_project2_HangMan.HangMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class SubGameClient {
	int movesMade=0;
	boolean guesser;
	String name;
	String opponent;
	String ID;
	int gameLenght;
	int wordLength;
	
	public void getRes(Response response) {
		// get response from parent ..
		// handle response
	}
	
	public SubGameClient(String gameID , String opponent , String name , int gameLenght , Boolean guesser) {
		this.ID=gameID;
		this.opponent=opponent;
		this.name = name;
		this.gameLenght = gameLenght;
		this.guesser = guesser;
		
		
		JFrame GameFrame = new JFrame("game with " + opponent);
		GameFrame.setLocation(550, 200);
		GameFrame.setVisible(true);
		GameFrame.getContentPane().setLayout(null);
		GameFrame.setResizable(false);
		GameFrame.setSize(500, 300);
		
		//TODO while for game length then listen for (word length) times then ask the word ... tell server ... switch sides ...
		
		
		//TODO get word .... tell server ... 
		GameFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	public SubGameClient(String gameID) {
		this.ID=gameID;
	}
	@Override
	public String toString() {
		return this.ID;
	}
	@Override
	public boolean equals(Object o) {
		if (((SubGameClient) o).ID.equals(this.ID))
			return true;
		else
			return false;
	}
	
}
