package dao;

import com.google.common.base.Optional;
import java.util.List;

/**
 * Data access object pattern.
 */
public interface Dao<T> {
  Optional<T> get(long id);
    
  List<T> getAll();
  
  void save(T t);
  
  void update(T t, String[] params);
  
  void delete(T t);
}
