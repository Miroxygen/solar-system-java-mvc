package model;

/**
 * A copy for the sake of putting in another members lendeditems-list.
 * This item can't handle contracts, because it shouldn't.
 */
public class ItemCopy extends Item {

    public ItemCopy(String category, String name, String description, int dayOfCreation, int costPerDay) {
        super(category, name, description, dayOfCreation, costPerDay);
    }

    public ItemCopy(Item item) {
        super(item);
    }
}
