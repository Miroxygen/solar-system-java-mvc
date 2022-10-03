package view;

import java.util.Scanner;


public class MainUI {
    private Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum loginChoices {
        Login,
        CreateMember,
        Quit,
    }

    public static enum mainMenuChoices {
        MemberMenu,
        ItemMenu,
        Logout,
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
        System.out.println("=== 2. View itemmenu.");
        System.out.println("=== 3. Logout");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return mainMenuChoices.MemberMenu;
        } else if(inputKey.equals("2")) {
            return mainMenuChoices.ItemMenu;
        } else if(inputKey.equals("3")) {
            return mainMenuChoices.Logout;
        } else  {
            return mainMenuChoices.MemberMenu;
        }
    }


    public void resourceNotFound() {
        System.out.println("The requested resource was not found.");
    }

    public void succesfulAction() {
        System.out.println("Your action was succesful.");
    }
}
