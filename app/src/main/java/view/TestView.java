package view;

import java.util.Scanner;

public class TestView {
  private Scanner input = new Scanner(System.in, "utf-8");

  public static enum Start {
    Login,
    Create,
    Quit
  }

  public static enum Menu {
    Profile,
    List,
    Contract,
    Time,
    Logout
  }

  public static enum List {
    Simple,
    Verbose
  }

  public Start login() {
    final String login = "L";
    final String create = "C";
    final String quit = "Q";
    System.out.println("=== Welcome to the Stuff Lending Club! ===");
    System.out.println("=== " + login +". Login with existing member");
    System.out.println("=== " + create + ". Create a new member");
    System.out.println("=== " + quit + ". Quit application.");
    String key = input.nextLine();
    if(key.equals(login)) {
      return Start.Login;
    } else if(key.equals(create)) {
      return Start.Create;
    } else if(key.equals(quit)) {
      return Start.Quit;
    }
    return null;
  }

  public Menu menu() {
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
    if(key.equals(edit)) {
      return Menu.Profile;
    } else if(key.equals(list)) {
      return Menu.List;
    } else if(key.equals(contract)) {
      return Menu.Contract;
    } else if(key.equals(time)) {
      return Menu.Time;
    } else if(key.equals(logout)) {
      return Menu.Logout;
    }
    return null;
  }

  public List list() {
    final String simple = "s";
    final String verbose = "v";
    System.out.println(simple + " - List simple | " + verbose + " - List verbose |");
    String key = input.nextLine();
    if (key.equals(simple)) {
      return List.Simple;
    } else if (key.equals(verbose)) {
      return List.Verbose;
    }
    return null;
  }

  public <T extends model.Member> T selectMember(Iterable<T> list) {
    try {
      int index = 0;
      for(model.Member m : list) {
        System.out.println(index + " | Name : " + m.getName());
        index++;
      }
      System.out.println("Please enter the index of the member you wish to login as");
      String key = input.nextLine();
      int intKey = Integer.parseInt(key);
      index = 0;
      for (T m : list) {
        if(index == intKey) {
          return m;
        }
        index++;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public <T extends model.Item> T selectItem(Iterable<T> list) {
    try {
      int index = 0;
      for(model.Item i : list) {
        System.out.println(index + " | Name : " + i.getName());
        index++;
      }
      if(index == 0) {
        throw new Exception("No items");
      }
      System.out.println("Please enter the index of the item");
      String key = input.nextLine();
      int intKey = Integer.parseInt(key);
      index = 0;
      for (T i : list) {
        if(index == intKey) {
          return i;
        }
        index++;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public <T extends model.Member> void listMembersSimple(Iterable<T> list) {
    for(model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() + " Credits : " + m.getCredit() + " Number of items : " + m.getNumberOfItems());
    }
  }

  public <T extends model.Member> void listMembersVerbose(Iterable<T> list, int currentDay) {
    for(model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail());
      System.out.println(" Items :");
      if(m.getNumberOfItems() != 0) {
        listMembersItems(m.getItems(), currentDay);
      } else {
        System.out.println(" No items.");
      }
    }
  }

  private <T extends model.Item> void listMembersItems(Iterable<T> list, int currentDay) {
    for (model.Item i : list) {
      System.out.println(" Name : " + i.getName() + " Category : " + i.getCategory() + " Description : " + i.getDescription() + " Cost per day : " + i.getCostPerday());
      for(model.Contract c : i.getContractList().getContracts()) {
        if(c.getStartDay() <= currentDay && c.getEndDay() >= currentDay) {
          System.out.println("Contract end day : " +  c.getEndDay() + " Start day : " + c.getStartDay() + " Lender :" + c.getLender());
        }
      }
    }
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

  public <T extends model.Item> model.Item selectFromAllAvailableItems(Iterable<T> list) {
    int index = 0;
    for(model.Item i : list) {
      System.out.println(index + " Name : " + i.getName());
      System.out.println(" Please select index of item you wish to lend.");
      index++;
    }
    String key = input.nextLine();
      int intKey = Integer.parseInt(key);
      index = 0;
      for (T i : list) {
        if(index == intKey) {
          return i;
        }
        index++;
      }
    return null;
  }

  public int getContractPeriod() {
    System.out.println("How many days do you wish to lend the item?");
    String days = input.nextLine();
    int intDays = Integer.parseInt(days);
    return intDays;
  }
}
