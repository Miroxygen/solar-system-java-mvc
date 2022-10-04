package controller;
import view.MainUI;
import view.MainUI.listChoices;
import view.MainUI.loginChoices;
import view.MainUI.mainMenuChoices;



public class MainController  {
    view.MainUI view = new MainUI();
    MemberController memberController = new MemberController();
    ItemController  itemController = new ItemController();
    
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
                        memberMenu();
                        break;
                    case ItemMenu:
                        itemController.ItemMenu(memberController.getSelectedMember());
                        break;
                    case ListEverything:
                        listMenu();
                        break;
                    case Logout:
                        memberController.removeSelectedMember();
                        itemController.removeCurrentMembersItemlist();
                        break;
                }
            }    

        }
    }

    public void listMenu() {
        listChoices action = view.listChoices(memberController.getSelectedMember());
        switch (action) {
            case Simple:
                view.ListAllSimpleWay(memberController.getMemberList());
                break;
            case Verbose:
                view.ListAllVerboseWay(memberController.getMemberList());
                break;
        }
    }

    public void memberMenu() {
        memberController.MemberMenu();
    }
}