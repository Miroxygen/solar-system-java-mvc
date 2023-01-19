package view;

import java.util.Scanner;

/**
 * View for member profile.
 */
public class EnglishMemberView implements MemberView {
  private Scanner input = new Scanner(System.in, "utf-8");
  final String details = "d";
  final String edit = "e";
  final String item = "i";
  final String name = "n";
  final String email = "e";
  final String phone = "p";
  final String delete = "d";
  final String add = "a";
  final String inspect = "i";


  /**
   * View member profile. 
   *
   * @return Enum.
   */
  public view.MemberView.Profile profile() {
    System.out.println(details + " Member details | " + edit + " Edit member | " + item + " Edit items");
    String key = input.nextLine();
    if (key.equals(details)) {
      return view.MemberView.Profile.Details;
    } else if (key.equals(edit)) {
      return view.MemberView.Profile.Edit;
    } else if (key.equals(item)) {
      return view.MemberView.Profile.Item;
    }
    return null;
  }

  /**
   * Edit member menu. 
   *
   * @return Enum.
   */
  public view.MemberView.Edit editMember() {
    System.out.println(" Please enter your action : ");
    System.out.println(name + " Edit name |" + email + " Edit email |" + phone 
        + " Edit phonenumber |" + delete + " Delete Member |");
    String key = input.nextLine();
    if (key.equals(name)) {
      return view.MemberView.Edit.Name;
    } else if (key.equals(email)) {
      return view.MemberView.Edit.Email;
    } else if (key.equals(phone)) {
      return view.MemberView.Edit.Phone;
    } else if (key.equals(delete)) {
      return view.MemberView.Edit.Delete;
    }
    return null;
  }

  /**
   * Edit members items menu.
   *
   * @return Enum.
   */
  public view.MemberView.Item itemMenu() {
    System.out.println(" Please enter your action :");
    System.out.println(add + " Add an item | " + delete + " Delete an item |" 
        + edit + " Edit an item |" + inspect + " Inspect an item | ");
    String key = input.nextLine();
    if (key.equals(add)) {
      return view.MemberView.Item.Add;
    } else if (key.equals(delete)) {
      return view.MemberView.Item.Delete;
    } else if (key.equals(edit)) {
      return view.MemberView.Item.Edit;
    } else if (key.equals(inspect)) {
      return view.MemberView.Item.Inspect;
    }
    return null;
  }

  /**
   * Gets a new value. 
   *
   * @return String value.
   */
  public String getNewStringValue() {
    System.out.println(" Please enter your new value : ");
    String value = input.nextLine();
    return value;
  }

  /**
   * Menu for creating new member. 
   *
   * @return Member.
   */
  public model.Member createMember() {
    System.out.println("=== Please enter a name.");
    String name = input.nextLine();
    System.out.println("=== Please enter a phone-number.");
    String phoneNumber = input.nextLine();
    System.out.println("=== Please enter an e-mail.");
    String email = input.nextLine();
    return new model.Member(name, phoneNumber, email);
  }

  /**
   * Selects a member.
   *
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
   * For looking at members info.
   *
   * @param <T> Anything of member.
   * @param m Member to look at.
   */
  public <T extends model.Member> void viewMember(model.Member m) {
    System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() 
        + " Phone-number : " + m.getPhoneNumber() + " ID : " + m.getId() + " Created on day : " + m.getDayOfCreation());
  }
}
