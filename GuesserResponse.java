package qp_project2_HangMan.HangMan;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class GuesserResponse implements Response {
	boolean isEmpty;
	Player resiver;
	String GameID;
	boolean wright = false;
	ArrayList<Integer> places=  new ArrayList<>();
}