package qp_project2_HangMan.HangMan;

import javax.swing.JFrame;

public class SubGameClient {
	boolean gusser;
	String name;
	String opponent;
	String ID;
	
	public void getRes(Response response) {
		// get response from parent ..
		// handle response
	}
	
	public SubGameClient(String gameID , String opponent , String name) {
		this.ID=gameID;
		this.opponent=opponent;
		this.name = name;
		JFrame GameFrame = new JFrame("game with " + opponent);
		GameFrame.setLocation(550, 200);
		GameFrame.setVisible(true);
		GameFrame.getContentPane().setLayout(null);
		GameFrame.setResizable(false);
		GameFrame.setSize(500, 300);
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
