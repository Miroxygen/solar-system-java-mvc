package model;


public class Member {
    protected String name;
    protected String email;
    protected String phoneNumber;
    private int id;
    private int dayOfCreation;
    public int credit;
    public MembersItemList itemList;


    public Member(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setItemList(MembersItemList itemList) {
        this.itemList = itemList;
    }

    public void setDayOfCreation(int dayOfCreation) {
        this.dayOfCreation = dayOfCreation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addCredit(int credit) {
        this.credit += credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getDayOfCreation() {
        return dayOfCreation;
    }

    public int getCredit() {
        return credit;
    }

    public MembersItemList getItemList() {
        return itemList;
    }
}
