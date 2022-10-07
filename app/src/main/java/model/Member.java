package model;


public class Member {
    protected String name;
    protected String email;
    protected String phoneNumber;
    private String id;
    private int dayOfCreation;
    public int credit;
    public MembersItemList itemList;


    public Member(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Member(Member m) {
        this.name = m.name;
        this.phoneNumber = m.phoneNumber;
        this.email = m.email;
        this.id = m.id;
    }

    public void setItemList(MembersItemList itemList) {
        this.itemList = itemList;
    }

    public void setDayOfCreation(int dayOfCreation) {
        this.dayOfCreation = dayOfCreation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addCredit(int credit) {
        this.credit += credit;
    }

    public void withdrawCredit(int credit) {
        this.credit -= credit;
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

    public String getId() {
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

    public static class MutableMember extends Member {

        public MutableMember(String name, String phoneNumber, String email) {
            super(name, phoneNumber, email);
        }

        public MutableMember (Member m) {
            super(m);
        }
        
    }
}
