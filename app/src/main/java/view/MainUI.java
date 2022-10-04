package view;

import java.util.Scanner;

import model.Item;
import model.Member;
import model.MemberList;


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
        ListEverything,
        LendItem,
        Logout,
    }

    public static enum listChoices {
        Simple,
        Verbose,
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
        System.out.println("=== 1. Edit member.");
        System.out.println("=== 2. Edit items.");
        System.out.println("=== 3. View all members and items.");
        System.out.println("=== 4. Lend an item");
        System.out.println("=== 5. Logout.");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return mainMenuChoices.MemberMenu;
        } else if(inputKey.equals("2")) {
            return mainMenuChoices.ItemMenu;
        } else if(inputKey.equals("3")) {
            return mainMenuChoices.ListEverything;
        } else if(inputKey.equals("4")) {
            return mainMenuChoices.LendItem;
        } else if(inputKey.equals("5")){
            return mainMenuChoices.Logout;
        } else {
            return mainMenuChoices.MemberMenu;
        }
    }

    public listChoices listChoices(model.Member member) {
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. List in a simple way");
        System.out.println("=== 2. List in a verbose way");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return listChoices.Simple;
        } else if(inputKey.equals("2")) {
            return listChoices.Verbose;
        } else {
            return listChoices.Simple;
        }
    }


    public void resourceNotFound() {
        System.out.println("The requested resource was not found.");
    }

    public void succesfulAction() {
        System.out.println("Your action was succesful.");
    }

    public void ListAllSimpleWay(MemberList members) {
        for(Member m : members.getMembers()) {
            System.out.println("Name : " + m.getName() + " Email : " + m.getEmail() + " Current credits : " + m.getCredit() + 
            " Number of owned items : " + m.getItemList().getNumberOfItems());
        }
    }

    public void ListAllVerboseWay(MemberList members) {
        for(Member m : members.getMembers()) {
            System.out.println("Name : " + m.getName() + " Email : " + m.getEmail());
            if(m.getItemList().getNumberOfItems() == 0) {
                System.out.println(" Member owns no items.");
            } else {
                for(Item i : m.getItemList().getItems()) {
                    System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription() + " Cost per day : " + i.getCostPerday() 
                    + " Day of creation : " + i.getDayOfCreation());
                }
            }
        }
    }
}
