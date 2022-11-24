package controller;

import java.util.ArrayList;

import model.Contract;
import model.Item;
import model.MembersItemList;
import model.Item.MutableItem;

public class TestLc {
  private view.TestView view;
  private view.TestMemberView memberView;
  private model.MemberList list;
  private model.Member.MutableMember chosenMember;
  private TestMember member;
  public ArrayList<model.Item.MutableItem> availableItems;
  private ArrayList<model.MembersContractList> membersContractLists;

  TestLc(view.TestView v, model.MemberList l, view.TestMemberView tml, TestMember tm) {
    view = v;
    list = l;
    memberView = tml;
    member = tm;
    availableItems = new ArrayList<model.Item.MutableItem>();
  }

  public void start() {
    try {
      Boolean running = true;
      while(running) {
        if(chosenMember == null) {
          view.TestView.Start action = view.login();
          switch (action) {
            case Login:
              login();
              break;
            case Create:
              createMember();
              break;
            case Quit:
              running = false;
              break;
            default:
              view.login();
              break;
          }
      } else {
        menu();
      }
    }
    } catch (Exception e) {
      
    }
  }

  private void login() {
    try {
      if(list.getNumberOfMembers() == 0) {
        throw new Exception("No members.");
      } else {
        selectMember();
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
    }

  private void selectMember() {
    chosenMember = view.selectMember(list.getMembers());
    member.setCurrentMember(chosenMember);
  }

  private void createMember() {
    try {
      model.Member newMember = memberView.createMember();
      if(!list.isEmailUnique(newMember.getEmail())) {
        throw new Exception("Email is not unique");
      } else if(!list.isPhoneNumberUnique(newMember.getPhoneNumber())) {
        throw new Exception("Phone-number is not unique");
      }
      model.Member.MutableMember newMutable = list.addMember(newMember);
      model.MembersItemList itemList = new MembersItemList();
      newMutable.setItemList(itemList);
      newMutable.setDayOfCreation(0);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }


 private void menu() {
  view.TestView.Menu action = view.menu();
  switch (action) {
    case Profile:
      memberProfile();
      break;
    case List:
      list();
      break;
    case Contract:
      contract();
      break;
    case Time:
      time();
      break;
    case Logout:
    chosenMember = null;
      break;  
    default:
      start();
      break;
  }
  }

  private void memberProfile() {
    view.TestMemberView.Profile action = memberView.profile();
    switch (action) {
      case Details:
        memberView.viewMember(chosenMember);
        break;
      case Edit:
        editMember();
        break;
      case Item:
        itemMenu();  
      default:
        break;
    }
  }

  private void editMember() {
    view.TestMemberView.Edit action = memberView.editMember();
    String newValue = memberView.getNewStringValue();
    switch (action) {
      case Name:
        editName(newValue);
        break;
      case Email:
        editEmail(newValue);
        break;
      case Phone:
        editPhone(newValue);
        break;
      case Delete:
        list.deleteMember(chosenMember);  
      default:
        break;
    }
	}

	private void editPhone(String newValue) {
    try {
      chosenMember.setPhoneNumber(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void editEmail(String newValue) {
    try {
      chosenMember.setEmail(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void editName(String newValue) {
    try {
      chosenMember.setName(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void itemMenu() {
    view.TestMemberView.Item action = memberView.itemMenu();
    switch (action) {
      case Add:
        createItem();
        break;
      case Delete:
        deleteItem();
        break;
      case Edit:
        editItem();
        break;
      default:
        break;
    }
  }

  private void editItem() {
    member.editItem();
  }

  private void deleteItem() {
    member.deleteItem();
  }

  private void createItem() {
    member.addItem(0);
  }

  private void time() {
  }

  private void contract() {
    try {
      setAvailableItems();
      if(availableItems.size() == 0) {
        throw new Exception("No available items.");
      }
      model.Item.MutableItem chosenItem = (MutableItem) view.selectFromAllAvailableItems(getAvailableItems());
      int contractPeriod = view.getContractPeriod();
      checkIfMemberCanAffordContract(chosenMember.getCredit(), (chosenItem.getCostPerday() * contractPeriod));
      model.Contract contract = new Contract(contractPeriod, contractPeriod, chosenItem, chosenMember);
      model.Contract.MutableContract newContract = new model.Contract.MutableContract(contract);
      model.MembersContractList currentContractList = chosenItem.getContractList();
      currentContractList.addContract(newContract);
      availableItems.clear();
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
      e.getStackTrace();
    }
  }

  private void checkIfMemberCanAffordContract(int credits, int costOfItem) throws Exception {
    if(credits < costOfItem) {
      throw new Exception("Can't afford item.");
    }
  }

  /**
   * Sets the current available items to the availableItems list.
   */
  private void setAvailableItems() {
    for(model.Member.MutableMember m : list.getMembers()) {
      for(model.Item.MutableItem i : m.getItemList().getItems()) {
        if(!i.getRented()) {
          availableItems.add(i);
        }
      }
    }
  }

  /**
   * Gets the list of avaialble items.
   *
   * @return Iterable of mutable items.
   */
  private Iterable<Item.MutableItem> getAvailableItems() {
    return availableItems;
  }

  private void list() {
    view.TestView.List action = view.list();
    switch (action) {
      case Simple:
        view.listMembersSimple(list.getMembers());
        break;
      case Verbose:
        view.listMembersVerbose(list.getMembers(), 0);
        break;
      default:
        break;
    }
  }
}
