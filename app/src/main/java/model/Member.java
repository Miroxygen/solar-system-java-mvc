package model;


public class Member {
    
    protected String name;
    protected String email;
    protected int phoneNumber;
    private int id;
    private int dayOfCreation;
    public int credit;

    public Member(String name, int phoneNumber, String email, int id, int dayOfCreation, int credit) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
        this.dayOfCreation = dayOfCreation;
        this.credit = credit;
    }
}
