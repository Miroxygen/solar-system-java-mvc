package controller;
import java.util.ArrayList;

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
        setCurrentItemList(selectedMember);
        model.Item newItem = itemUI.createItem();
        selectedMember.addCredit(100);
        currentMembersItemlist.addItem(newItem);
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
}
