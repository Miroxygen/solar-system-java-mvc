package model;

import java.util.ArrayList;

public class Item {
    protected String category;
    protected String name;
    protected String description;
    protected int dayOfCreation;
    protected int costPerDay;
    protected Boolean rented = false;
    public Contract currentContract;
    protected ArrayList<Contract> futureContracts = new ArrayList<Contract>();
    protected ArrayList<Contract> oldContracts = new ArrayList<Contract>();

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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
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

    public void setCurrentContract(Contract currentContract) {
        this.currentContract = currentContract;
    }

    public Contract getCurrentContract() {
        return currentContract;
    }
    public Iterable<Contract> getFutureContracts() {
        return futureContracts;
    }

    public Iterable<Contract> getOldContracts() {
        return oldContracts;
    }

    public void addContract(Contract contract) {
        this.futureContracts.add(contract);
    }
}
