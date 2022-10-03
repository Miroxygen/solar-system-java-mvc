package controller;
import view.MainUI;
import view.MainUI.loginChoices;
import view.MainUI.mainMenuChoices;



public class MainController  {
    view.MainUI view = new MainUI();
    MemberController memberController = new MemberController();
    
    public void startMenu() {
        Boolean running = true;
        while(running) {
            if(memberController.getSelectedMember() == null) {
                loginChoices action = view.loginMenu();
                switch (action) {
                    case Login:
                       memberController.selectMemberToActAs();
                        break;
                    case CreateMember:
                        memberController.createMember();
                        break;
                    case Quit:
                        running = false;
                }
            } else {
                mainMenuChoices action = view.mainMenu(memberController.getSelectedMember());
                switch (action) {
                    case MemberMenu:
                        MemberMenu();
                        break;
                    case Logout:
                        memberController.removeSelectedMember();
                        break;
                }
            }    

        }
    }

    public void MemberMenu() {
        memberController.MemberMenu();
    }

    public void editMemberMenu() {
        memberController.editMemberMenu();
    }
}