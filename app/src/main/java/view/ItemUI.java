package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Item;
import model.MembersItemList;

/**
 * Represents the item view.
 */
public class ItemUi {
  private Scanner userInput = new Scanner(System.in, "utf-8");

  /**
   * Represents menu choices.
   */
  public static enum ItemMenuChoices {
    AddItem,
    ViewOneItem,
    ChangeItem,
    DeleteItem,
    Back
  }

  /**
   * Represents menu choices.
   */
  public static enum ChangeItemChoices {
    Category,
    Name,
    Description,
    Cost,
  }

  /**
   * Shows item menu.
   *
   * @param name Membername.
   * @return Static enum.
   */
  public ItemMenuChoices showItemMenu(String name) {
    System.out.println("  ~ Welcome! You are logged in as : " + name);
    System.out.println("=== 1. Add item to this member.");
    System.out.println("=== 2. Look at detailed information about one item.");
    System.out.println("=== 3. Change one item.");
    System.out.println("=== 4. Delete one item.");
    System.out.println("=== 5. Back to main menu.");
    String inputKey = userInput.nextLine();
    switch (inputKey) {
      case "1":
        return ItemMenuChoices.AddItem;
      case "2":
        return ItemMenuChoices.ViewOneItem;
      case "3":
        return ItemMenuChoices.ChangeItem;
      case "4":
        return ItemMenuChoices.DeleteItem;
      case "5":
        return ItemMenuChoices.Back;
      default:
        return ItemMenuChoices.Back;
    }
  }

  /**
   * View for creating item.
   *
   * @param currentDay Int.
   * @return Object Item.
   */
  public Item createItem(int currentDay) {
    System.out.println("=== Please enter a category for your item : (Tool, Vehicle, Game, Toy, Sport, or Other)");
    String category = userInput.nextLine();
    System.out.println("=== Please enter a name for your item :");
    String name = userInput.nextLine();
    System.out.println("=== Please enter a short descrition for you item : ");
    String description = userInput.nextLine();
    System.out.println("=== Please enter a cost per day for your item : (10-100 credits)");
    int costPerDay = userInput.nextInt();
    userInput.nextLine();
    return new Item(category, name, description, currentDay, costPerDay);
  }

  /**
   * List of available items.
   *
   * @param arrayList List of items.
   * @param selectedMember Object.
   * @return Object item.
   * @throws Exception No items to show.
   */
  public Item getLendableItem(ArrayList<MembersItemList> arrayList, model.Member selectedMember) throws Exception {
    int index = 0;
    for (MembersItemList mil : arrayList) {
      if (mil.getOwner() == selectedMember) {
        System.out.println(" These are your own item/s. You can reserve them (for free).");
      } else {
        System.out.println(" Other members item/s.");
      }
      for (Item i : mil.getItems()) {
        System.out.println(index + " | Category : " + i.getCategory() + " Name : " + i.getName() 
            + " Description : " + i.getDescription() + " Cost per day : " +  i.getCostPerday());
        index++;
      }
    }
    if (index == 0) {
      throw new Exception("No items.");
    }
    System.out.println("=== Please enter the index of item you want to select :");
    int selectedIndex = userInput.nextInt();
    userInput.nextLine();
    index = 0;
    for (MembersItemList mil : arrayList) {
      for (Item i : mil.getItems()) {
        if (index == selectedIndex) {
          return i;
        }
        index++;
      }
    }
    return null;
  }

  /**
   * For seeing all info.
   *
   * @param <T> Child of Item.
   * @param item Object.
   */
  public <T extends Item> void showOneItem(T item) {
    System.out.println("Category : " + item.getCategory() + " Name : " + item.getName() 
        + " Description : " + item.getDescription() + " Cost per day : " +  item.getCostPerday() 
        + " Day of ceation : " + item.getDayOfCreation() + " Rented? " + item.getRented());
    if (item.getRented()) {
      System.out.println(" Currently rented to " + item.getCurrentContract().getLender().getName() 
          + " from day " + item.getCurrentContract().getStartDay() 
          + " to " + item.getCurrentContract().getEndDay());
    }
    int index = 0;
    System.out.println("Old contracts : ");
    for (model.Contract c : item.getOldContracts()) {
      if (c == null) {
        System.out.println(" Null ");
      } else {
        System.out.println(" Lender : " + c.getLender().getName() + " Startday : " + c.getStartDay() 
            + " Endday :" + c.getEndDay());
        index++;
      }    
    }
    if (index == 0) {
      System.out.println("No old contracts.");
    }
    index = 0;
    System.out.println("Future contracts : ");
    for (model.Contract c : item.getFutureContracts()) {
      System.out.println(" Lender : " + c.getLender().getName() + " Startday : " 
          + c.getStartDay() + " Endday :" + c.getEndDay());
      index++;
    }
    if (index == 0) {
      System.out.println("No future contracts.");
    }
  }

  /**
   * For interacting with item.
   *
   * @param <T> Child of item.
   * @param itemList Iterates T.
   * @param listLength Int.
   * @return Object Item.
   * @throws Exception No items to select from.
   */
  public <T extends Item> T selectItemFromCurrentMember(Iterable<T> itemList, int listLength) throws Exception {
    if (listLength == 0) {
      throw new Exception("No items.");
    } else {
      int index = 0;
      for (Item i : itemList) {
        System.out.println("Index : " + index + " Item : " + i.getName());
        index++;
      }
      System.out.println("=== Please enter the index of item you want to select :");
      int selectedIndex = userInput.nextInt();
      userInput.nextLine();
      index = 0;
      for (T i : itemList) {
        if (index == selectedIndex) {
          return i;
        }
        index++;
      }
    }
    throw new Exception("Invalid index.");
  }   

  /**
   * Logged in members items.
   *
   * @param itemList Iterable.
   */
  public void showMembersItems(MembersItemList itemList) {
    for (Item i : itemList.getItems()) {
      System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() 
          + " Description : " + i.getDescription()
          + " Cost per day : " +  i.getCostPerday() + " Day of ceation : " + i.getDayOfCreation());
    }
  }

  /**
   * Changes fields.
   *
   * @return static enum.
   */
  public ChangeItemChoices changeItem() {
    System.out.println("=== What trait do you wish to change?");
    System.out.println(" 1. Category.");
    System.out.println(" 2. Name.");
    System.out.println(" 3. Description.");
    System.out.println(" 4. Price per day");
    String inputKey = userInput.nextLine();
    if (inputKey.equals("1")) {
      return ChangeItemChoices.Category;
    } else if (inputKey.equals("2")) {
      return ChangeItemChoices.Name;
    } else if (inputKey.equals("3")) {
      return ChangeItemChoices.Description;
    } else if (inputKey.equals("4")) {
      return ChangeItemChoices.Cost;
    } else {
      return ChangeItemChoices.Name;
    }
  }

  /**
   * A new value for changeItem().
   *
   * @return String.
   */
  public String newStringValue() {
    System.out.println(" Please enter the new value you want :");
    String inputKey = userInput.nextLine();
    return inputKey;
  }

  /**
   * A new value for changeItem().
   *
   * @return Int.
   */
  public int newIntValue() {
    System.out.println(" Please enter the new value you want :");
    int inputKey = userInput.nextInt();
    userInput.nextLine();
    return inputKey;
  }
}
