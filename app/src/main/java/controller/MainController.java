package controller;
import org.checkerframework.checker.units.qual.C;

import model.Contract;
import model.Item;
import model.Member;
import model.Item.MutableItem;
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
                memberController.showMembersSimple();
                break;
            case Verbose:
                memberController.displayMembersVerbose();
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
                itemController.setCurrentItemList(memberController.getSelectedMember().getItemList());
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
            Item.MutableItem lendItem = (MutableItem) itemController.selectLendableItem(memberController.getSelectedMember());
            int startDate = contractController.getStartDate();
            int endDate = startDate + contractController.getContractLength();
            checkIfItemIsAvailable(lendItem,startDate, endDate);
            Contract newContract = contractController.getNewContract(memberController.getSelectedMember(), lendItem, startDate, endDate);
            if(lendItem.getOwner() != memberController.getSelectedMember()) {
                if((lendItem.getCostPerday() * newContract.getLength()) > memberController.getSelectedMember().getCredit()) {
                    throw new Exception("Not enough credits.");
                }
                memberController.getSelectedMember().withdrawCredit(lendItem.getCostPerday() * newContract.getLength());
                lendItem.getOwner().addCredit(lendItem.getCostPerday() * newContract.getLength());
            }
            if(newContract.getStartDay() == time.getCurrentDay()) {
                lendItem.setCurrentContract(newContract);
                lendItem.setAsRented();
            } else {
                lendItem.addToFutureContracts(newContract);
            }
            lendItem = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  
    }

    public void checkIfItemIsAvailable(Item lendItem, int startDate , int endDate) throws Exception {
        if(startDate < 0 || startDate + time.getCurrentDay() < time.getCurrentDay()) {
            throw new Exception("Can't lend in the past.");
        }
        if(lendItem.getCurrentContract() != null) {
            if(startDate <= lendItem.getCurrentContract().getEndDay()) {
                throw new Exception("Not available");
            }
        }
        for(Contract fC : lendItem.getFutureContracts()) {
            if(startDate > fC.getStartDay() && startDate < fC.getEndDay() || (startDate == fC.getStartDay() || startDate == fC.getEndDay())) {
                throw new Exception("Not available");
            } else if(endDate > fC.getStartDay() && endDate < fC.getEndDay() || (endDate == fC.getStartDay() || endDate == fC.getEndDay())) {
                throw new Exception("Not available");
            }
        }
    }

    public void advanceTime() {
        try {
            int daysToAdvance = mainUI.advanceTime(time);
            if(daysToAdvance < 0) {
                throw new Exception("Can't go back in time.");
            }
            time.advanceTime(daysToAdvance);
            dailyContractCheck();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void dailyContractCheck() {
        for(Contract c : contractController.getRunningContracts()) {
            setCurrentContract(c);
            Contract oldContract = removeOldContracts(c);
            if(oldContract != null) {
                for(Contract fC : oldContract.getItem().getFutureContracts()) {
                    setCurrentContract(fC);
                }
            }  
        }
    }

    public void setCurrentContract(Contract contract) {
        if(contract.getStartDay() <= time.getCurrentDay() && contract.getEndDay() >= time.getCurrentDay()) {
            contract.getItem().setCurrentContract(contract);
            contract.getItem().removeFromFutureContracts(contract);
            contract.getItem().setAsRented();
        }
    }

    public Contract removeOldContracts(Contract contract) {
        if(contract.getEndDay() < time.getCurrentDay()) {
            contract.getItem().moveExpiredContract(contract);
            contractController.removeExpiredContract(contract);
            return contract;
        }
        else return null;
    }
}
