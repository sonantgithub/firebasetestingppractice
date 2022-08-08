package com.example.firebasetesting;

public class person
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String aid;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String bname;

    // Variable to store data corresponding
    // to age keyword in database
    private String cemail;

    private String dactive;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    public person(String aid, String bname, String cemail, String dactive) {
        this.aid = aid;
        this.bname = bname;
        this.cemail = cemail;
        this.dactive = dactive;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getDactive() {
        return dactive;
    }

    public void setDactive(String dactive) {
        this.dactive = dactive;
    }
// Getter and setter method

}