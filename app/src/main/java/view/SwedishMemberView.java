package view;

import java.util.Scanner;
import model.Member;

/**
 * The Swedish memberview.
 */
public class SwedishMemberView implements MemberView {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * For displaying members profile.
   */
  public view.MemberView.Profile profile() {
    String details = "1";
    String edit = "2";
    String item = "3";
    System.out.println(details + " Medlemsdetaljer | " + edit + " Redigera medlem | " + item + " Redigera pryl");
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
   * For editing a member.
   */
  public Edit editMember() {
    String name = "1";
    String email = "2";
    String phone = "3";
    String delete = "4";
    System.out.println(" Gör ditt val : ");
    System.out.println(name + " Ändra namn |" + email + " Ändra email |" + phone 
        + " Ändra telefonnummer |" + delete + " Ta bort medlem |");
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
   * Displays the items menu.
   */
  public Item itemMenu() {
    String add = "1";
    String edit = "2";
    String inspect = "3";
    String delete = "4";
    System.out.println(" Gör ditt val :");
    System.out.println(add + " Lägg till en pryl | " + edit + " Redigera en pryl |" 
        + inspect + " Se pryldetaljer | " + delete + " Ta bort pryl | ");
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
   * For getting a string value.
   */
  public String getNewStringValue() {
    System.out.println(" Ange ett nytt värde : ");
    String value = input.nextLine();
    return value;
  }

  /**
   * For creating a new member.
   */
  public Member createMember() {
    System.out.println("=== Ange ett namn.");
    String name = input.nextLine();
    System.out.println("=== Ett telefonnummer.");
    String phoneNumber = input.nextLine();
    System.out.println("=== En email.");
    String email = input.nextLine();
    return new model.Member(name, phoneNumber, email);
  }

  /**
   * For selecting an exisiting member.
   */
  public <T extends Member> T selectMember(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Member m : list) {
        System.out.println(index + " | Namn : " + m.getName());
        index++;
      }
      System.out.println("Ange siffran för medlemmen du vill logga in som");
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
   * For seeing a members info.
   */
  public <T extends Member> void viewMember(Member m) {
    System.out.println(" Namn : " + m.getName() + " Email : " + m.getEmail() 
        + " Telefonnummer : " + m.getPhoneNumber() + " ID : " + m.getId() + " Skapades dag : " + m.getDayOfCreation());
  }
  
}
