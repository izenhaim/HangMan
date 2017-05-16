package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
	String sender;
	ReqType type;
	String GameID;
	String msg;
	String msg2;
	Character value;
}