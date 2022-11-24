package view;

import java.util.Scanner;

public class TestMemberView extends TestView {
  private Scanner input = new Scanner(System.in, "utf-8");

  public static enum Profile {
    Details,
    Edit,
    Item
  }

  public static enum Edit {
    Name,
    Email,
    Phone,
    Delete
  }

  public static enum Item {
    Add,
    Delete,
    Edit,
    Inspect
  }

  public Profile profile() {
    final String details = "d";
    final String edit = "e";
    final String item = "i";
    System.out.println(details + " Member details | " + edit + " Edit member | " + item + " Edit items");
    String key = input.nextLine();
    if (key.equals(details)) {
      return Profile.Details;
    } else if (key.equals(edit)) {
      return Profile.Edit;
    } else if (key.equals(item)) {
      return Profile.Item;
    }
    return null;
  }

  public Edit editMember() {
    final String name = "n";
    final String email = "e";
    final String phone = "p";
    final String delete = "d";
    System.out.println(" Please enter your action : ");
    System.out.println(name + " Edit name |" + email + " Edit email |" + phone + " Edit phonenumber |" + delete + " Delete Member |");
    String key = input.nextLine();
    if(key.equals(name)) {
      return Edit.Name;
    } else if(key.equals(email)) {
      return Edit.Email;
    } else if(key.equals(phone)) {
      return Edit.Phone;
    } else if(key.equals(delete)) {
      return Edit.Delete;
    }
    return null;
  }

  public Item itemMenu() {
    final String add = "a";
    final String delete = "d";
    final String edit = "e";
    final String inspect = "i";
    System.out.println(" Please enter your action :");
    System.out.println(add + " Add an item | " + delete + " Delete an item |" + edit + " Edit an item |" + inspect + " | Inspect an item | ");
    String key = input.nextLine();
    if(key.equals(add)) {
      return Item.Add;
    } else if(key.equals(delete)) {
      return Item.Delete;
    } else if(key.equals(edit)) {
      return Item.Edit;
    } else if(key.equals(inspect)) {
      return Item.Inspect;
    }
    return null;
  }

  public String getNewStringValue() {
    System.out.println(" Please enter your new value : ");
    String value = input.nextLine();
    return value;
  }

  public model.Member createMember() {
    System.out.println("=== Please enter a name.");
    String name = input.nextLine();
    System.out.println("=== Please enter a phone-number.");
    String phoneNumber = input.nextLine();
    System.out.println("=== Please enter an e-mail.");
    String email = input.nextLine();
    return new model.Member(name, phoneNumber, email);
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
      // TODO: handle exception
    }
    return null;
  }

	public <T extends model.Member> void viewMember(model.Member m) {
    System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() +
    " Phone-number : " + m.getPhoneNumber() + " ID : " + m.getId() + " Created on day : " + m.getDayOfCreation());
	}
}
