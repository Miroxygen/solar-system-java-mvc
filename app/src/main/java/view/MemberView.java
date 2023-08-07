package view;

/**
 * Interface for memberview.
 */
public interface MemberView {
  /**
   * Choices for profile.
   */
  public static enum Profile {
    Details,
    Edit,
    Item
  }

  /**
   * Choices for edit.
   */
  public static enum Edit {
    Name,
    Email,
    Phone,
    Delete
  }

  /**
   * Choices for item.
   */
  public static enum Item {
    Add,
    Delete,
    Edit,
    Inspect
  }

  public Profile profile();

  public Edit editMember();

  public Item itemMenu();

  public String getNewStringValue();

  public model.Member createMember();

  public <T extends model.Member> T selectMember(Iterable<T> list);

  public <T extends model.Member> void viewMember(model.Member m);

}
