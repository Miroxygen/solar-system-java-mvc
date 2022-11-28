package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * View for items.
 */
public class ItemView extends View {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * Menu for editing.
   */
  public static enum Edit {
    Category,
    Name,
    Description,
    Cost
  }

  /**
   * Creates an item.
   *
   * @param currentDay Current day in system.
   * @return Item,
   * @throws Exception If input is false.
   */
  @SuppressFBWarnings(value = "VARIABLE_DECLARATION_DISTANCE", justification = "Value wont be altered.")
  public model.Item createItem(int currentDay) throws Exception {
    try {
      System.out.println("=== Please enter a category for your item : (Tool, Vehicle, Game, Toy, Sport, or Other)");
      String category = input.nextLine();
      wrongCategory(category);
      System.out.println("=== Please enter a name for your item :");
      String name = input.nextLine();
      System.out.println("=== Please enter a short descrition for you item : ");
      String description = input.nextLine();
      System.out.println("=== Please enter a cost per day for your item : (10-100 credits)");
      int costPerDay = input.nextInt();
      wrongCost(costPerDay);
      input.nextLine();
      return new model.Item(category, name, description, currentDay, costPerDay);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Selects an item from list.
   *
   * @param <T> Item.
   * @param list List of items.
   * @return Item.
   */
  public <T extends model.Item> T selectItem(Iterable<T> list) {
    int index = 0;
    for (model.Item i : list) {
      System.out.println(index + " | Name : " + i.getName());
      index++;
    }
    System.out.println("Please enter the index of the item");
    String key = input.nextLine();
    int intKey = Integer.parseInt(key);
    index = 0;
    for (T i : list) {
      if (index == intKey) {
        return i;
      }
      index++;
    }
    return null;
  }

  /**
   * For editing an item. 
   *
   * @return Enum.
   */
  public Edit editItem() {
    try {
      final String category = "cg";
      final String name = "n";
      final String description = "d";
      final String cost = "c";
      System.out.println(" Enter what you want to change :");
      System.out.println(category + " Category | " + name + " Name | " 
          + description + " Description | " + cost + " Cost |");
      String key = input.nextLine();
      if (key.equals(category)) {
        return Edit.Category;
      } else if (key.equals(name)) {
        return Edit.Name;
      } else if (key.equals(description)) {
        return Edit.Description;
      } else if (key.equals(cost)) {
        return Edit.Cost;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Gets a new value for an item. 
   *
   * @return New value string.
   */
  public String getNewValue() {
    System.out.println(" Enter the new value : ");
    String value = input.nextLine();
    return value;
  }

  /**
   * If the item has wrong category. 
   *
   * @param category Item category.
   * @throws Exception If wrong category.
   */
  public void wrongCategory(String category) throws Exception {
    String[] categories = {"Tool", "tool", "Vehicle", "vehicle",
      "Game", "game", "Toy", "toy", "Sport", "sport", "Other", "other"};
    List<String> categoryList = Arrays.asList(categories);
    if (!categoryList.contains(category)) {
      throw new Exception("Wrong category");
    }
  }

  /**
   * Checks if item has wrong cost. 
   *
   * @param cost Item cost.
   * @throws Exception If wrong cost.
   */
  public void wrongCost(int cost) throws Exception {
    if (cost < 10 || cost > 100) {
      throw new Exception("Wrong cost.");
    }
  }

  /**
   * Displays one item with its contracts.
   *
   * @param item Item to show.
   * @param currentDay Current day in system.
   */
  public void showOneItem(model.Item item, int currentDay) {
    System.out.println("Category : " + item.getCategory() + " Name : " + item.getName() 
        + " Description : " + item.getDescription()
        + " Day of Creation : " + item.getDayOfCreation() + " Cost per day : " + item.getCostPerday());
    for (model.Contract c : item.getContractList().getContracts()) {
      if (c.getStartDay() > currentDay) {
        System.out.println(" Future contracts :  Start Day : " + c.getStartDay() + " End day : " 
            + c.getEndDay() + " Lender : " + c.getLender().getName());
      } else if (c.getEndDay() < currentDay) {
        System.out.println(" Old contracts : Start day : " + c.getStartDay() + " End day : " 
            + c.getEndDay() + " Lender : " + c.getLender().getName());
      } else {
        System.out.println(" Current contract : Start day : " + c.getStartDay() + " End day : "
            + c.getEndDay() + " Lender : " + c.getLender().getName());
      }
    }
  }
}
