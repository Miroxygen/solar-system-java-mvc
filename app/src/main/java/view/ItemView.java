package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Item;
import model.MembersItemList;

public class ItemView {
    private Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum itemMenuChoices {
        AddItem,
        ViewItems,
        ViewOneItem,
        ChangeItem,
        DeleteItem,
        Back
    }

    public itemMenuChoices showItemMenu(model.Member member) {
        System.out.println("  ~ Welcome! You are logged in as : " + member.getName());
        System.out.println("=== 1. Add item to this member.");
        System.out.println("=== 2. Look at all available items.");
        System.out.println("=== 3. Look at detailed information about one item.");
        System.out.println("=== 4. Change one item.");
        System.out.println("=== 5. Delete one item.");
        System.out.println("=== 6. Back to main menu.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return itemMenuChoices.AddItem;
            case "2":
                return itemMenuChoices.ViewItems;
            case "3":
                return itemMenuChoices.ViewOneItem;
            case "4":
                return itemMenuChoices.ChangeItem;
            case "5":
                return itemMenuChoices.DeleteItem;
            case "6":
                return itemMenuChoices.Back;
            default:
                return itemMenuChoices.Back;
        }
    }

    public Item createItem() {
        System.out.println("=== Please enter a category for your item : (Tool, vehicle, Game, Toy, Sport, or Other)");
        String category = userInput.nextLine();
        System.out.println("=== Please enter a name for your item :");
        String name = userInput.nextLine();
        System.out.println("=== Please enter a short descrition for you item : ");
        String description = userInput.nextLine();
        System.out.println("=== Please enter a cost per day for your item : (10-100 credits)");
        int costPerDay = userInput.nextInt();
        return new Item(category, name, description, 0, costPerDay);
    }

    public  void showAllItems(ArrayList<MembersItemList> arrayList) {
        for(MembersItemList mil : arrayList) {
            System.out.println(" Owner : " + mil.getOwner().getName());
            System.out.println(" Items ");
            for(Item i : mil.getItems()) {
                System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription()
                + " Cost per day : " +  i.getCostPerday() + " Day of ceation : " + i.getDayOfCreation() + " Is it rented? " + i.getRented());
            }
        }
    }

    public void showOneItem(model.MembersItemList itemList) {
        System.out.println("=== Please enter name of item you want to see :");

    }   

    public void showMembersItems(MembersItemList itemList) {
        for(Item i : itemList.getItems()) {
            System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription()
                + " Cost per day : " +  i.getCostPerday() + " Day of ceation : " + i.getDayOfCreation());
        }
    }
}
