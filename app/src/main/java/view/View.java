package view;

import java.util.Scanner;

import model.Member;


public class View {
    Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum menuChoices {
        AddMember,
        Quit,
    }
    

    public menuChoices showMenu () {
        System.out.println("=== Welcome to the Stuff Lending Club! ===");
        System.out.println("=== 1. Add new member");
        System.out.println("=== 2. Quit application.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return menuChoices.AddMember;
            case "2":
                return menuChoices.Quit;
            default: 
            return menuChoices.Quit;
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

    public void showMember (model.Member m) {
        System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Phone-number : " + m.getPhoneNumber());
    }
}
