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

    public static enum changeItemChoices {
        Category,
        Name,
        Description,
        Cost,
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
        System.out.println("=== Please enter a category for your item : (Tool, Vehicle, Game, Toy, Sport, or Other)");
        String category = userInput.nextLine();
        System.out.println("=== Please enter a name for your item :");
        String name = userInput.nextLine();
        System.out.println("=== Please enter a short descrition for you item : ");
        String description = userInput.nextLine();
        System.out.println("=== Please enter a cost per day for your item : (10-100 credits)");
        int costPerDay = userInput.nextInt();
        userInput.nextLine();
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

    public void showOneItem(Item item) {
        System.out.println("Category : " + item.getCategory() + " Name : " + item.getName() + " Description : " + item.getDescription()
        + " Cost per day : " +  item.getCostPerday() + " Day of ceation : " + item.getDayOfCreation());
    }

    public Item selectOneItem(MembersItemList itemList) {
        int index = 0;
        for(Item i : itemList.getItems()) {
            System.out.println("Index : " + index + " Item : " + i.getName());
            index++;
        }
        System.out.println("=== Please enter the index of item you want to select :");
        int selectedIndex = userInput.nextInt();
        userInput.nextLine();
        index = 0;
        for(Item i : itemList.getItems()) {
            if(index == selectedIndex) {
                return i;
            }
            index++;
        }
        return null;
    }   

    public void showMembersItems(MembersItemList itemList) {
        for(Item i : itemList.getItems()) {
            System.out.println("Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription()
                + " Cost per day : " +  i.getCostPerday() + " Day of ceation : " + i.getDayOfCreation());
        }
    }

    public changeItemChoices changeItem() {
        System.out.println("=== What trait do you wish to change?");
        System.out.println(" 1. Category.");
        System.out.println(" 2. Name.");
        System.out.println(" 3. Description.");
        System.out.println(" 4. Price per day");
        String inputKey = userInput.nextLine();
        if(inputKey.equals("1")) {
            return changeItemChoices.Category;
        } else if(inputKey.equals("2")) {
            return changeItemChoices.Name;
        } else if(inputKey.equals("3")) {
            return changeItemChoices.Description;
        } else if(inputKey.equals("4")) {
            return changeItemChoices.Cost;
        } else {
            return changeItemChoices.Name;
        }
    }

    public String newStringValue() {
        System.out.println(" Please enter the new value you want :");
        String inputKey = userInput.nextLine();
        return inputKey;
    }

    public int newIntValue() {
        System.out.println(" Please enter the new value you want :");
        int inputKey = userInput.nextInt();
        return inputKey;
    }
}
