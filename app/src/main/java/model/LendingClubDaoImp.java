package model;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements persistance interface.
 */
public class LendingClubDaoImp implements LendingClubDao {
  List<Member> memberList;

  /**
   * Implementation.
   */
  public LendingClubDaoImp() {
    memberList = new ArrayList<Member>();
    Member member1 = new Member("1", "1", "1");
    Member member2 = new Member("2", "2", "2");
    memberList.add(member2);
    memberList.add(member1);
  }

  @Override
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Suppose to represent a future DB.")
  public List<Member> getAllMembers() {
    return memberList;
  }

  @Override
  public Member getMember(int index) {
    return memberList.get(index);
  }

  @Override
  public void updateMember(int memberIndex) {
    memberList.get(memberIndex).setId("123");
  }

  @Override
  public void deleteMember(Member m) {
    memberList.remove(m);
  }
  
}
