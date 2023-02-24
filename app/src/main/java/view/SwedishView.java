package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import model.Item;
import model.Member;

/**
 * Thw Swedish main view.
 */
public class SwedishView implements View {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * For loggin in.
   */
  public Start login() {
    final String login = "1";
    final String create = "2";
    final String quit = "3";
    System.out.println("=== Välkommen till Pryllånarklubben! ===");
    System.out.println("=== " + login + ". Logga in");
    System.out.println("=== " + create + ". Skapa ny medlem");
    System.out.println("=== " + quit + ". Avsluta.");
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
   * For displaying main menu.
   */
  public Menu menu(int currentDay) {
    displayCurrentDay(currentDay);
    final String edit = "1";
    final String list = "2";
    final String contract = "3";
    final String time = "4";
    final String logout = "5";
    System.out.println("=== " + edit + "| Medlemsprofil");
    System.out.println("=== " + list + "| Medlemslista");
    System.out.println("=== " + contract + "| Skapa ett kontrakt");
    System.out.println("=== " + time + "| Öka på tid");
    System.out.println("=== " + logout + "| Logga ut");
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
   * For viewing lists.
   */
  public ListChoice list() {
    final String simple = "1";
    final String verbose = "2";
    System.out.println(simple + " - Enkel lista | " + verbose + " - Långrandig lista |");
    String key = input.nextLine();
    if (key.equals(simple)) {
      return ListChoice.Simple;
    } else if (key.equals(verbose)) {
      return ListChoice.Verbose;
    }
    return null;
  }


  /**
   * For selecting a member.
   */
  public <T extends Member> T selectMember(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Member m : list) {
        System.out.println(index + " | Namn : " + m.getName());
        index++;
      }
      System.out.println("Ange siffran för rätt medlem");
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
   * For selecting an item.
   */
  public <T extends Item> T selectItem(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Item i : list) {
        System.out.println(index + " | Namn : " + i.getName());
        index++;
      }
      if (index == 0) {
        throw new Exception("Inga prylar");
      }
      System.out.println("Ange siffran för rätt pryl");
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
   * For viewing a simple memberslist.
   */
  public <T extends Member> void listMembersSimple(Iterable<T> list) {
    for (model.Member m : list) {
      System.out.println(" Namn : " + m.getName() + " Email : " + m.getEmail() 
          + " Pengar : " + m.getCredit() + " Antal prylar : " + m.getNumberOfItems());
    }
  }


  /**
   * For viewing a verbose memberslist.
   */
  public <T extends Member> void listMembersVerbose(Iterable<T> list, int currentDay) {
    List<T> memberCopyList = new ArrayList<T>();
    for (T m : list) {
      memberCopyList.add(m);
    }
    Collections.sort(memberCopyList, (a, b) -> a.getName().compareTo(b.getName()));
    for (T m : memberCopyList) {
      System.out.println(" Namn : " + m.getName() + " Email : " + m.getEmail());
      System.out.println(" Prylar :");
      if (m.getNumberOfItems() != 0) {
        listMembersItems(m.getItems(), currentDay);
      } else {
        System.out.println(" Inga prylar.");
      }
    }
  }



  /**
   * For listing all members items.
   */
  public <T extends Item> void listMembersItems(Iterable<T> list, int currentDay) {
    for (model.Item i : list) {
      System.out.println(" Namn : " + i.getName() + " Kategori : " + i.getCategory() 
          + " Beskrivning : " + i.getDescription() + " Kostnad per dag : " + i.getCostPerday() 
          + " Lånad : " + i.getRented());
      if (i.getRented() == true) {
        for (model.Contract c : i.getContractList().getContracts()) {
          if (c.getStartDay() < currentDay && c.getEndDay() >= currentDay) {
            System.out.println("Kontraktets slutdag : " +  c.getEndDay() + " Startdag : " 
                + c.getStartDay() + " Lånare : " + c.getLender().getName());
          }
        }
      }
    }
  }


  /**
   * For displaying a message.
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }


  /**
   * Selects from all non-rented items.
   */
  public <T extends Item> Item selectFromAllAvailableItems(Iterable<T> list) {
    int index = 0;
    for (model.Item i : list) {
      System.out.println(index + " Namn : " + i.getName());
      System.out.println(" Välj siffran för prylen du vill låna");
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
   * Gets a contracts start day.
   */
  public int getContractStartDay() {
    System.out.println("Vilken dag vill du börja låna?");
    String startDay = input.nextLine();
    int intStartDay = Integer.parseInt(startDay);
    return intStartDay;
  }


  /**
   * Gets a contracts period.
   */
  public int getContractPeriod() {
    System.out.println("Hur många dagar vill du låna?");
    String days = input.nextLine();
    int intDays = Integer.parseInt(days);
    return intDays;
  }


  /**
   * For advancing time.
   */
  public int time(int currentDay) {
    displayCurrentDay(currentDay);
    System.out.println("Hur många dagar vill du öka tiden med?");
    String days = input.nextLine();
    int daysInInteger = Integer.parseInt(days);
    return daysInInteger;
  }


  /**
   * Displays the current day.
   */
  public void displayCurrentDay(int currentDay) {
    System.out.println(" Dag : " + currentDay);
  }


  /**
   * For deleting a member.
   */
  public Delete deleteMember() {
    final String yes = "1";
    final String no = "2";
    System.out.println("Vill du verkligen ta bort den här medlemmen? "  + yes + " / " + no);
    String answer = input.nextLine();
    if (answer.equals(yes)) {
      return Delete.Yes;
    } else if (answer.equals(no)) {
      return Delete.No;
    }
    return null;
  }
  
}
