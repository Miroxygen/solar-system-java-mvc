package view;

import java.util.Scanner;


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
}
