package controller;

public class TestMember {
  private view.TestItemView view;
  private model.Member.MutableMember chosenMember;
  private model.MembersItemList itemList;
  private model.Item.MutableItem chosenItem;

  TestMember(view.TestItemView v) {
    view = v;
  }

  public void setCurrentMember(model.Member.MutableMember m) {
    chosenMember = m;
    itemList = chosenMember.getItemList();
  }

  public void addItem(int currentDay) {
    try {
      itemList.addItem(view.createItem(currentDay));
      chosenMember.addCredit(100);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  public void editItem() {
    try {
      selectItem();
      view.TestItemView.Edit action = view.editItem();
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

  private void editCost(String value) {
    try {
      int intValue = Integer.parseInt(value);
      view.wrongCost(intValue);
      chosenItem.setCostPerDay(intValue);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }

  private void editDescription(String value) {
    chosenItem.setDescription(value);
  }

  private void editName(String value) {
     chosenItem.setName(value);
  }

  private void editCategory(String value) {
    try {
      view.wrongCategory(value);
      chosenItem.setCategory(value);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
  }
 }

  private void selectItem() throws Exception {
    if(itemList.getNumberOfItems() == 0) {
      throw new Exception("No items.");
    }
    chosenItem = view.selectItem(itemList.getItems());
  }

  public void deleteItem() {
    try {
      selectItem();
      itemList.deleteItem(chosenItem);
    } catch (Exception e) {
      view.displayMessage(e.getMessage());
    }
  }
}
