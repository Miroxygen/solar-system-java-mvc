package model;

import java.util.ArrayList;

/**
 * An item you can change and add contracts to.
 */
public class MutableItem extends Item {
    protected Boolean isRented = false;
    public Contract currentContract;
    protected ArrayList<Contract> futureContracts = new ArrayList<Contract>();
    protected ArrayList<Contract> oldContracts = new ArrayList<Contract>();

    public MutableItem(String category, String name, String description, int dayOfCreation, int costPerDay) {
        super(category, name, description, dayOfCreation, costPerDay);
    }

    public MutableItem(Item item) {
        super(item);
    }

    public Boolean getRented() {
        return this.isRented;
    }
    
    public void setAsRented() {
        this.isRented = true;
    }

    public void setAsNotRented() {
        this.isRented = false;
    }

    public void setCurrentContract(Contract currentContract) {
        this.currentContract = currentContract;
    }

    public void removeCurrentContract() {
        this.currentContract = null;
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

    public void addToFutureContracts(Contract contract) {
        this.futureContracts.add(contract);
    }

    public void addToOldContracts(Contract contract) {
        this.oldContracts.add(contract);
    }
}
