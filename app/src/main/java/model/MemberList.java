package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a list of object Member.
 */
public class MemberList {
  private ArrayList<Member.MutableMember> members = new ArrayList<Member.MutableMember>();
  private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  Random random = new Random();
  private StringBuilder builder = new StringBuilder();


  /**
  * For iterating members.
  *
  * @return Objectlist.
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Member.MutableMember> getMembers() {
    return members;
  }

  /**
  * For adding members.
  *
  * @param member Object.
  * @return Object.
  */
  public Member addMember(Member member) {
    member.setId(generateUniqueId());
    member.addCredit(0);
    members.add(new Member.MutableMember(member));
    return members.get(members.size() - 1);
  }

  /**
  * For deleting a member.
  *
  * @param member Object.
  */
  public void deleteMember(model.Member member) {
    members.remove(member);
  }

  /**
  * Creates a new id with Stringbuilder.
  *
  *@return String id.
  */
  public String createAlphaNumericId() {
    while (builder.length() < 6) {
      int randomCharIndex = (int) (random.nextFloat() * chars.length());
      builder.append(chars.charAt(randomCharIndex));
    }
    String id = builder.toString();
    builder.delete(0, 5);
    return id;
  }

  /**
   * Generates a new ID while the created ID is not unique.
   *
   *@return String id.
   */
  public String generateUniqueId() {
    ArrayList<String> memberId = new ArrayList<String>();
    for (Member m : members) {
      memberId.add(m.getId());
    }
    String id = createAlphaNumericId();
    do {
      id = createAlphaNumericId();
    } while (memberId.contains(id));
    return id;
  }


}
