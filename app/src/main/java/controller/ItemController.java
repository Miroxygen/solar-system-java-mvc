package controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import model.Item;
import model.Member;
import model.MembersItemList;
import view.ItemView;
import view.ItemView.changeItemChoices;
import view.ItemView.itemMenuChoices;

public class ItemController {
    private view.ItemView itemUI = new ItemView();
    private ArrayList<MembersItemList> allMembersItemList = new ArrayList<MembersItemList>();
    private MembersItemList currentMembersItemlist = null;
    
    
    public void ItemMenu(Member selectedMember) {
        itemMenuChoices action = itemUI.showItemMenu(selectedMember);
        switch (action) {
            case AddItem:
                addItem(selectedMember);
                break;
            case ViewItems:
                itemUI.showAllItems(allMembersItemList);
            case ViewOneItem:
                itemUI.showOneItem(itemUI.selectOneItem(currentMembersItemlist));
                break;
            case ChangeItem:
                changeItemMenu();
                break;
            case DeleteItem:
                deleteItem();
                break;
            case Back:
                return;
        }
    }

    public void changeItemMenu() {
        Item itemToChange = itemUI.selectOneItem(currentMembersItemlist);
        changeItemChoices action = itemUI.changeItem();
        switch (action) {
            case Category:
                changeCategory(itemToChange);
                break;
            case Name:
            changeName(itemToChange);
                break;
            case Description:
                changeDescription(itemToChange);
                break;
            case Cost:
                changeCostPerDay(itemToChange);
                break;
        }
    }

    public void changeCategory(Item itemToChange) {
        String category = itemUI.newStringValue();
        itemToChange.setCategory(category);
    }

    public void changeName(Item itemToChange) {
        String name = itemUI.newStringValue();
        itemToChange.setName(name);
    }

    public void changeDescription(Item itemToChange) {
        String description = itemUI.newStringValue();
        itemToChange.setDescription(description);
    }

    public void changeCostPerDay(Item itemToChange) {
        int costPerDay = itemUI.newIntValue();
        itemToChange.setCostPerDay(costPerDay);
    }

    public void addItem(Member selectedMember) {
        try {
        setCurrentItemList(selectedMember);
        model.Item newItem = itemUI.createItem();
        handleCategoryErrors(newItem.getCategory());
        handleCostError(newItem.getCostPerday());
        selectedMember.addCredit(100);
        currentMembersItemlist.addItem(newItem);
        } catch (Exception e) {
           System.out.println(e.getMessage() + "| Your item was NOT created.");
        }  
    }


    public void deleteItem() {
        Item selectedItem = itemUI.selectOneItem(currentMembersItemlist);
        currentMembersItemlist.deleteItem(selectedItem);
    }

    public void setCurrentItemList(Member selectedMember) {
        currentMembersItemlist = selectedMember.getItemList();
        addCurrentListIfItHasntBeenAdded();
    }

    public void addCurrentListIfItHasntBeenAdded() {
        if(!allMembersItemList.contains(currentMembersItemlist)) {
            allMembersItemList.add(currentMembersItemlist);
        }
    }

    public void removeCurrentMembersItemlist() {
        this.currentMembersItemlist = null;
    }

    public boolean handleStringErrors(String input)  {
        List<String> categories = Arrays.asList("tool", "vehicle", "game", "toy", "sport", "other");
        boolean ifCategory = Iterables.any(categories, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String categories) {
                return categories.equalsIgnoreCase(input);
            }
        });
        return ifCategory;
    }

    public void handleCategoryErrors(String category) throws Exception {
        if(handleStringErrors(category) == false) {
            throw new Exception(" Wrong category.");
        }
    }

    public void handleCostError(Integer cost) throws Exception {
        if(cost < 10 || cost > 100) {
            throw new Exception("Faulty cost per day.");
        }
    }
}
