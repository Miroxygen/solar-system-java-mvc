package model;


public class Member {
    protected String name;
    protected String email;
    protected String phoneNumber;
    private int id;
    private int dayOfCreation;
    public int credit;


    public Member(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setStaticData(int id, int dayOfCreation) {
        this.id = id;
        this.dayOfCreation = dayOfCreation;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
