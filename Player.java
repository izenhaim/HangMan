package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Comparable , Serializable {
	boolean gusser;
	String opponentName;
	String name;
	int opponentIP;
	public Player() {}
	public Player(String name){
		this.name=name;
	}
	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public int compareTo(Object o) {
		if(((Player)o).name.equals(this.name))
			return 0;
		else return 1;
	}
	
}
