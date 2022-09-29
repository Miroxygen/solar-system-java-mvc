package model;

import java.util.ArrayList;
import java.util.Random;

public class MemberList {
    private ArrayList<Member> members = new ArrayList<Member>();

    public Iterable<Member> getMembers() {
        return members;
    }

    public Member addMember(Member member) {
        member.setStaticData(generateRandomNumber(), 0);
        members.add(member);
        return members.get(members.size() - 1);
    }

    private int generateRandomNumber() {
        Random radomnumber = new Random();
        return radomnumber.nextInt();
    }
}
