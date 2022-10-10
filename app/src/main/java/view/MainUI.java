package view;

import java.util.Scanner;

/**
 * Represents main view.
 */
public class MainUi {
  private Scanner userInput = new Scanner(System.in, "utf-8");

  /**
  * Static choices.
  */
  public static enum LoginChoices {
    Login,
    CreateMember,
    Quit,
    Time,
  }

  /**
   * Static choices.
   */
  public static enum MainMenuChoices {
    MemberMenu,
    ItemMenu,
    ListEverything,
    LendItem,
    Logout,
    Time,
  }

  /**
   * Static choices.
   */
  public static enum ListChoices {
    Simple,
    Verbose,
  }

  /**
   * Static choices.
   */
  public static enum TimeChoices {
    CurrentDay,
    AdvanceTime,
  }


  /**
   * Represents login UI.
   *
   * @param time Object.
   * @return Static enum.
   */
  public LoginChoices loginMenu(model.Time time) {
    System.out.println(" Current day : " + time.getCurrentDay());
    System.out.println("=== Welcome to the Stuff Lending Club! ===");
    System.out.println("=== 1. Login with existing member");
    System.out.println("=== 2. Create a new member");
    System.out.println("=== 3. Quit application.");
    System.out.println("=== 4. Handle time");
    String inputKey = userInput.nextLine();
    if (inputKey.equals("1")) {
      return LoginChoices.Login;
    } else if (inputKey.equals("2")) {
      return LoginChoices.CreateMember;
    } else if (inputKey.equals("3")) {
      return LoginChoices.Quit;
    } else if (inputKey.equals("4")) {
      return LoginChoices.Time;
    } else {
      return LoginChoices.Quit;
    }
  }

  /**
   * When logged in.
   *
   * @param member Object.
   * @param time Object.
   * @return
   * 
   */
  public MainMenuChoices mainMenu(model.Member member, model.Time time) {
    System.out.println(" Current day : " + time.getCurrentDay());
    System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
    System.out.println("=== 1. Edit member.");
    System.out.println("=== 2. Edit items.");
    System.out.println("=== 3. View all members and items.");
    System.out.println("=== 4. Lend an item");
    System.out.println("=== 5. Logout.");
    System.out.println("=== 6. Handle time.");
    String inputKey = userInput.nextLine();
    if (inputKey.equals("1")) {
      return MainMenuChoices.MemberMenu;
    } else if (inputKey.equals("2")) {
      return MainMenuChoices.ItemMenu;
    } else if (inputKey.equals("3")) {
      return MainMenuChoices.ListEverything;
    } else if (inputKey.equals("4")) {
      return MainMenuChoices.LendItem;
    } else if (inputKey.equals("5")) {
      return MainMenuChoices.Logout;
    } else if (inputKey.equals("6")) {
      return MainMenuChoices.Time;
    } else {
      return MainMenuChoices.MemberMenu;
    }
  }

  /**
  * Listing member and items.
  *
  * @param member Object.
  * @param time Object.
  * @return Static enum.
  * 
  */
  public ListChoices listChoices(model.Member member, model.Time time) {
    System.out.println(" Current day : " + time.getCurrentDay());
    System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
    System.out.println("=== 1. List in a simple way");
    System.out.println("=== 2. List in a verbose way");
    String inputKey = userInput.nextLine();
    if (inputKey.equals("1")) {
      return ListChoices.Simple;
    } else if (inputKey.equals("2")) {
      return ListChoices.Verbose;
    } else {
      return ListChoices.Simple;
    }
  }

  /**
  * For increasing daycounter.
  *
  * @param time Object.
  * @return Int.
  */
  public Integer advanceTime(model.Time time) {
    System.out.println(" Current day : " + time.getCurrentDay());
    System.out.println(" How many days do you want to advance?");
    String inputKey = userInput.nextLine();
    int daysToAdvance = Integer.parseInt(inputKey);
    return daysToAdvance;
  }


  /**
  * Helpful info.
  */
  public void resourceNotFound() {
    System.out.println("The requested resource was not found.");
  }

  /*
  * Helpful info.
  */
  public void succesfulAction() {
    System.out.println("Your action was succesful.");
  }

  /**
   * For printing error messgaes etc.
   *
   * @param text String.
   */
  public void displayText(String text) {
    System.out.println(text);
  }
}
