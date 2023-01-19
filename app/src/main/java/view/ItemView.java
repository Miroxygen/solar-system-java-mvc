package view;

public interface ItemView {

  /**
   * Menu for editing.
   */
  public static enum Edit {
    Category,
    Name,
    Description,
    Cost
  }

  /**
   * Creates an item.
   *
   * @param currentDay Current day in system.
   * @return Item,
   * @throws Exception If input is false.
   */
  public model.Item createItem(int currentDay) throws Exception;

  /**
   * Selects an item from list.
   *
   * @param <T> Item.
   * @param list List of items.
   * @return Item.
   */
  public <T extends model.Item> T selectItem(Iterable<T> list);

  /**
   * For editing an item. 
   *
   * @return Enum.
   */
  public Edit editItem();

  /**
   * Gets a new value for an item. 
   *
   * @return New value string.
   */
  public String getNewValue();

  /**
   * If the item has wrong category. 
   *
   * @param category Item category.
   * @throws Exception If wrong category.
   */
  public void wrongCategory(String category) throws Exception;

  /**
   * Checks if item has wrong cost. 
   *
   * @param cost Item cost.
   * @throws Exception If wrong cost.
   */
  public void wrongCost(int cost) throws Exception;

  /**
   * Displays one item with its contracts.
   *
   * @param item Item to show.
   * @param currentDay Current day in system.
   */
  public void showOneItem(model.Item item, int currentDay);

  /**
   * Displays a messgae.
   *
   * @param message String.
   */
  public void displayMessage(String message);
}
