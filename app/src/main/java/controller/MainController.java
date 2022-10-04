package controller;
import model.Contract;
import model.Item;
import model.Member;
import view.MainUI;
import view.MainUI.listChoices;
import view.MainUI.loginChoices;
import view.MainUI.mainMenuChoices;



public class MainController  {
    view.MainUI mainUI = new MainUI();
    MemberController memberController = new MemberController();
    ItemController  itemController = new ItemController();
    ContractController contractController = new ContractController();
    model.Time time = new model.Time();
    
    public void startMenu() {
        try {
            time.advanceTime(0);
            Boolean running = true;
        while(running) {
            if(memberController.getSelectedMember() == null) {
                loginChoices action = mainUI.loginMenu(time);
                switch (action) {
                    case Login:
                        login();
                        break;
                    case CreateMember:
                        memberController.createMember(time);
                        break;
                    case Quit:
                        running = false;
                        break;
                    case Time:
                        advanceTime();
                        break;
                }
            } else {
                mainMenuChoices action = mainUI.mainMenu(memberController.getSelectedMember(), time);
                switch (action) {
                    case MemberMenu:
                        memberMenu();
                        break;
                    case ItemMenu:
                        itemController.ItemMenu(memberController.getSelectedMember(), time);
                        break;
                    case ListEverything:
                        listMenu();
                        break;
                    case LendItem:
                        lendItem();
                        break;
                    case Logout:
                        memberController.removeSelectedMember();
                        itemController.removeCurrentMembersItemlist();
                        break;
                    case Time:
                        advanceTime();
                        break;
                    }
                }    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listMenu() {
        listChoices action = mainUI.listChoices(memberController.getSelectedMember(), time);
        switch (action) {
            case Simple:
                mainUI.ListAllSimpleWay(memberController.getMemberList());
                break;
            case Verbose:
                mainUI.ListAllVerboseWay(memberController.getMemberList());
                break;
        }
    }

    public void login() {
        try {
            int counter = 0;
            for(Member m : memberController.getMemberList().getMembers()) {
                counter++;
            }
            if(counter == 0) {
                throw new Exception("No members.");
            } else {
                memberController.selectMemberToActAs();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
    }

    public void memberMenu() {
        memberController.MemberMenu();
    }

    public void lendItem() {
        try {
            Item lendItem = itemController.selectItemFromOtherMembersAvailableItems(memberController.getSelectedMember());
            Contract newContract = contractController.establishNewContract(memberController.getSelectedMember(), lendItem);
            lendItem.addContract(newContract);
            lendItem.setCurrentContract(newContract);
            lendItem.setRented(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    }

    public void advanceTime() {
        int daysToAdvance = mainUI.advanceTime(time);
        time.advanceTime(daysToAdvance);
    }
}