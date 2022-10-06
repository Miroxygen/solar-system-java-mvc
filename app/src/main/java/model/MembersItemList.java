package model;

import java.util.ArrayList;

public class MembersItemList {
    private Member owner = null;
    private ArrayList<Item.MutableItem> items = new ArrayList<Item.MutableItem>();

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }

    public Item addItem(Item item) {
        items.add(new Item.MutableItem(item));
        return items.get(items.size() - 1);
    }

    public Iterable<Item.MutableItem> getItems() {
        return items;
    }

    public int getNumberOfItems() {
        return items.size();
    }

    public void deleteItem(Item.MutableItem selectedItem) {
       items.remove(selectedItem);
    }
}
