package model;

/**
 * Represents an Item.
 */
public class Item {
  private String category;
  private String name;
  private String description;
  private int dayOfCreation;
  private int costPerDay;
  private ContractList contractList = new ContractList();

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

  public ContractList getContractList() {
    return new ContractList(contractList);
  }

  /**
  * A mutable version of object Item.
  */
  public static class MutableItem extends Item {

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

