package model;

public class Users {
	private int id;
	private String name;
	private String pass;
	private int birth;
	private String address;
	private String contact;
	private String medical;
	private String skill;
	private String targetJob;
	
	public Users() {}
	public Users(int id, String name, String pass, int birth, String address, String contact, String medical, String skill, String targetJob) {
		this.id = id;
		this.pass = pass;
		this.birth = birth;
		this.address = address;
		this.contact = contact;
		this.medical = medical;
		this.skill = skill;
		this.targetJob = targetJob;
	}
	
	public int getId() {return id ;}
	public void setId(int id) { this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getPass() {return pass;}
	public void setPass(String pass) {this.pass = pass;}
	public int getBirth() {return birth;}
	public void setBirth(int birth) {this.birth = birth;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getContact() {return contact;}
	public void setContact(String contact) {this.contact = contact;}
	public String getMedical() {return medical;}
	public void setMedical(String medical) {this.medical = medical;}
	public String getSkill() {return skill;}
	public void setSkill(String skill) {this.skill = skill;}
	public String getTargetJob() {return targetJob;}
	public void setTargetJob(String targetJob) {this.targetJob = targetJob;}
}
