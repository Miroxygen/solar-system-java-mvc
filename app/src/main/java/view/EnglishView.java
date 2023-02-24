package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The view for the controller.
 */
public class EnglishView implements View {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * Login view.
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
   * Menu view.
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
   * List view.
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
   * View for selecting a member.
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
   * View for selecting a member.
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
   * A simple members list.
   */
  public <T extends model.Member> void listMembersSimple(Iterable<T> list) {
    for (model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() 
          + " Credits : " + m.getCredit() + " Number of items : " + m.getNumberOfItems());
    }
  }


  /**
   * A verbose members list.
   */
  public <T extends model.Member> void listMembersVerbose(Iterable<T> list, int currentDay) {
    List<T> memberCopyList = new ArrayList<T>();
    for (T m : list) {
      memberCopyList.add(m);
    }
    Collections.sort(memberCopyList, (a, b) -> a.getId().compareTo(b.getId()));
    for (T m : memberCopyList) {
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
   * Lists a members items.
   */
  public <T extends model.Item> void listMembersItems(Iterable<T> list, int currentDay) {
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
   * For system out print.
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * For selecting from all available (not rented) items.
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
   * Gets a starting day for a contract.
   */
  public int getContractStartDay() {
    System.out.println("On which day do you want to start your lending period?");
    String startDay = input.nextLine();
    int intStartDay = Integer.parseInt(startDay);
    return intStartDay;
  }


  /**
   * Gets total lenght of contract.
   */
  public int getContractPeriod() {
    System.out.println("How many days do you wish to lend the item?");
    String days = input.nextLine();
    int intDays = Integer.parseInt(days);
    return intDays;
  }

  /**
   * For advancing day time counter.
   */
  public int time(int currentDay) {
    displayCurrentDay(currentDay);
    System.out.println("How many days do you want to advance time?");
    String days = input.nextLine();
    int daysInInteger = Integer.parseInt(days);
    return daysInInteger;
  }

  /**
   * Displays current day.
   */
  public void displayCurrentDay(int currentDay) {
    System.out.println(" The current day is : " + currentDay);
  }

  /**
   * Deletes a member.
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
