package view;

import java.util.Scanner;

/**
 * The view for the controller.
 */
public class View {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * Choices for start.
   */
  public static enum Start {
    Login,
    Create,
    Quit
  }

  /**
   * Choices for menu.
   */
  public static enum Menu {
    Profile,
    List,
    Contract,
    Time,
    Logout
  }

  /**
   * Choices for list.
   */
  public static enum ListChoice {
    Simple,
    Verbose
  }
  
  /**
   * Choices for delete.
   */
  public static enum Delete {
    Yes,
    No
  }
  /**
   * Login menu.
   *
   * @return An enum.
   */
  public Start login() {
    final String login = "L";
    final String create = "C";
    final String quit = "Q";
    System.out.println("=== Welcome to the Stuff Lending Club! ===");
    System.out.println("=== " + login + ". Login with existing member");
    System.out.println("=== " + create + ". Create a new member");
    System.out.println("=== " + quit + ". Quit application.");
    String key = input.nextLine();
    if (key.equals(login)) {
      return Start.Login;
    } else if (key.equals(create)) {
      return Start.Create;
    } else if (key.equals(quit)) {
      return Start.Quit;
    }
    return null;
  }

  /**
   * The manin menu.
   *
   * @param currentDay Systems current day.
   * @return An enum.
   */
  public Menu menu(int currentDay) {
    displayCurrentDay(currentDay);
    final String edit = "e";
    final String list = "l";
    final String contract = "c";
    final String time = "t";
    final String logout = "out";
    System.out.println("=== " + edit + "| Memberprofile.");
    System.out.println("=== " + list + "| List members.");
    System.out.println("=== " + contract + "| Establish a contract.");
    System.out.println("=== " + time + "| Increase time.");
    System.out.println("=== " + logout + "| Logout.");
    String key = input.nextLine();
    if (key.equals(edit)) {
      return Menu.Profile;
    } else if (key.equals(list)) {
      return Menu.List;
    } else if (key.equals(contract)) {
      return Menu.Contract;
    } else if (key.equals(time)) {
      return Menu.Time;
    } else if (key.equals(logout)) {
      return Menu.Logout;
    }
    return null;
  }

  /**
   * Menu to display lists. 
   *
   * @return An enum.
   */
  public ListChoice list() {
    final String simple = "s";
    final String verbose = "v";
    System.out.println(simple + " - List simple | " + verbose + " - List verbose |");
    String key = input.nextLine();
    if (key.equals(simple)) {
      return ListChoice.Simple;
    } else if (key.equals(verbose)) {
      return ListChoice.Verbose;
    }
    return null;
  }

  /**
   * Select a member from a list. 
   *
   * @param <T> Member.
   * @param list List of member.
   * @return Member.
   */
  public <T extends model.Member> T selectMember(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Member m : list) {
        System.out.println(index + " | Name : " + m.getName());
        index++;
      }
      System.out.println("Please enter the index of the member you wish to login as");
      String key = input.nextLine();
      int intKey = Integer.parseInt(key);
      index = 0;
      for (T m : list) {
        if (index == intKey) {
          return m;
        }
        index++;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * Select an item from a list.
   *
   * @param <T> Item
   * @param list List of items.
   * @return Item.
   */
  public <T extends model.Item> T selectItem(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Item i : list) {
        System.out.println(index + " | Name : " + i.getName());
        index++;
      }
      if (index == 0) {
        throw new Exception("No items");
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * List members in a simple way.
   *
   * @param <T> Member.
   * @param list List of members.
   */
  public <T extends model.Member> void listMembersSimple(Iterable<T> list) {
    for (model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() 
          + " Credits : " + m.getCredit() + " Number of items : " + m.getNumberOfItems());
    }
  }

  /**
   * Lists members and their items in a verbose way.
   *
   * @param <T> Memeber.
   * @param list List of members.
   * @param currentDay Current day in the system.
   */
  public <T extends model.Member> void listMembersVerbose(Iterable<T> list, int currentDay) {
    for (model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail());
      System.out.println(" Items :");
      if (m.getNumberOfItems() != 0) {
        listMembersItems(m.getItems(), currentDay);
      } else {
        System.out.println(" No items.");
      }
    }
  }

  /**
   * List members items and if they have current contracts.
   *
   * @param <T> Item.
   * @param list List of item.
   * @param currentDay Systems current day.
   */
  private <T extends model.Item> void listMembersItems(Iterable<T> list, int currentDay) {
    for (model.Item i : list) {
      System.out.println(" Name : " + i.getName() + " Category : " + i.getCategory() 
          + " Description : " + i.getDescription() + " Cost per day : " + i.getCostPerday() 
          + " Rented : " + i.getRented());
      if (i.getRented() == true) {
        for (model.Contract c : i.getContractList().getContracts()) {
          if (c.getStartDay() < currentDay && c.getEndDay() >= currentDay) {
            System.out.println("Contract end day : " +  c.getEndDay() + " Start day : " 
                + c.getStartDay() + " Lender : " + c.getLender().getName());
          }
        }
      }
    }
  }

  /**
   * Displays any string put in.
   *
   * @param message String message.
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Select an item from the available items.
   *
   * @param <T> An item object.
   * @param list Iterable item list.
   * @return An item.
   */
  public <T extends model.Item> model.Item selectFromAllAvailableItems(Iterable<T> list) {
    int index = 0;
    for (model.Item i : list) {
      System.out.println(index + " Name : " + i.getName());
      System.out.println(" Please select index of item you wish to lend.");
      index++;
    }
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
   * Get a startday for the contract. 
   *
   * @return Integer of startday.
   */
  public int getContractStartDay() {
    System.out.println("On which day do you want to start your lending period?");
    String startDay = input.nextLine();
    int intStartDay = Integer.parseInt(startDay);
    return intStartDay;
  }

  /**
   * Gets how many days to lend the item.
   *
   * @return Integer of days.
   */
  public int getContractPeriod() {
    System.out.println("How many days do you wish to lend the item?");
    String days = input.nextLine();
    int intDays = Integer.parseInt(days);
    return intDays;
  }

  /**
   * To increase daytime counter.
   *
   * @param currentDay To show the current day,
   * @return Integer of days advancement.
   */
  public int time(int currentDay) {
    displayCurrentDay(currentDay);
    System.out.println("How many days do you want to advance time?");
    String days = input.nextLine();
    int daysInInteger = Integer.parseInt(days);
    return daysInInteger;
  }

  /**
   * Display the current day.
   *
   * @param currentDay An integer.
   */
  public void displayCurrentDay(int currentDay) {
    System.out.println(" The current day is : " + currentDay);
  }

  /**
   * View for deleting a member. 
   *
   * @return An enum.
   */
  public Delete deleteMember() {
    final String yes = "Y";
    final String no = "N";
    System.out.println("Are you sure you want to delete this member? "  + yes + " / " + no);
    String answer = input.nextLine();
    if (answer.equals(yes)) {
      return Delete.Yes;
    } else if (answer.equals(no)) {
      return Delete.No;
    }
    return null;
  }
}
