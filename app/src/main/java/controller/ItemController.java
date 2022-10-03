package controller;
import java.util.ArrayList;

import model.Member;
import model.MembersItemList;
import view.ItemView;
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
            default:
                break;
        }
    }



    public void addItem(Member selectedMember) {
        if(currentMembersItemlist == null) {
            if(getMembersItemList(selectedMember) == (null)) {
                createNewMembersItemList(selectedMember);
            }
            currentMembersItemlist = getMembersItemList(selectedMember);
        }
        model.Item newItem = itemUI.createItem();
        currentMembersItemlist.addItem(newItem);
    }

    public MembersItemList getMembersItemList(Member selectedMember) {
        for(MembersItemList mil : allMembersItemList) {
            if(mil.getOwner() == selectedMember) {
                return mil;
            }    
        }
        return null;
    }

    public void createNewMembersItemList(Member selectedMember) {
        MembersItemList newMembersItemList = new MembersItemList();
        allMembersItemList.add(newMembersItemList);
        newMembersItemList.setOwner(selectedMember);
    }
}
