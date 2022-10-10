package dao;

import com.google.common.base.Optional;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Member;

/**
 * DAO pattern model.
 */
public class MemberDao implements Dao<model.Member> {
  private List<model.Member> members = new ArrayList<>();

  public MemberDao() {
    members.add(new Member("Albin Hansson", "073456789", "albin@mejl.se"));
    members.add(new Member("Magnus Ladulås", "123", "tidigare@post.se"));
  }

  @Override
  public Optional<Member> get(long id) {
    return Optional.fromNullable(members.get((int) id));
  }

  @Override
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public List<Member> getAll() {
    return members;
  }

  @Override
  public void save(Member t) {
    members.add(t);
  }

  @Override
  public void update(Member t, String[] params) {
    t.setName(Objects.requireNonNull(
            params[0], "Name cannot be null"));
    t.setEmail(Objects.requireNonNull(
            params[1], "Email cannot be null"));
    members.add(t);
  }

  @Override
  public void delete(Member t) {
    members.remove(t);
  }
}
