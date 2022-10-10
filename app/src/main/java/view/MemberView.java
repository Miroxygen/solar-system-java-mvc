package view;

import java.util.Scanner;
import model.Member;

/**
 * Represents the UI when editing a member.
 */
public class MemberView {
  private Scanner userInput = new Scanner(System.in, "utf-8");

  /**
   * Represents choices in UI.
   */
  public static enum MemberMenuChoices {
    InspectMember,
    ChangeMember,
    DeleteMember,
    EditMember,
    Return,
  }

  /**
   * Represents choices in UI.
   */
  public static enum EditMemberChoices {
    Name,
    PhoneNumber,
    Email,
  }

  /**
  * Menu for editing member.
  *
  * @param member Currently logged in as.
  * @return enum
  *
  */
  public MemberMenuChoices showMemberMenu(model.Member member) {
    System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
    System.out.println("=== 1. Look at members details.");
    System.out.println("=== 2. Edit this member.");
    System.out.println("=== 3. Delete this member.");
    System.out.println("=== 4. Back to main menu.");
    String inputKey = userInput.nextLine();
    switch (inputKey) {
      case "1":
        return MemberMenuChoices.InspectMember;
      case "2":
        return MemberMenuChoices.EditMember;
      case "3":
        return MemberMenuChoices.DeleteMember;
      case "4":
        return MemberMenuChoices.Return;
      default:
        return MemberMenuChoices.Return;
    }
  }

  /**
  * Creates non-mutable member.
  *
  * @return Member object.
  */
  public model.Member createMember() {
    System.out.println("=== Please enter a name.");
    String name = userInput.nextLine();
    System.out.println("=== Please enter a phone-number.");
    String phoneNumber = userInput.nextLine();
    System.out.println("=== Please enter an e-mail.");
    String email = userInput.nextLine();
    return new Member(name, phoneNumber, email);
  }

  /**
  * Lets user selct an available member.
  *
  * @param <T> Any child of member.
  * @param memberList Iterable with any T.
  * @return Object of memberchild.
  */
  public <T extends Member> T selectMember(Iterable<T> memberList) throws Exception {
    try {
      int index = 0;
      for (Member m : memberList) {
        System.out.println(index + ". | Name : " + m.getName() + " Email : " + m.getEmail());
        index++;
      }
      System.out.println("Please enter the index of the member you wish to login as");
      String stringIndex = userInput.nextLine();
      int selectedIndex = Integer.parseInt(stringIndex);
      index = 0;
      for (T m : memberList) {
        if (index == selectedIndex) {
          return m;
        }
        index++;
      }
      throw new Exception("Invalid index");
    } catch (Exception e) {
      throw e;
    }
    
  }

  /**
  * Shows currently logged in member.
  *
  * @param m Object of Member.
  */
  public void showMember(model.Member m) {  
    System.out.println(m.getClass());
    System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Phone-number : "
        + m.getPhoneNumber() + " Id : " + m.getId() + " Day of Creation : " + m.getDayOfCreation() 
          + " Credits : " + m.getCredit()); 
  }

  /**
  * Simple means without items and with more member info.
  *
  * @param <T> Any child of member.
  * @param members List of T's.
  * 
  */
  public <T extends Member> void displayAllMembersSimple(Iterable<T> members) {
    for (Member member : members) {
      System.out.println("Name : " + member.getName() + " Email : " + member.getEmail() 
          + " Current credits : " + member.getCredit() 
          + " Number of owned items : "  + member.getItemList().getNumberOfItems());
    }
  }

  /**
  * Verbose means with all items and their information. Less member info.
  *
  * @param <T> Any child of member.
  * @param members List of T's. 
  */
  public <T extends Member> void displayAllMembersVerbose(Iterable<T> members) {
    for (Member member : members) {
      System.out.println("Name : " + member.getName() + " Email : " + member.getEmail());
      System.out.println(" Owned items : ");
      for (model.Item i : member.getItemList().getItems()) {
        System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " 
            + i.getDescription() + " Cost per day : " + i.getCostPerday());
        if (i.getRented()) {
          System.out.println(" Currently lent to : " + i.getCurrentContract().getLender().getName() + " Start day : " 
              + i.getCurrentContract().getStartDay() + " End day : " + i.getCurrentContract().getEndDay());
        }
      }
    }
  }


  /**
  * Member's field.
  *
  * @return Enum choice.
  */
  public EditMemberChoices editMember() {
    System.out.println("Please enter what you would like to change : ");
    System.out.println("N for name.");
    System.out.println("P for phone-number.");
    System.out.println("E for e-mail.");
    String inputKey = userInput.nextLine();
    if (inputKey.equals("N")) {
      return EditMemberChoices.Name;
    } else if (inputKey.equals("P")) {
      return EditMemberChoices.PhoneNumber;
    } else if (inputKey.equals("E")) {
      return EditMemberChoices.Email;
    } else {
      return EditMemberChoices.Name;
    }
  }

  /**
  * Member field name.
  *
  * @return String.
  */
  public String newName() {
    System.out.println("Please enter the new name you wish to have :");
    String newName = userInput.nextLine();
    return newName;
  }

  /**
  * Member field phoneNumber.
  *
  * @return String.
  */
  public String newPhoneNumber() {
    System.out.println("Please enter the new phone-number you wish to have :");
    String newPhoneNumber = userInput.nextLine();
    return newPhoneNumber;
  }

  /**
  * Member field email.
  *
  * @return String
  */
  public String newEmail() {
    System.out.println("Please enter the new e-mail you wish to have :");
    String newEmail = userInput.nextLine();
    return newEmail;
  }

  /**
  * Helpful info.
  */
  public void succesfulAction() {
    System.out.println("Your action was succesful.");
  }

  /**
  * Deletes reference of Member.
  *
  * @param member Object of Member.
  * @return String.
  */
  public String deleteMember(model.Member member) {
    System.out.println("Are you sure you want to delete the logged in member?");
    showMember(member);
    System.out.println("Y/N");
    String inputKey = userInput.nextLine();
    return inputKey;
  }
}
