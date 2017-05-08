package qp_project2_HangMan.HangMan;

public class SubGameClient {
	String opponent;
	String ID;
	GameClient parent;
	
	public void getRes(Response response) {
		// get response from parent ..
		// handle response
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
