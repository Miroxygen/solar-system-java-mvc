package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Item;
import view.ItemView.Edit;

/**
 * Member controls their information and items.
 */
public class Member {
  private view.ItemView view;
  private model.Member.MutableMember chosenMember;
  private model.MembersItemList itemList;
  private model.Item.MutableItem chosenItem;

  Member(view.ItemView v) {
    view = v;
  }

  /**
   * Sets currently logged in member. 
   *
   * @param m Member.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Member is a mutable.")
  public void setCurrentMember(model.Member.MutableMember m) {
    chosenMember = m;
    itemList = chosenMember.getItemList();
  }

  /**
   * Adds item to member. 
   *
   * @param currentDay Current day in system.
   */
  public void addItem(int currentDay) {
    try {
      Item newItem = view.createItem(currentDay);
      itemList.addItem(newItem);
      chosenMember.addCredit(100);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * Menu for editing item.
   */
  public void editItem() {
    try {
      selectItem();
      Edit action = view.editItem();
      String value = view.getNewValue();
      switch (action) {
        case Category:
          editCategory(value);
          break;
        case Name:
          editName(value);
          break;
        case Description:
          editDescription(value);
          break;
        case Cost:
          editCost(value);
          break;
        default:
          break;
      }
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * Edits items cost. 
   *
   * @param value New cost.
   */
  private void editCost(String value) {
    try {
      int intValue = Integer.parseInt(value);
      view.wrongCost(intValue);
      chosenItem.setCostPerDay(intValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * Edits edits description. 
   *
   * @param value New description.
   */
  private void editDescription(String value) {
    chosenItem.setDescription(value);
  }

  /**
   * Edits items name. 
   *
   * @param value New name.
   */
  private void editName(String value) {
    chosenItem.setName(value);
  }

  /**
   * Sets items category.
   *
   * @param value New category.
   */
  private void editCategory(String value) {
    try {
      view.wrongCategory(value);
      chosenItem.setCategory(value);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
  * Select item to edit. 
  *
  * @throws Exception If there are no items.
  */
  private void selectItem() throws Exception {
    if (itemList.getNumberOfItems() == 0) {
      throw new Exception("No items.");
    }
    chosenItem = view.selectItem(itemList.getItems());
  }

  /**
   * Deletes an item.
   */
  public void deleteItem() {
    try {
      selectItem();
      itemList.deleteItem(chosenItem);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  /**
   * Look at all info of an item. 
   *
   * @param currentDay Current day of system.
   */
  public void inspectItem(int currentDay) {
    try {
      selectItem();
      view.showOneItem(chosenItem, currentDay);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }
}
