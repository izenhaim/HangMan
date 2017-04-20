package qp_project2_HangMan.HangMan;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable {
	Player sender;
	ReqType type;
	String GameID;
	String msg;
	Character value;
}