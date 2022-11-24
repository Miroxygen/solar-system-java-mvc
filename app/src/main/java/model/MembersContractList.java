package model;

import java.util.ArrayList;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class MembersContractList {
  private Member.MutableMember owner = null;
  private ArrayList<Contract.MutableContract> contracts = new ArrayList<Contract.MutableContract>();

  /**
   * Object owner.
   *
   * @param owner Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "MutableMember is a mutable copy.")
  public void setOwner(Member.MutableMember owner) {
    this.owner = owner;
  }

  /**
   * Get owner-object.
   *
   * @return Object.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an mutable.")
  public Member.MutableMember getOwner() {
    return owner;
  }

  public void addContract(Contract.MutableContract c) {
    contracts.add(c);
  }

  public void deleteContract(Contract.MutableContract c) {
    contracts.remove(c);
  }

   /**
   * Returns iterable of contracts.
   *
   * @return Iterable.
   */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Contract.MutableContract> getContracts() {
    return contracts;
  }
}
