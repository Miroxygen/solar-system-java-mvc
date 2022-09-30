package view;

import java.util.Scanner;

import model.Member;
import model.MemberList;


public class View {
    Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum menuChoices {
        MemberMenu,
        Quit,
    }

    public static enum memberMenuChoices {
        AddMember,
        ListMembers,
        InspectMember,
        ChangeMember,
        DeleteMember,
        Back,
    }
    

    public menuChoices showMenu () {
        System.out.println("=== Welcome to the Stuff Lending Club! ===");
        System.out.println("=== 1. Go to member menu");
        System.out.println("=== 2. Quit application.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return menuChoices.MemberMenu;
            case "2":
                return menuChoices.Quit;
            default: 
            return menuChoices.Quit;
        }
    }

    public memberMenuChoices showMemberMenu () {
        System.out.println("=== 1. Create a new member");
        System.out.println("=== 2. Look at full member details");
        System.out.println("=== 3. Look at all members");
        System.out.println("=== 4. Delete a member");
        System.out.println("=== 5. Back to main menu");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return memberMenuChoices.AddMember;
            case "2":
                return memberMenuChoices.InspectMember;
            case "3":
            return memberMenuChoices.ListMembers;
            case "4":
                return memberMenuChoices.DeleteMember;
            case "5":
                return memberMenuChoices.Back;
            default:
                return memberMenuChoices.Back;
        }
    }

    public model.Member createMember () {
        System.out.println("=== Please enter a name.");
        String name = userInput.nextLine();
        System.out.println("=== Please enter a phone-number.");
        String phoneNumber = userInput.nextLine();
        System.out.println("=== Please enter an e-mail.");
        String email = userInput.nextLine();
        return new Member(name, phoneNumber, email);
    }

    public String inspectMember() {
        System.out.println("=== Please enter name of member");
        String name = userInput.nextLine();
        return name;
    }

    public void showMember (model.Member m) {  
        System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Phone-number : " + m.getPhoneNumber() + " Id : " + m.getId() + " Day of Creation : " + m.getDayOfCreation() + " Credits : " + m.getCredit()); 
    }

    public void showMemberList(model.MemberList memberList) {
        for(Member m : memberList.getMembers()) {
            System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Phone-number : " + m.getPhoneNumber());
        }
    }

    public void resourceNotFound() {
        System.out.println("The requested resource was not found.");
    }

    public void deleteMember() {

    }
}
