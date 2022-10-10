package controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Member;
import view.ContractView;

/**
 * Represents a view for controller.
 */
public class ContractController {
  view.ContractView conractUi = new ContractView();
  ArrayList<Contract> savedContracts = new ArrayList<Contract>();

  /**
  * Creates a new contract.
  *
  * @param lender Object.
  * @param item Object.
  * @param startDate Int.
  * @param endDate Int.
  * @return New object Contract.
  * 
  */
  public Contract getNewContract(Member.MutableMember lender, Item.MutableItem item, int startDate, int endDate) {
    Contract newContract = new Contract(startDate, endDate, item, lender);
    savedContracts.add(newContract);
    return newContract;
  }

  /**
  * Contracts start date.
  *
  * @return Int.
  */
  public int getStartDate() {
    int startDate = conractUi.getContractStartDate();
    return startDate;
  }

  /**
  * Length of contract.
  *
  * @return Int.
  * @throws Exception Minusdays of lending.
  */
  public int getContractLength() throws Exception {
    int length = conractUi.getContractLength();
    if (length < 0) {
      throw new Exception("Can't lend negative days");
    }
    return length;
  }

  /**
  * Get iterable of ArrayList.
  *
  * @return Iterable list.
  */
  @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Returning an abstraction.")
  public Iterable<Contract> getRunningContracts() {
    return savedContracts;
  }

  /**
  * Expired means endday is over.
  *
  * @param contract Object contract.
  * 
  */
  public void removeExpiredContract(Contract contract) {
    savedContracts.remove(contract);
  }
}
