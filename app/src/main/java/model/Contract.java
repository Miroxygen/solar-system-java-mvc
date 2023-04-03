package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a contract.
 */
public class Contract {
  private int startDay;
  private int endDay;
  private Member lender;
  private Item item;

  /**
   * Constructor.
   *
   * @param startDay Int.
   * @param endDay Int.
   * @param item Object.
   * @param lender Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Contract(int startDay, int endDay) {
    this.startDay = startDay;
    this.endDay = endDay;
  }

  /**
   * For child object.
   *
   * @param c Contract.
   */
  public Contract(Contract c) {
    this.startDay = c.startDay;
    this.endDay = c.endDay;
    this.lender = new Member(c.lender);
    this.item = new Item(c.item);
  }

/**
 * Sets the lender of the contract to a copy of the Member object.
 *
 * @param lender The new lender for the contract.
 */
  public void setLender(Member lender) {
    this.lender = new Member(lender);
  }

  /**
   * Returns the lender of the contract.
   *
   * @return The lender.
   */
  public Member getLender() {
    return new Member(lender);
  }

  /**
 * Sets the item of the contract to a copy of the Item object.
 *
 * @param item The new item for the contract.
 */
 public void setItem(Item item) {
   this.item = new Item(item);
 }

 /**
  * Returns the item of the contract.
  *
  * @return The item.
  */
 public Item getItem() {
  return new Item(item);
 }

  /**
   * Contracts startday.
   *
   * @return Int.
   */
  public int getStartDay() {
    return startDay;
  }

  /**
   * Contracts end day.
   *
   * @return Int.
   */
  public int getEndDay() {
    return endDay;
  }

  /**
   * Contracts length.
   *
   * @return Int.
   */
  public int getLength() {
    return getEndDay() - getStartDay();
  }

  /**
   * Mutable Contract.
   */
  public static class MutableContract extends Contract {

    /**
     * For creating a copy.
     *
     * @param c Contract.
     */
    public MutableContract(Contract c) {
      super(c);
    }
  }

}
