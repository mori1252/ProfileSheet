package model;

public class Staff {
	private int staffId;
	private String staffName;
	private String pass;
	
	public Staff() {}
	public Staff(int staffId, String staffName, String pass) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.pass = pass;
	}
	
	public int getStaffId() {return staffId;}
	public void setStaffId(int staffId) {this.staffId = staffId;}
	public String getStaffName() {return staffName;}
	public void setStaffName(String staffName) {this.staffName = staffName;}
	public String getPass() {return pass;}
	public void setPass(String pass) {this.pass = pass;}
}
