package controller;
import org.checkerframework.checker.units.qual.m;

import model.Member;
import model.MemberList;
import view.View;
import view.View.memberMenuChoices;
import view.View.menuChoices;


public class Controller  {
    view.View view = new View();
    model.MemberList memberList = new MemberList();
    
    public void startMenu() {
        Boolean running = true;
        while(running) {
            menuChoices action = view.showMenu();
            switch (action) {
                case MemberMenu:
                    memberMenu();
                    break;
                case Quit:
                    running = false;
            }
        }
    }

    public void memberMenu() {
        memberMenuChoices action = view.showMemberMenu();
        switch (action) {
            case AddMember:
                createMember();
                break;
            case InspectMember:
                inspectMember();
                break;
            case ListMembers:
                view.showMemberList(memberList);
                break;
            case Back:
                return;
            default:
                return;
        }
    }

    public void inspectMember() {
        String memberName = view.inspectMember();
        if(findMember(memberName) == null) {
            view.resourceNotFound();
        } else {
            view.showMember(findMember(memberName));
        } 
    }

    public model.Member findMember(String memberName) {
        for(Member m : memberList.getMembers()) {
            if(m.getName().equals(memberName)) {
                return m;
            } 
        }
        return null;
    }

    public void createMember() {
        model.Member newMember = view.createMember();
        memberList.addMember(newMember);
        view.showMember(newMember);
    }
}