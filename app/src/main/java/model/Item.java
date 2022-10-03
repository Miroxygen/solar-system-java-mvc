package model;

public class Item {
    protected String category;
    protected String name;
    protected String description;
    protected int dayOfCreation;
    protected int costPerDay;

    public Item(String category, String name, String description, int dayOfCreation, int costPerDay) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.dayOfCreation = dayOfCreation;
        this.costPerDay = costPerDay;
    }
}
