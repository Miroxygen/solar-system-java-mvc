package model;

import java.util.ArrayList;

public class MembersItemList {
    private Member owner = null;
    private ArrayList<Item> items = new ArrayList<Item>();

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getOwner() {
        return owner;
    }

    public Item addItem(Item item) {
        items.add(item);
        return items.get(items.size() - 1);
    }

    public Iterable<Item> getItems() {
        return items;
    }
}
