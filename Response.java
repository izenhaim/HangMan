package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Response implements Serializable {
	boolean isEmpty;
	boolean wright;
	Player resiver;
	String GameID;
	String msg;
	public Response() {}
	public Response(String msg) {
		this.msg = msg;
	}
}
