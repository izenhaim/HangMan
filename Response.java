package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Response implements Serializable {
	boolean startGame = false;
	boolean isEmpty;
	boolean wright;
	Player resiver;
	String GameID;
	String msg;
	String msg2;

	public Response() {
	}

	public Response(String msg) {
		this.msg = msg;
	}
}
