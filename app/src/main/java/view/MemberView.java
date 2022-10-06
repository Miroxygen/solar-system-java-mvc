package view;

import java.util.Scanner;

import model.Member;
import model.MemberList;

public class MemberView {
    private Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum memberMenuChoices {
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

    public memberMenuChoices showMemberMenu (model.Member member) {
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. Look at members details.");
        System.out.println("=== 2. Edit this member.");
        System.out.println("=== 3. Delete this member.");
        System.out.println("=== 4. Back to main menu.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return memberMenuChoices.InspectMember;
            case "2":
                return memberMenuChoices.EditMember;
            case "3":
                return memberMenuChoices.DeleteMember;
            case "4":
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

    public <T extends Member> T selectMember(Iterable<T> memberList) {
        int index = 0;
        for(Member m : memberList) {
            System.out.println(index + ". | Name : " + m.getName() + " Email : " + m.getEmail());
            index++;
        }
        System.out.println("Please enter the index of the member you wish to login as");
        String stringIndex = userInput.nextLine();
        int selectedIndex = Integer.parseInt(stringIndex);
        index = 0;
        for(T m : memberList) {
            if(index == selectedIndex) {
                return m;
            }
            index++;
        }
        return null;
    }

    public void showMember (model.Member m) {  
        System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Phone-number : " + m.getPhoneNumber() + " Id : " + m.getId() + " Day of Creation : " + m.getDayOfCreation() + " Credits : " + m.getCredit()); 
    }

    public <T extends Member> void displayAllMembers (Iterable<T> members) {
        for(Member member : members) {
            System.out.println("Name : " + member.getName() + " Email : " + member.getEmail() + " Current credits : " + member.getCredit() + 
            " Number of owned items : " + member.getItemList().getNumberOfItems());
        }
    }

    public <T extends Member> void displayAllMembersSimple (Iterable<T> members) {
        for(Member member : members) {
            System.out.println("Name : " + member.getName() + " Email : " + member.getEmail());
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
