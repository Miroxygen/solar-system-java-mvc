package controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Item;
import model.Item.MutableItem;
import model.Member;
import model.MembersItemList;
import org.checkerframework.checker.nullness.qual.Nullable;
import view.ItemView;
import view.ItemView.ChangeItemChoices;
import view.ItemView.ItemMenuChoices;

/**
 * Represents a controller for item.
 */
public class ItemController {
  private view.ItemView itemUi = new ItemView();
  private ArrayList<MembersItemList> allMembersItemList = new ArrayList<MembersItemList>();
  private MembersItemList currentMembersItemlist = null;

  /**
  * Editing menu for items.
  *
  * @param selectedMember Object of member.
  * @param time Object time.
  * @throws Exception Errors.
  */
  public void itemMenu(Member.MutableMember selectedMember, model.Time time) throws Exception {
    try {
      ItemMenuChoices action = itemUi.showItemMenu(selectedMember.getName());
      switch (action) {
        case AddItem:
          addItem(selectedMember, time);
          break;
        case ViewOneItem:
          itemUi.showOneItem(itemUi.selectItemFromCurrentMember(currentMembersItemlist.getItems(),
              currentMembersItemlist.getNumberOfItems()));
          break;
        case ChangeItem:
          changeItemMenu();
          break;
        case DeleteItem:
          deleteItem();
          break;
        case Back:
          return;
        default:
          addItem(selectedMember, time);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * For editing an item.

   * @throws Exception Error.
  */
  public void changeItemMenu() throws Exception {
    try {
      Item itemToChange = itemUi.selectItemFromCurrentMember(currentMembersItemlist.getItems(),
          currentMembersItemlist.getNumberOfItems());
      ChangeItemChoices action = itemUi.changeItem();
      switch (action) {
        case Category:
          changeCategory(itemToChange);
          break;
        case Name:
          changeName(itemToChange);
          break;
        case Description:
          changeDescription(itemToChange);
          break;
        case Cost:
          changeCostPerDay(itemToChange);
          break;
        default:
          changeName(itemToChange);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * Parameter Category.
  *
  * @param itemToChange Object item.
  */
  public void changeCategory(Item itemToChange) {
    String category = itemUi.newStringValue();
    itemToChange.setCategory(category);
  }

  /**
  * Parameter name.
  *
  * @param itemToChange Object item.
  */
  public void changeName(Item itemToChange) {
    String name = itemUi.newStringValue();
    itemToChange.setName(name);
  }

  /**
  * Parameter description.
  *
  * @param itemToChange Object item.
  */
  public void changeDescription(Item itemToChange) {
    String description = itemUi.newStringValue();
    itemToChange.setDescription(description);
  }

  /**
  * Parameter costPerDay.
  *
  * @param itemToChange Object item.
  */
  public void changeCostPerDay(Item itemToChange) {
    int costPerDay = itemUi.newIntValue();
    itemToChange.setCostPerDay(costPerDay);
  }

  /**
  * Add object Item to object Members MembersItemList.
  *
  * @param selectedMember Object.
  * @param time Object. 
   * @throws Exception Error.
  */
  public void addItem(Member.MutableMember selectedMember, model.Time time) throws Exception {
    try {
      model.Item newItem = itemUi.createItem(time.getCurrentDay());
      handleCategoryErrors(newItem.getCategory());
      handleCostError(newItem.getCostPerday());
      selectedMember.addCredit(100);
      model.Item createdItem = currentMembersItemlist.addItem(newItem);
      createdItem.setOwner(selectedMember);
    } catch (Exception e) {
      throw e;
    }  
  }


  /**
  * Remove reference to chosen object.
  *
  * @throws Exception Error.
  */
  public void deleteItem() throws Exception {
    try {
      Item.MutableItem selectedItem;
      selectedItem = itemUi.selectItemFromCurrentMember(currentMembersItemlist.getItems(),
        currentMembersItemlist.getNumberOfItems());
      currentMembersItemlist.deleteItem(selectedItem);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * Reference MemberItemList instead of field of Member.
  *
  * @param itemList Object.
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "An abstraction is in parameter.")
  public void setCurrentItemList(MembersItemList itemList) {
    currentMembersItemlist = itemList;
    addCurrentListIfItHasntBeenAdded();
  }

  /**
  * Keep all MembersItemList here for referencing.
  */
  public void addCurrentListIfItHasntBeenAdded() {
    if (!allMembersItemList.contains(currentMembersItemlist)) {
      allMembersItemList.add(currentMembersItemlist);
    }
  }

  /**
  * For lending between members or to onself.
  *
  * @param selectedMember Object.
  * @return Object Item.
  * @throws Exception Error.
  * 
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Item selectLendableItem(Member selectedMember) throws Exception {
    try {
      Item.MutableItem selectedItem = (MutableItem) itemUi.getLendableItem(allMembersItemList, selectedMember);
      return selectedItem;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
  * For loggin out.
  */
  public void removeCurrentMembersItemlist() {
    this.currentMembersItemlist = null;
  }

  /**
  * To make sure the strings are correct.
  */
  public boolean stringMatcher(String input, List<String> stringsToCompareWith)  {
    boolean ifMatch = Iterables.any(stringsToCompareWith, new Predicate<String>() {
      @Override
      public boolean apply(@Nullable String categories) {
        return categories.equalsIgnoreCase(input);
      }
    });
    return ifMatch;
  }

  /**
  * So the categories are correct.
  *
  * @param category String
  * @throws Exception Wrong category.
  */
  public void handleCategoryErrors(String category) throws Exception {
    List<String> categories = Arrays.asList("tool", "vehicle", "game", "toy", "sport", "other");
    if (stringMatcher(category, categories) == false) {
      throw new Exception(" Wrong category.");
    }
  }

  /**
  * So the costs are correct.
  *
  * @param cost Int.
  * @throws Exception Wrong cost.
  */
  public void handleCostError(Integer cost) throws Exception {
    if (cost < 10 || cost > 100) {
      throw new Exception("Faulty cost per day.");
    }
  }
}
