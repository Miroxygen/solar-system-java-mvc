package view;

/**
* Interface for view.
*/
public interface View {
  /**
   * Choices for start.
   */
  public static enum Start {
    Login,
    Create,
    Quit
  }

  /**
   * Choices for menu.
   */
  public static enum Menu {
    Profile,
    List,
    Contract,
    Time,
    Logout
  }

  /**
   * Choices for list.
   */
  public static enum ListChoice {
    Simple,
    Verbose
  }
  
  /**
   * Choices for delete.
   */
  public static enum Delete {
    Yes,
    No
  }

  /**
  * Login menu.
  *
  * @return An enum.
  */
  public Start login();

  /**
  * The manin menu.
  *
  * @param currentDay Systems current day.
  * @return An enum.
  */
  public Menu menu(int currentDay);

  /**
   * Menu to display lists. 
   *
   * @return An enum.
   */
  public ListChoice list();

  /**
   * Select a member from a list. 
   *
   * @param <T> Member.
   * @param list List of member.
   * @return Member.
   */
  public <T extends model.Member> T selectMember(Iterable<T> list);

  /**
   * Select an item from a list.
   *
   * @param <T> Item
   * @param list List of items.
   * @return Item.
   */
  public <T extends model.Item> T selectItem(Iterable<T> list);

  /**
   * List members in a simple way.
   *
   * @param <T> Member.
   * @param list List of members.
   */
  public <T extends model.Member> void listMembersSimple(Iterable<T> list);

  /**
   * Lists members and their items in a verbose way.
   *
   * @param <T> Memeber.
   * @param list List of members.
   * @param currentDay Current day in the system.
   */
  public <T extends model.Member> void listMembersVerbose(Iterable<T> list, int currentDay);

  /**
   * List members items and if they have current contracts.
   *
   * @param <T> Item.
   * @param list List of item.
   * @param currentDay Systems current day.
   */
  public <T extends model.Item> void listMembersItems(Iterable<T> list, int currentDay);

  /**
   * Displays any string put in.
   *
   * @param message String message.
   */
  public void displayMessage(String message);

  /**
   * Select an item from the available items.
   *
   * @param <T> An item object.
   * @param list Iterable item list.
   * @return An item.
   */
  public <T extends model.Item> model.Item selectFromAllAvailableItems(Iterable<T> list);

  /**
   * Get a startday for the contract. 
   *
   * @return Integer of startday.
   */
  public int getContractStartDay();

  /**
   * Gets how many days to lend the item.
   *
   * @return Integer of days.
   */
  public int getContractPeriod();

  /**
   * To increase daytime counter.
   *
   * @param currentDay To show the current day,
   * @return Integer of days advancement.
   */
  public int time(int currentDay);

  /**
   * Display the current day.
   *
   * @param currentDay An integer.
   */
  public void displayCurrentDay(int currentDay);

  /**
   * View for deleting a member. 
   *
   * @return An enum.
   */
  public Delete deleteMember();


}
