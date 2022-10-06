package model;

import java.util.ArrayList;

public class MembersItemList {
    private Member owner = null;
    private ArrayList<MutableItem> items = new ArrayList<MutableItem>();

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }

    public Item addItem(Item item) {
        items.add(new MutableItem(item));
        return items.get(items.size() - 1);
    }

    public Iterable<MutableItem> getItems() {
        return items;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public void deleteItem(Item selectedItem) {
       items.remove(selectedItem);
    }
}
