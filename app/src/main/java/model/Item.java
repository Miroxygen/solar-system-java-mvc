package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;

/**
 * Represents an Item.
 */
public class Item {
  protected String category;
  protected String name;
  protected String description;
  protected int dayOfCreation;
  protected int costPerDay;
  protected Boolean isRented = false;
  protected Contract currentContract;
  protected ArrayList<Contract> futureContracts = new ArrayList<Contract>();
  protected ArrayList<Contract> oldContracts = new ArrayList<Contract>();
  protected Member.MutableMember owner;

  /**
   * Constructor.
   *
   * @param category String.
   * @param name String.
   * @param description String.
   * @param dayOfCreation Int.
   * @param costPerDay Int.
   */
  public Item(String category, String name, String description, int dayOfCreation, int costPerDay) {
    this.category = category;
    this.name = name;
    this.description = description;
    this.dayOfCreation = dayOfCreation;
    this.costPerDay = costPerDay;
  }

  /**
   * For child object.
   *
   * @param item Object.
   */
  public Item(Item item) {
    this.category = item.category;
    this.name = item.name;
    this.description = item.description;
    this.dayOfCreation = item.dayOfCreation;
    this.costPerDay = item.costPerDay;
  }

  /**
   * Itemowner.
   *
   * @param owner Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "MutableMember is a mutable copy.")
  public void setOwner(Member.MutableMember owner) {
    this.owner = owner;
  }

  /**
   * Itemowner.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Member.MutableMember getOwner() {
    return owner;
  }

  /**
   * Item is unavailable.
   */
  public void setAsRented() {
    this.isRented = true;
  }

  /**
   * Item is available.
   */
  public void setAsNotRented() {
    this.isRented = false;
  }

  /**
   * Itemcategory.
   *
   * @param category Field.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Itemname.
   *
   * @param name Field.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Itemdescrition.
   *
   * @param description Field.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Itemcostperday.
   *
   * @param costPerDay Field.
   */
  public void setCostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Items availablity.
   *
   * @return Boolean.
   */
  public Boolean getRented() {
    return this.isRented;
  }

  /**
   * Items category.
   *
   * @return String.
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Items name.
   *
   * @return String.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Items description.
   *
   * @return String.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Items dayofcreation.
   *
   * @return Int.
   */
  public int getDayOfCreation() {
    return this.dayOfCreation;
  }

  /**
   * Items costperday.
   *
   * @return Int.
   */
  public int getCostPerday() {
    return this.costPerDay;
  }

  /**
   * If item is contracted.
   *
   * @param currentContract Object. 
   */
  public void setCurrentContract(Contract currentContract) {
    this.currentContract = currentContract;
  }

  /**
   * For voiding contracts.
   */
  public void removeCurrentContract() {
    this.currentContract = null;
  }

  /**
   * For voiding contracts.
   *
   * @param contract Object.
   */
  public void moveExpiredContract(Contract contract) {
    this.futureContracts.remove(contract);
    this.oldContracts.add(contract);
    removeCurrentContract();
    setAsNotRented();
  }

  /**
   * If contracted.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Contract getCurrentContract() {
    return currentContract;
  }

  /**
   * To make it possible to lend for the future.
   *
   * @return List.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Contract> getFutureContracts() {
    return futureContracts;
  }

  /**
   * To show old conracts.
   *
   * @return List.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Contract> getOldContracts() {
    return oldContracts;
  }

  /**
   * Storing for future.
   *
   * @param contract Object.
   */
  public void addToFutureContracts(Contract contract) {
    this.futureContracts.add(contract);
  }

  /**
   * To set as current.
   *
   * @param contract Object.
   */
  public void removeFromFutureContracts(Contract contract) {
    this.futureContracts.remove(contract);
  }

  /**
   * To avoid duplication, checks first.
   *
   * @param contract Object.
   */
  public void addToOldContracts(Contract contract) {
    if (!this.oldContracts.contains(contract) || contract != null) {
      this.oldContracts.add(contract);
    }   
  }

  /**
  * An item you can change.
  */
  public static class MutableItem extends Item {

    /**
     * Constructor.
     *
     * @param category String.
     * @param name String.
     * @param description String.
     * @param dayOfCreation Int.
     * @param costPerDay Int.
     */
    public MutableItem(String category, String name, String description, int dayOfCreation, int costPerDay) {
      super(category, name, description, dayOfCreation, costPerDay);
    }

    /**
    * For making a mutable version.
    *
    * @param item Parent object.
    */
    public MutableItem(Item item) {
      super(item);
    }
  }
}

