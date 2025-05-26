// model/Account.java

package model;

public class Account {
    private int id;
    private String name;
    private String pass;
    private String birth;
    private String address;
    private String contact;
    private String education;       // 学歴
    private String workHistory;     // 職歴
    private String targetJob;
    private String certifications;  // 資格・免許
    private String selfPR;          // 自己PR
    private String hobbies;         // 趣味・特技
    private String disability;
    private String medical;
    private String photoBase64;

    public Account() {}

    // フルコンストラクタ
    public Account(int id, String name, String pass, String birth,
                   String address, String contact,
                   String education, String workHistory,
                   String targetJob, String certifications,
                   String selfPR, String hobbies,
                   String disability, String medical,
                   String photoBase64) {
        this.id              = id;
        this.name            = name;
        this.pass            = pass;
        this.birth           = birth;
        this.address         = address;
        this.contact         = contact;
        this.education       = education;
        this.workHistory     = workHistory;
        this.targetJob       = targetJob;
        this.certifications  = certifications;
        this.selfPR          = selfPR;
        this.hobbies         = hobbies;
        this.disability      = disability;
        this.medical         = medical;
        this.photoBase64     = photoBase64;
    }

    // ──── ゲッター ────
    public int    getId()             { return id; }
    public String getName()           { return name; }
    public String getPass()           { return pass; }
    public String getBirth()          { return birth; }
    public String getAddress()        { return address; }
    public String getContact()        { return contact; }
    public String getEducation()      { return education; }
    public String getWorkHistory()    { return workHistory; }
    public String getTargetJob()      { return targetJob; }
    public String getCertifications() { return certifications; }
    public String getSelfPR()         { return selfPR; }
    public String getHobbies()        { return hobbies; }
    public String getDisability()     { return disability; }
    public String getMedical()        { return medical; }
    public String getPhotoBase64()    { return photoBase64; }

    // ──── セッター ────
    public void setId(int id)                         { this.id = id; }
    public void setName(String name)                  { this.name = name; }
    public void setPass(String pass)                  { this.pass = pass; }
    public void setBirth(String birth)                { this.birth = birth; }
    public void setAddress(String address)            { this.address = address; }
    public void setContact(String contact)            { this.contact = contact; }
    public void setEducation(String education)        { this.education = education; }
    public void setWorkHistory(String workHistory)    { this.workHistory = workHistory; }
    public void setTargetJob(String targetJob)        { this.targetJob = targetJob; }
    public void setCertifications(String certifications) { this.certifications = certifications; }
    public void setSelfPR(String selfPR)              { this.selfPR = selfPR; }
    public void setHobbies(String hobbies)            { this.hobbies = hobbies; }
    public void setDisability(String disability)      { this.disability = disability; }
    public void setMedical(String medical)            { this.medical = medical; }
    public void setPhotoBase64(String photoBase64)    { this.photoBase64 = photoBase64; }
}
