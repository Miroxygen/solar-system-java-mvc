package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Item;
import model.Member;

public class SwedishView implements View {
  private Scanner input = new Scanner(System.in, "utf-8");
  private final String login = "1";
  private final String create = "2";
  private final String quit = "3";
  private final String edit = "1";
  private final String list = "2";
  private final String contract = "3";
  private final String time = "4";
  private final String logout = "5";
  private final String simple = "1";
  private final String verbose = "2";

  public Start login() {
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


  public Menu menu(int currentDay) {
    displayCurrentDay(currentDay);
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


  public ListChoice list() {
    System.out.println(simple + " - Enkel lista | " + verbose + " - Långrandig lista |");
    String key = input.nextLine();
    if (key.equals(simple)) {
      return ListChoice.Simple;
    } else if (key.equals(verbose)) {
      return ListChoice.Verbose;
    }
    return null;
  }


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


  public <T extends Member> void listMembersSimple(Iterable<T> list) {
    for (model.Member m : list) {
      System.out.println(" Namn : " + m.getName() + " Email : " + m.getEmail() 
          + " Pengar : " + m.getCredit() + " Antal prylar : " + m.getNumberOfItems());
    }
  }


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


  public void displayMessage(String message) {
    System.out.println(message);
  }


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


  public int getContractStartDay() {
    System.out.println("Vilken dag vill du börja låna?");
    String startDay = input.nextLine();
    int intStartDay = Integer.parseInt(startDay);
    return intStartDay;
  }


  public int getContractPeriod() {
    System.out.println("Hur många dagar vill du låna?");
    String days = input.nextLine();
    int intDays = Integer.parseInt(days);
    return intDays;
  }


  public int time(int currentDay) {
    displayCurrentDay(currentDay);
    System.out.println("Hur många dagar vill du öka tiden med?");
    String days = input.nextLine();
    int daysInInteger = Integer.parseInt(days);
    return daysInInteger;
  }


  public void displayCurrentDay(int currentDay) {
    System.out.println(" Dag : " + currentDay);
  }


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
