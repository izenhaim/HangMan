package qp_project2_HangMan.HangMan;

import java.util.ArrayList;

public class Game {
	Player p1;
	Player p2;
	String ID;
	private ArrayList<Integer> gussedChars = new ArrayList<>();
	private ArrayList<Character> chars = new ArrayList<>();
	
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.ID = p1.toString() + "," + p2.toString();
	}
	
	boolean hasPlayer(Player p){
		return p1.equals(p)||p2.equals(p);
	}
	
	void setword(String word) {
		if (this.chars.isEmpty()) {
			this.gussedChars.ensureCapacity(word.length());
			for (int i = 0; i < word.length(); i++)
				this.chars.add(word.charAt(i));
		}
	}

	Response[] handleGame(Request r) {
		if (r.type == ReqType.Write) {

			if (chars.contains(r.value)) {
				ArrayList<Integer> places = new ArrayList<>();
				for (int i = 0; i < chars.size(); i++) {
					if (chars.get(i).equals(r.value)) {
						gussedChars.add(i);
						places.add(i);
					}
				}

				GuesserResponse res = new GuesserResponse();
				res.isEmpty = false;
				res.resiver = r.sender.equals(p1) ? p2 : p1;
				res.GameID = this.ID;
				res.wright = true;
				res.places = places;
				GiverResponse gres = new GiverResponse();
				gres.GameID = this.ID;
				gres.isEmpty = false;
				gres.resiver = r.sender.equals(p1) ? p1 : p2;
				gres.gussedChars = this.gussedChars;
				gres.gussedNow = r.value;
				gres.wright = true;
				Response[] toReturn = { res, gres };
				return toReturn;
			} else {
				GuesserResponse res = new GuesserResponse();
				res.isEmpty = false;
				res.resiver = r.sender.equals(p1) ? p2 : p1;
				res.GameID = this.ID;
				res.wright = false;
				GiverResponse gres = new GiverResponse();
				gres.GameID = this.ID;
				gres.isEmpty = false;
				gres.resiver = r.sender.equals(p1) ? p1 : p2;
				gres.gussedNow = r.value;
				gres.wright = false;
				Response[] toReturn = { res, gres };
				return toReturn;
			}

		} else
			return new Response[0];
	}

}
