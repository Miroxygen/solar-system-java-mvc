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
  private Random random = new Random();
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
  public Member.MutableMember addMember(Member member) {
    member.setId(generateUniqueId());
    members.add(new Member.MutableMember(member));
    return members.get(members.size() - 1);
  }

  public int getNumberOfMembers() {
    return members.size();
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

  /**
   * Checks if the email is unique.
   *
   * @param email Members email.
   * @return Boolean.
   */
  public Boolean isEmailUnique(String email) {
    for (Member.MutableMember m : members) {
      if (m.getEmail().equals(email)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if the phone-number is unique.
   *
   * @param number Members phonenumber.
   * @return Boolean.
   */
  public Boolean isPhoneNumberUnique(String number) {
    for (Member.MutableMember m : members) {
      if (m.getPhoneNumber().equals(number)) {
        return false;
      }
    }
    return true;
  }

}
