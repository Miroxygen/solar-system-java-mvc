package dao;

import com.google.common.base.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Item;

/**
 * DAO pattern model.
 */
public class ItemDao implements Dao<model.Item> {
  private List<model.Item> items = new ArrayList<>();


  public ItemDao() {
    items.add(new Item("Tool", "Axe", "To chop branches.", 0, 25));
    items.add(new Item("Games", "PlayStation5", "Top of the line.", 0, 100));
  }

  @Override
  public Optional<Item> get(long id) {
    return Optional.fromNullable(items.get((int) id));
  }

  @Override
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning a mutable.")
  public List<Item> getAll() {
    return items;
  }

  @Override
  public void save(Item t) {
    items.add(t);
  }

  @Override
  public void update(Item t, String[] params) {
    t.setName(Objects.requireNonNull(
            params[0], "Name cannot be null"));
    t.setCategory(Objects.requireNonNull(
            params[1], "Category cannot be null"));
    t.setDescription(Objects.requireNonNull(
            params[2], "Description cannot be null"));  
    items.add(t);
  }

  @Override
  public void delete(Item t) {
    items.remove(t);
  }
  
}
