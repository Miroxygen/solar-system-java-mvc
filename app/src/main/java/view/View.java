package view;

import java.util.Scanner;

import model.Member;
import model.MemberList;


public class View {
    Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum menuChoices {
        Login,
        CreateMember,
        Quit,
    }

    public static enum memberMenuChoices {
        ListMembers,
        InspectMember,
        ChangeMember,
        DeleteMember,
        Logout,
    }
    

    public menuChoices loginMenu () {
        System.out.println("=== Welcome to the Stuff Lending Club! ===");
        System.out.println("=== 1. Login with existing member");
        System.out.println("=== 2. Create a new member");
        System.out.println("=== 3. Quit application.");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return menuChoices.Login;
        } else if(inputKey.equals("2")) {
            return menuChoices.CreateMember;
        } else if(inputKey.equals("3")) {
            return menuChoices.Quit;
        } else {
            return menuChoices.Quit;
        }
    }

    public String login(model.MemberList ml) {
        showMemberList(ml);
        System.out.println("Please enter the name of the member you wish to login as");
        String inputKey = userInput.nextLine();
        return inputKey;
    }

    public memberMenuChoices showMemberMenu (model.Member member) {
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. Look at this members details.");
        System.out.println("=== 2. Look at all members.");
        System.out.println("=== 3. Delete this member.");
        System.out.println("=== 4. Logout.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return memberMenuChoices.InspectMember;
            case "2":
            return memberMenuChoices.ListMembers;
            case "3":
                return memberMenuChoices.DeleteMember;
            case "4":
                return memberMenuChoices.Logout;
            default:
                return memberMenuChoices.Logout;
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

    public void succesfulAction() {
        System.out.println("Your action was succesful.");
    }

    public String deleteMember(model.Member member) {
        System.out.println("Are you sure you want to delete the logged in member?");
        showMember(member);
        System.out.println("Y/N");
        String inputKey = userInput.nextLine();
        return inputKey;
    }
}
