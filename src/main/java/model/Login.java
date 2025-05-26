package model;

public class Login {
	
	private int id;
	private String pass;
	
	public Login() {}
	public Login (int id, String pass) {
		this.id = id;
		this.pass = pass;
	}
	
	public int getId() {return id ;}
	public String getPass() {return pass;}
	
	public void setId(int id) {this.id = id;}
	public void setPass(String pass) {this.pass = pass;}
}
