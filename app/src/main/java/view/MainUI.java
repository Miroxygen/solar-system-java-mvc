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
        Time,
    }

    public static enum mainMenuChoices {
        MemberMenu,
        ItemMenu,
        ListEverything,
        LendItem,
        Logout,
        Time,
    }

    public static enum listChoices {
        Simple,
        Verbose,
    }

    public static enum timeChoices {
        CurrentDay,
        AdvanceTime,
    }
    

    public loginChoices loginMenu (model.Time time) {
        System.out.println(" Current day : " + time.getCurrentDay());
        System.out.println("=== Welcome to the Stuff Lending Club! ===");
        System.out.println("=== 1. Login with existing member");
        System.out.println("=== 2. Create a new member");
        System.out.println("=== 3. Quit application.");
        System.out.println("=== 4. Handle time");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return loginChoices.Login;
        } else if(inputKey.equals("2")) {
            return loginChoices.CreateMember;
        } else if(inputKey.equals("3")) {
            return loginChoices.Quit;
        } else if(inputKey.equals("4")) {
            return loginChoices.Time;
        } else {
            return loginChoices.Quit;
        }
    }

    public mainMenuChoices mainMenu (model.Member member, model.Time time) {
        System.out.println(" Current day : " + time.getCurrentDay());
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. Edit member.");
        System.out.println("=== 2. Edit items.");
        System.out.println("=== 3. View all members and items.");
        System.out.println("=== 4. Lend an item");
        System.out.println("=== 5. Logout.");
        System.out.println("=== 6. Handle time.");
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
        } else if(inputKey.equals("6")) {
            return mainMenuChoices.Time;
        } else {
            return mainMenuChoices.MemberMenu;
        }
    }

    public listChoices listChoices(model.Member member, model.Time time) {
        System.out.println(" Current day : " + time.getCurrentDay());
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

    public Integer advanceTime(model.Time time) {
        System.out.println(" Current day : " + time.getCurrentDay());
        System.out.println(" How many days do you want to advance?");
        String inputKey = userInput.nextLine();
        int daysToAdvance = Integer.parseInt(inputKey);
        return daysToAdvance;
    }
 

    public void resourceNotFound() {
        System.out.println("The requested resource was not found.");
    }

    public void succesfulAction() {
        System.out.println("Your action was succesful.");
    }

    public void ListAllSimpleWay(MemberList members) {
        
    }

    public <T extends Member> void listAllVerboseWay(Iterable<T> memberList) {
        for(Member m : memberList) {
            System.out.println("Name : " + m.getName() + " Email : " + m.getEmail());
            if(m.getItemList().getNumberOfItems() == 0) {
                System.out.println(" Member owns no items.");
            } else {
                for(model.MutableItem i : m.getItemList().getItems()) {
                    System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription() + " Cost per day : " + i.getCostPerday() 
                    + " Day of creation : " + i.getDayOfCreation());
                }
            }
        }
    }
}
