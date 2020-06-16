package db.uz.telegramwebusers.Model;

public class ModelAdministrators {

    private String u_id;
    private String phonenumber;
    private String firstname;
    private String lastname;
    private String branch;
    private String departmen;
    private String joindate;

    public ModelAdministrators(int i, String kkk, String s, String s1, String s2, java.util.Date date){
        super();
    }

    public ModelAdministrators(String u_id, String phonenumber, String firstname, String lastname, String branch, String departmen, String joindate) {
        this.u_id = u_id;
        this.phonenumber = phonenumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.branch = branch;
        this.departmen = departmen;
        this.joindate = joindate;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDepartmen() {
        return departmen;
    }

    public void setDepartmen(String departmen) {
        this.departmen = departmen;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }



}
