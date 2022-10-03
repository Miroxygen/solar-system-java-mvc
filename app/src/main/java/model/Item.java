package model;

public class Item {
    protected String category;
    protected String name;
    protected String description;
    protected int dayOfCreation;
    protected int costPerDay;
    protected Boolean rented = false;

    public Item(String category, String name, String description, int dayOfCreation, int costPerDay) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.dayOfCreation = dayOfCreation;
        this.costPerDay = costPerDay;
    }

    public void setRented(Boolean ifRented) {
        this.rented = ifRented;
    }

    public Boolean getRented() {
        return this.rented;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDayOfCreation() {
        return this.dayOfCreation;
    }

    public int getCostPerday() {
        return this.costPerDay;
    }
}
