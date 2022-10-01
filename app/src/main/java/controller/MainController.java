package controller;
import model.Member;
import model.MemberList;
import view.View;
import view.View.memberMenuChoices;
import view.View.editMemberChoices;
import view.View.loginChoices;
import view.View.mainMenuChoices;



public class MainController  {
    view.View view = new View();
    MemberController memberController = new MemberController();
    model.MemberList memberList = new MemberList();
    model.Member selectedMember = null;
    
    public void startMenu() {
        Boolean running = true;
        while(running) {
            if(selectedMember == null) {
                loginChoices action = view.loginMenu();
                switch (action) {
                    case Login:
                       selectedMember = login();
                        break;
                    case CreateMember:
                        memberController.createMember();
                        break;
                    case Quit:
                        running = false;
                }
            } else {
                mainMenuChoices action = view.mainMenu(selectedMember);
                switch (action) {
                    case MemberMenu:
                        MemberMenu();
                        break;
                    case Logout:
                        selectedMember = null;
                        break;
                }
            }    

        }
    }

    public void MemberMenu() {
        memberMenuChoices action = view.showMemberMenu(selectedMember);
        switch (action) {
            case InspectMember:
                view.showMember(selectedMember);
                break;
            case ListMembers:
                memberController.showMemberList();
                break;
            case EditMember:
                editMemberMenu();
                break;
            case DeleteMember:
                memberController.deleteMember(selectedMember);
                selectedMember = null;
                break;
            case Return:
                return;
            default:
                return;
            
        }
    }

    public void editMemberMenu() {
        editMemberChoices action = view.editMember();
        switch (action) {
            case Name:
                memberController.editName(selectedMember);
                break;
            case PhoneNumber:
                memberController.editPhoneNumber(selectedMember);
                break;
            case Email:
                memberController.editEmail(selectedMember);
                break;
        }
    }

    public Member login() {
        String name = view.login(memberList);
        selectedMember = memberController.findMemberByname(name);
        return selectedMember;
    }
}