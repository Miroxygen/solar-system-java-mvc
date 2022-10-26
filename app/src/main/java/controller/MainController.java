package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Contract;
import model.Item;
import model.Item.MutableItem;
import model.Member;
import model.Member.MutableMember;
import view.MainView;
import view.MainView.ListChoices;
import view.MainView.LoginChoices;
import view.MainView.MainMenuChoices;


/**
 * Represents the head controller.
 */
public class MainController  {
  MainView mainUi = new MainView();
  MemberController memberController = new MemberController();
  ItemController  itemController = new ItemController();
  ContractController contractController = new ContractController();
  model.Time time = new model.Time();

  /**
  * Starting point of application.
  */
  public void startMenu() {
    try {
      time.advanceTime(0);
      Boolean running = true;
      while (running) {
        if (memberController.getSelectedMember() == null) {
          LoginChoices action = mainUi.loginMenu(time);
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
            default:
              login();
          }
        } else {
          MainMenuChoices action = mainUi.mainMenu(memberController.getSelectedMember(), time);
          switch (action) {
            case MemberMenu:
              memberMenu();
              break;
            case ItemMenu:
              itemController.itemMenu(memberController.getSelectedMember(), time);
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
            default:
              memberMenu();
              break;
          }
        }    
      }
    } catch (Exception e) {
      mainUi.displayText(e.getMessage());
      startMenu();
    }
  }

  /**
  * For listing members and items.
  */
  public void listMenu() {
    ListChoices action = mainUi.listChoices(memberController.getSelectedMember(), time);
    switch (action) {
      case Simple:
        memberController.showMembersSimple();
        break;
      case Verbose:
        memberController.displayMembersVerbose();
        break;
      default:
        memberController.showMembersSimple();
    }
  }

  /**
  * For future implementations mostly.
  * Right now no password is added.
  */
  public void login() throws Exception {
    try {
      int counter = 0;
      for (Member m : memberController.getMemberList().getMembers()) {
        counter++;
      }
      if (counter == 0) {
        throw new Exception("No members.");
      } else {
        memberController.selectMemberToActAs();
        itemController.setCurrentItemList(memberController.getSelectedMember().getItemList());
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * For editing member.
  */
  public void memberMenu() {
    memberController.memberMenu();
  }

  /**
  * Added to main because its a "system" function.
  */
  public void lendItem() throws Exception {
    try {
      Item.MutableItem lendItem = (MutableItem) itemController.selectLendableItem(memberController.getSelectedMember());
      int startDate = contractController.getStartDate();
      int endDate = startDate + contractController.getContractLength();
      checkIfItemIsAvailable(lendItem, startDate, endDate);
      Contract newContract = contractController.getNewContract((MutableMember) 
          memberController.getSelectedMember(), lendItem, startDate, endDate);
      if (lendItem.getOwner() != memberController.getSelectedMember()) {
        if ((lendItem.getCostPerday() * newContract.getLength()) > memberController.getSelectedMember().getCredit()) {
          throw new Exception("Not enough credits.");
        }
        memberController.getSelectedMember().withdrawCredit(lendItem.getCostPerday() * newContract.getLength());
        lendItem.getOwner().addCredit(lendItem.getCostPerday() * newContract.getLength());
      }
      if (newContract.getStartDay() == time.getCurrentDay()) {
        lendItem.setCurrentContract(newContract);
        lendItem.setAsRented();
      } else {
        lendItem.addToFutureContracts(newContract);
      }
      lendItem = null;
    } catch (Exception e) {
      throw e;
    }  
  }

  /**
  * So no unavailable item can be lended.
  *
  * @param lendItem Object item.
  * @param startDate Int.
  * @param endDate Int.
  * @throws Exception If no availability.
  */
  public void checkIfItemIsAvailable(Item lendItem, int startDate, int endDate) throws Exception {
    if (startDate < 0 || startDate + time.getCurrentDay() < time.getCurrentDay()) {
      throw new Exception("Can't lend in the past.");
    }
    if (lendItem.getCurrentContract() != null) {
      if (startDate <= lendItem.getCurrentContract().getEndDay()) {
        throw new Exception("Not available");
      }
    }
    for (Contract futureContract : lendItem.getFutureContracts()) {
      if (startDate > futureContract.getStartDay() && startDate < futureContract.getEndDay() 
          || (startDate == futureContract.getStartDay() || startDate == futureContract.getEndDay())) {
        throw new Exception("Not available");
      } else if (endDate > futureContract.getStartDay() && endDate < futureContract.getEndDay() 
          || (endDate == futureContract.getStartDay() || endDate == futureContract.getEndDay())) {
        throw new Exception("Not available");
      }
    }
  }

  /**
  * For testing out contractperiods and so on.
  */
  public void advanceTime() throws Exception {
    try {
      int daysToAdvance = mainUi.advanceTime(time);
      if (daysToAdvance < 0) {
        throw new Exception("Can't go back in time.");
      }
      time.advanceTime(daysToAdvance);
      dailyContractCheck();
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * Cleans up void contracts and adds future ones that are eligable.
  */
  public void dailyContractCheck() {
    for (Contract c : contractController.getRunningContracts()) {
      setCurrentContract(c);
      Contract oldContract = removeOldContracts(c);
      if (oldContract != null) {
        for (Contract futureContract : oldContract.getItem().getFutureContracts()) {
          setCurrentContract(futureContract);
        }
      }  
    }
  }

  /**
  * If contract is eligable to be current, make it so.
  *
  * @param contract Object.
  */
  public void setCurrentContract(Contract contract) {
    if (contract.getStartDay() <= time.getCurrentDay() && contract.getEndDay() >= time.getCurrentDay()) {
      contract.getItem().setCurrentContract(contract);
      contract.getItem().removeFromFutureContracts(contract);
      contract.getItem().setAsRented();
    }
  }

  /**
  * Cleans up. Returns oldContract for checking conflicts with future contracts.
  *
  * @param contract Object.
  * @return Object contract.
  * 
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Contract removeOldContracts(Contract contract) {
    if (contract.getEndDay() < time.getCurrentDay()) {
      contract.getItem().moveExpiredContract(contract);
      contractController.removeExpiredContract(contract);
      return contract;
    }
    return null;
  }
}
