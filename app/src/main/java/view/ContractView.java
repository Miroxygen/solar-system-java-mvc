package view;

/**
 * Interface for contractview.
 */
public interface ContractView {
  
  /**
   * Creates a contract.
   *
   * @param currentDay Current day in systen,
   * @return Contract.
   * @throws Exception If contract cant be created.
   */
  public model.Contract createContract(int currentDay) throws Exception;

  /**
   * Gives a contract startday.
   *
   * @return Integer.
   */
  public int getStartDay();

  /**
   * Gets lenght of contract.
   *
   * @return Integer.
   */
  public int getContractPeriod();
}
