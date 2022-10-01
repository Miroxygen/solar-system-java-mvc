package view;

import java.util.Scanner;

import model.Member;
import model.MemberList;


public class View {
    Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum loginChoices {
        Login,
        CreateMember,
        Quit,
    }

    public static enum mainMenuChoices {
        MemberMenu,
        Logout,
    }

    public static enum memberMenuChoices {
        ListMembers,
        InspectMember,
        ChangeMember,
        DeleteMember,
        EditMember,
        Return,
    }

    public static enum editMemberChoices {
        Name,
        PhoneNumber,
        Email,
    }
    

    public loginChoices loginMenu () {
        System.out.println("=== Welcome to the Stuff Lending Club! ===");
        System.out.println("=== 1. Login with existing member");
        System.out.println("=== 2. Create a new member");
        System.out.println("=== 3. Quit application.");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return loginChoices.Login;
        } else if(inputKey.equals("2")) {
            return loginChoices.CreateMember;
        } else if(inputKey.equals("3")) {
            return loginChoices.Quit;
        } else {
            return loginChoices.Quit;
        }
    }

    public mainMenuChoices mainMenu (model.Member member) {
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. View membermenu");
        System.out.println("=== 2. Logout");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return mainMenuChoices.MemberMenu;
        } else if(inputKey.equals("2")) {
            return mainMenuChoices.Logout;
        } else {
            return mainMenuChoices.MemberMenu;
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
        System.out.println("=== 3. Edit this member.");
        System.out.println("=== 4. Delete this member.");
        System.out.println("=== 5. Logout.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return memberMenuChoices.InspectMember;
            case "2":
            return memberMenuChoices.ListMembers;
            case "3":
                return memberMenuChoices.EditMember;
            case "4":
                return memberMenuChoices.DeleteMember;
            case "5":
                return memberMenuChoices.Return;
            default:
                return memberMenuChoices.Return;
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

    public editMemberChoices editMember() {
        System.out.println("Please enter what you would like to change : ");
        System.out.println("N for name.");
        System.out.println("P for phone-number.");
        System.out.println("E for e-mail.");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("N")) {
            return editMemberChoices.Name;
        } else if(inputKey.equals("P")) {
            return editMemberChoices.PhoneNumber;
        } else if(inputKey.equals("E")) {
            return editMemberChoices.Email;
        } else {
            return editMemberChoices.Name;
        }
    }

    public String newName() {
        System.out.println("Please enter the new name you wish to have :");
        String newName = userInput.nextLine();
        return newName;
    }

    public String newPhoneNumber() {
        System.out.println("Please enter the new phone-number you wish to have :");
        String newPhoneNumber = userInput.nextLine();
        return newPhoneNumber;
    }

    public String newEmail() {
        System.out.println("Please enter the new e-mail you wish to have :");
        String newEmail = userInput.nextLine();
        return newEmail;
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
