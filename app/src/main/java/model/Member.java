package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a member.
 */
public class Member {
  protected String name;
  protected String email;
  protected String phoneNumber;
  private String id;
  private int dayOfCreation;
  protected int credit;
  protected MembersItemList itemList;


  /**
   * Constructor.
   *
   * @param name String.
   * @param phoneNumber String.
   * @param email String.
   */
  public Member(String name, String phoneNumber, String email) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  /**
   * Constructor for child.
   *
   * @param m Object.
   */
  public Member(Member m) {
    this.name = m.name;    
    this.phoneNumber = m.phoneNumber;
    this.email = m.email;
    this.id = m.id;
  }

  /**
   * Sets day of creation.
   *
   * @param dayOfCreation Int.
   */
  public void setDayOfCreation(int dayOfCreation) {
    this.dayOfCreation = dayOfCreation;
  }

  /**
   * Sets id.
   *
   * @param id String.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return String.
   */
  public String getName() {
    return name;
  }

  /**
   * Get phonenumber.
   *
   * @return String.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }


  /**
   * Get field.
   *
   * @return String.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get field.
   *
   * @return String.
   */
  public String getId() {
    return id;
  }

  /**
   * Get field.
   *
   * @return Int.
   */
  public int getDayOfCreation() {
    return dayOfCreation;
  }

  /**
   * Get field.
   *
   * @return Int.
   */
  public int getCredit() {
    return credit;
  }

  public int getNumberOfItems() {
    return itemList.getNumberOfItems();
  }

  public Iterable<model.Item.MutableItem> getItems() {
    return itemList.getItems();
  }

  /**
   * Return object.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public MembersItemList getItemList() {
    return itemList;
  }

  /**
  * Represents a member you can change.
  */
  public static class MutableMember extends Member {
  private MembersContractList contractList;
  

    /**
     * Constructor.
     *
     * @param name Inherited.
     * @param phoneNumber Inherited.
     * @param email Inherited.
     */
    public MutableMember(String name, String phoneNumber, String email) {
      super(name, phoneNumber, email);
    }

    /**
     * Use this constructor.
     *
     * @param m Parent Member.
     */
    public MutableMember(Member m) {
      super(m);
      contractList = new MembersContractList();
      contractList.setOwner(this);
    }

      /**
     * Sets name.
     *
     * @param name String.
     */
    public void setName(String name) {
      this.name = name;
    }

      /**
     * Set field.
     *
     * @param email String.
     */
    public void setEmail(String email) {
      this.email = email;
    }

      /**
     * Set field.
     *
     * @param phoneNumber String.
     */
    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }


  /**
   * Adding more credits.
   *
   * @param credit Int.
   */
  public void addCredit(int credit) {
    this.credit += credit;
  }

  /**
   * Removing credit.
   *
   * @param credit Int.
   */
  public void withdrawCredit(int credit) {
    this.credit -= credit;
  }

  /**
   * Setting list.
   *
   * @param itemList Object.
   */
  public void setItemList(MembersItemList itemList) {
    this.itemList = itemList;
  }

  public MembersContractList getContractList() {
    return contractList;
  }
  }
}
