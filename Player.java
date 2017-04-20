package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable {
	boolean firstTimer = true;
	boolean gusser;
	String opponentName;
	String name;
	int opponentIP;
	@Override
	public String toString() {
		return this.name;
	}
	
}
