package controller;
import model.Member;
import model.MemberList;
import view.View;
import view.View.memberMenuChoices;
import view.View.menuChoices;



public class Controller  {
    view.View view = new View();
    model.MemberList memberList = new MemberList();
    model.Member selectedMember = null;
    
    public void startMenu() {
        Boolean running = true;
        while(running) {
            if(selectedMember == null) {
                menuChoices action = view.loginMenu();
                switch (action) {
                    case Login:
                       selectedMember = login();
                        break;
                    case CreateMember:
                        createMember();
                        break;
                    case Quit:
                        running = false;
                }
            } else {
                memberMenu();
            }    
        }
    }

    public Member login() {
        String name = view.login(memberList);
        return findMember(name);
    }

    public void memberMenu() {
        memberMenuChoices action = view.showMemberMenu(selectedMember);
        switch (action) {
            case InspectMember:
                view.showMember(selectedMember);
                break;
            case ListMembers:
                view.showMemberList(memberList);
                break;
            case DeleteMember:
                deleteMember();
                break;
            case Logout:
                selectedMember = null;
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
        try {
            model.Member newMember = view.createMember();
            checkForDuplicateMemberData(newMember.getPhoneNumber(), newMember.getEmail());
            memberList.addMember(newMember);
            view.showMember(newMember);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            createMember();
        }
    }

    public void checkForDuplicateMemberData(String phoneNumber, String email) throws Exception {
        for(Member m : memberList.getMembers()) {
            if(m.getPhoneNumber().equals(phoneNumber)) {
                throw new Exception("Invalid phonenumber");
            } else if(m.getEmail().equals(email)) {
                throw new Exception("Invalid email");
            } 
        }
    }

    public void deleteMember() {
        String deleteAnswer = view.deleteMember(selectedMember);
        if(deleteAnswer.equals("Y") || deleteAnswer.equals("y")) {
            memberList.deleteMember(selectedMember);
            selectedMember = null;
            view.succesfulAction();
        } else {
            return;
        }
    }
}