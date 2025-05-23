package model;

public class Account {
    private int id;
    private String name;
    private String pass;
    private String birth;
    private String address;
    private String contact;
    private String disability;
    private String medical;
    private String skill;
    private String targetJob;

    // 引数なしコンストラクタを追加
    public Account() {
    }

    // フルコンストラクタ
    public Account(int id, String name, String pass, String birth,
                   String address, String contact, String disability,
                   String medical, String skill, String targetJob) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.birth = birth;
        this.address = address;
        this.contact = contact;
        this.disability = disability;
        this.medical = medical;
        this.skill = skill;
        this.targetJob = targetJob;
    }

    // ──────────── ゲッター ────────────
    public int getId()             { return id; }
    public String getName()        { return name; }
    public String getPass()        { return pass; }
    public String getBirth()       { return birth; }
    public String getAddress()     { return address; }
    public String getContact()     { return contact; }
    public String getDisability()  { return disability; }
    public String getMedical()     { return medical; }
    public String getSkill()       { return skill; }
    public String getTargetJob()   { return targetJob; }

    // ──────────── セッター ────────────
    public void setId(int id)                  { this.id = id; }
    public void setName(String name)           { this.name = name; }
    public void setPass(String pass)           { this.pass = pass; }
    public void setBirth(String birth)         { this.birth = birth; }
    public void setAddress(String address)     { this.address = address; }
    public void setContact(String contact)     { this.contact = contact; }
    public void setDisability(String disability) { this.disability = disability; }
    public void setMedical(String medical)     { this.medical = medical; }
    public void setSkill(String skill)         { this.skill = skill; }
    public void setTargetJob(String targetJob) { this.targetJob = targetJob; }
}
