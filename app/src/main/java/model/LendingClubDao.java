package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;

/**
 * Start of persistence interface.
 */
public interface LendingClubDao {

  /**
   * Get all members.
   *
   * @return Member.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppose to represent a future DB.")
  List<Member> getAllMembers();

  /**
   * Get one member.
   *
   * @return Member
   */
  public Member getMember(int index);

  /**
   * Updates a member.
   *
   * @param memberIndex Member.
   */
  public void updateMember(int memberIndex);

  /**
   * Deletes a member.
   *
   * @param m Member.
   */
  public void deleteMember(Member m);
}
