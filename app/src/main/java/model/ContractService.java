package model;

public class ContractService {
  private ContractList cL;


  public ContractService(ContractList cL) {
    this.cL = cL;
  }

  public void addContract(Contract c) {
    cL.addContract(new Contract.MutableContract(c));
  }
}
