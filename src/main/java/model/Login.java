package model;

public class Login {
	
	private int id;
	private String pass;
	
	public Login (int id, String pass) {
		this.id = id;
		this.pass = pass;
	}
	
	public int getId() {return id ;}
	public String getPass() {return pass;}
}
