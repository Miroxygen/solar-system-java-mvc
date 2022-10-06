package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Item;
import model.MembersItemList;
import model.MutableItem;

public class ItemUI {
    private Scanner userInput = new Scanner(System.in, "utf-8");

    public static enum itemMenuChoices {
        AddItem,
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
        System.out.println("=== 2. Look at detailed information about one item.");
        System.out.println("=== 3. Change one item.");
        System.out.println("=== 4. Delete one item.");
        System.out.println("=== 5. Back to main menu.");
        String inputKey = userInput.nextLine();
        switch (inputKey) {
            case "1":
                return itemMenuChoices.AddItem;
            case "2":
                return itemMenuChoices.ViewOneItem;
            case "3":
                return itemMenuChoices.ChangeItem;
            case "4":
                return itemMenuChoices.DeleteItem;
            case "5":
                return itemMenuChoices.Back;
            default:
                return itemMenuChoices.Back;
        }
    }

    public Item createItem(int currentDay) {
        System.out.println("=== Please enter a category for your item : (Tool, Vehicle, Game, Toy, Sport, or Other)");
        String category = userInput.nextLine();
        System.out.println("=== Please enter a name for your item :");
        String name = userInput.nextLine();
        System.out.println("=== Please enter a short descrition for you item : ");
        String description = userInput.nextLine();
        System.out.println("=== Please enter a cost per day for your item : (10-100 credits)");
        int costPerDay = userInput.nextInt();
        userInput.nextLine();
        return new Item(category, name, description, currentDay, costPerDay);
    }

    public Item getLendableItem(ArrayList<MembersItemList> arrayList, model.Member selectedMember) throws Exception {
        int index = 0;
        for(MembersItemList mil : arrayList) {
            if(mil.getOwner() != selectedMember) {
                for(Item i : mil.getItems()) {
                    System.out.println(index +" | Category : " + i.getCategory() + " Name : " + i.getName() + " Description : " + i.getDescription()
                    + " Cost per day : " +  i.getCostPerday());
                    index++;
                }
            }
        }
        if(index == 0) {
            System.out.println("No available items.");
            throw new Exception("No items.");
        }
        System.out.println("=== Please enter the index of item you want to select :");
        int selectedIndex = userInput.nextInt();
        userInput.nextLine();
        index = 0;
        for(MembersItemList mil : arrayList) {
            for(Item i : mil.getItems()) {
                if(index == selectedIndex) {
                    return i;
                }
                if(i.getRented() == false) {
                    index++;
                }   
            }
        }
        return null;
    }

    public void showOneItem(Item item) {
        System.out.println("Category : " + item.getCategory() + " Name : " + item.getName() + " Description : " + item.getDescription()
        + " Cost per day : " +  item.getCostPerday() + " Day of ceation : " + item.getDayOfCreation());
        int index = 0;
        for(model.Contract c : item.getOldContracts()) {
            System.out.println("Old contracts : ");
            System.out.println(" Lender : " + c.getLender() + " Startday : " + c.getStartDay() + " Endday :" + c.getEndDay());
            index++;
        }
        if(index == 0) {
            System.out.println("No old contracts.");
        }
        index = 0;
        for(model.Contract c : item.getFutureContracts()) {
            System.out.println("Future contracts : ");
            System.out.println(" Lender : " + c.getLender() + " Startday : " + c.getStartDay() + " Endday :" + c.getEndDay());
            index++;
        }
        if(index == 0) {
            System.out.println("No future contracts.");
        }
    }

    public <T extends Item> T selectItemFromCurrentMember(Iterable<T> itemList) throws Exception {
        if(itemList == null) {
            throw new Exception("No items.");
        } else {
            int index = 0;
            for(Item i : itemList) {
                System.out.println("Index : " + index + " Item : " + i.getName());
                index++;
            }
            System.out.println("=== Please enter the index of item you want to select :");
            int selectedIndex = userInput.nextInt();
            userInput.nextLine();
            index = 0;
            for(T i : itemList) {
                if(index == selectedIndex) {
                    return i;
                }
                index++;
            }
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

    public void showAllItemsVerbose(ArrayList<MembersItemList> membersItemsList) {
        for(MembersItemList mil : membersItemsList) {
            System.out.println(mil.getOwner().getName());
            for(Item i : mil.getItems()) {
                System.out.println(i.getCategory() + i.getName() + i.getDescription() + i.getCostPerday());
            }
        }
    }
}
