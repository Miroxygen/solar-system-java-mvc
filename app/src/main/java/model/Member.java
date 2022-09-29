package model;


public class Member {
    
    protected String name;
    protected String email;
    protected String phoneNumber;
    private int id;
    private int dayOfCreation;
    public int credit;

    public Member(String name, String phoneNumber, String email, int id, int dayOfCreation, int credit) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
        this.dayOfCreation = dayOfCreation;
        this.credit = credit;
    }
}
