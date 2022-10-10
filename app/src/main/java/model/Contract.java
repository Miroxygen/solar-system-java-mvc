package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a contract.
 */
public class Contract {
  protected int startDay;
  protected int endDay;
  public Item.MutableItem item;
  protected Member.MutableMember lender;

  /**
   * Constructor.
   *
   * @param startDay Int.
   * @param endDay Int.
   * @param item Object.
   * @param lender Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Contract(int startDay, int endDay, Item.MutableItem item, Member.MutableMember lender) {
    this.startDay = startDay;
    this.endDay = endDay;
    this.item = item;
    this.lender = lender;
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
   * Contracted item.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Item.MutableItem getItem() {
    return item;
  }

  /**
   * Contractor.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public Member.MutableMember getLender() {
    return lender;
  }

  /**
   * Contracts length.
   *
   * @return Int.
   */
  public int getLength() {
    return getEndDay() - getStartDay();
  }

}
