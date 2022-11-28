package controller;



import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Item.MutableItem;
import model.MembersItemList;


/**
 * The lending club handles creation of members, and interactions between them all, and when they're all involved.
 */
public class LendingClub {
  private view.View view;
  private view.MemberView memberView;
  private model.MemberList list;
  private model.Member.MutableMember chosenMember;
  private Member member;
  public ArrayList<model.Item.MutableItem> availableItems;
  private model.Time time;
  private ArrayList<model.ContractList> allContractLists = new ArrayList<model.ContractList>();

  LendingClub(view.View v, model.MemberList l, view.MemberView tml, Member tm, model.Time t) {
    view = v;
    list = l;
    memberView = tml;
    member = tm;
    time = t;
    availableItems = new ArrayList<model.Item.MutableItem>();
  }

  /**
   * Starts up the lending club.
   */
  public void start() {
    try {
      Boolean running = true;
      while (running) {
        if (chosenMember == null) {
          view.View.Start action = view.login();
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
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * Login with an existing member.
   */
  private void login() {
    try {
      if (list.getNumberOfMembers() == 0) {
        throw new Exception("No members.");
      } else {
        selectMember();
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
  * Selets member to act as. 
  */
  private void selectMember() {
    chosenMember = view.selectMember(list.getMembers());
    member.setCurrentMember(chosenMember);
  }

  /**
  * Creates a new member.
    */
  private void createMember() {
    try {
      model.Member newMember = memberView.createMember();
      if (!list.isEmailUnique(newMember.getEmail())) {
        throw new Exception("Email is not unique");
      } else if (!list.isPhoneNumberUnique(newMember.getPhoneNumber())) {
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


  /**
  * Main menu.
  */
  private void menu() {
    view.View.Menu action = view.menu(time.getCurrentDay());
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

  /**
  * Member profile menu.
  */
  private void memberProfile() {
    view.MemberView.Profile action = memberView.profile();
    switch (action) {
      case Details:
        memberView.viewMember(chosenMember);
        break;
      case Edit:
        editMember();
        break;
      case Item:
        itemMenu();
        break;  
      default:
        break;
    }
  }

  /**
  * Edit member menu.
  */
  private void editMember() {
    view.MemberView.Edit action = memberView.editMember();
    String newValue;
    switch (action) {
      case Name:
        newValue =  memberView.getNewStringValue();
        editName(newValue);
        break;
      case Email:
        newValue =  memberView.getNewStringValue();
        editEmail(newValue);
        break;
      case Phone:
        newValue =  memberView.getNewStringValue();
        editPhone(newValue);
        break;
      case Delete:
        deleteMember();
        break;  
      default:
        break;
    }
  }

  /**
  * Edit members phone number. 
  *
  * @param newValue New phone number.
  */
  private void editPhone(String newValue) {
    try {
      chosenMember.setPhoneNumber(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
  * Edit members email. 
  *
  * @param newValue New email.
  */
  private void editEmail(String newValue) {
    try {
      chosenMember.setEmail(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
  * Edit members name.
  *
  * @param newValue New name.
  */
  private void editName(String newValue) {
    try {
      chosenMember.setName(newValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
  * Deletes a member.
  */
  private void deleteMember() {
    view.View.Delete action = view.deleteMember();
    switch (action) {
      case Yes:
        list.deleteMember(chosenMember);
        view.displayMessage("Succesfully deleted member.");
        chosenMember = null;
        break;
      case No:
        return;
      default:
        break;
    }
  }

  /**
  * Item menu.
  */
  private void itemMenu() {
    view.MemberView.Item action = memberView.itemMenu();
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
      case Inspect:
        inspectItem();
        break;
      default:
        break;
    }
  }

  /**
  * Look at items info.
  */
  private void inspectItem() {
    member.inspectItem(time.getCurrentDay());
  }

  /**
  * Edit one item.
  */
  private void editItem() {
    member.editItem();
  }

  /**
  * Delete one item.
  */
  private void deleteItem() {
    member.deleteItem();
  }

  /**
  * Create an item.
  */
  private void createItem() {
    member.addItem(time.getCurrentDay());
  }

  /**
  * Handles days in system.
  */
  private void time() {
    int advancement = view.time(time.getCurrentDay());
    time.advanceTime(advancement);
    makeItemRentedCorrect();
  }

  /**
   * If day time counter increases, check that items rented status are true.
   */
  private void makeItemRentedCorrect() {
    for (model.ContractList cl : allContractLists) {
      for (model.Contract.MutableContract c : cl.getContracts()) {
        model.Item.MutableItem item = c.getItem();
        if (item.getRented() == true && c.getEndDay() < time.getCurrentDay()) {
          item.setAsNotRented();
        }
        if (item.getRented() == false) {
          if (c.getStartDay() <= time.getCurrentDay() && c.getEndDay() >= time.getCurrentDay()) {
            item.setAsRented();
          }
        }
      }
    }

  }

  /**
   * Establishes a contract if its possible to do so.
   */
  private void contract() {
    try {
      setAvailableItems();
      if (availableItems.size() == 0) {
        throw new Exception("No available items.");
      }
      model.Item.MutableItem chosenItem = (MutableItem) view.selectFromAllAvailableItems(getAvailableItems());
      int startDay = view.getContractStartDay();
      int contractPeriod = view.getContractPeriod();
      checkIfItemIsAvailble(startDay, (startDay + contractPeriod), chosenItem);
      checkIfMemberCanAffordContract(chosenMember.getCredit(), (chosenItem.getCostPerday() * contractPeriod));
      model.Contract contract = new Contract(startDay, (startDay + contractPeriod), chosenItem, chosenMember);
      model.Contract.MutableContract newContract = new model.Contract.MutableContract(contract);
      model.ContractList currentContractList = chosenItem.getContractList();
      if (!allContractLists.contains(currentContractList)) {
        allContractLists.add(currentContractList);
      }
      currentContractList.addContract(newContract);
      if (newContract.getStartDay() == time.getCurrentDay()) {
        chosenItem.setAsRented();
      }
      for (model.Item.MutableItem item : chosenMember.getItems()) {
        if (item != chosenItem) {
          chosenMember.withdrawCredit(chosenItem.getCostPerday() * contractPeriod);
        }
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    } finally {
      availableItems.clear();
    }
  }

  /**
   * Checks if the lender can afford to lend item.
   *
   * @param credits Lender crdits.
   * @param costOfItem Total cost of rental.
   * @throws Exception If lender cant afford.
   */
  private void checkIfMemberCanAffordContract(int credits, int costOfItem) throws Exception {
    if (credits < costOfItem) {
      throw new Exception("Can't afford item.");
    }
  }

  private void checkIfItemIsAvailble(int startDay, int endDay, model.Item.MutableItem item) throws Exception {
    for (model.Contract.MutableContract c : item.getContractList().getContracts()) {
      if (startDay >= c.getStartDay() && startDay <= c.getEndDay()) {
        throw new Exception("Item not available these days.");
      } else if (endDay >= c.getStartDay() && endDay <= c.getEndDay()) {
        throw new Exception("Item not available these days.");
      }
    }
  }

  /**
   * Sets the current available items to the availableItems list.
   */
  private void setAvailableItems() {
    for (model.Member.MutableMember m : list.getMembers()) {
      for (model.Item.MutableItem i : m.getItems()) {
        availableItems.add(i);
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

  /**
   * Menu for lists.
   */
  private void list() {
    view.View.ListChoice action = view.list();
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
