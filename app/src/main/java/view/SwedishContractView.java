package view;

import java.util.Scanner;

import model.Contract;

/**
 * Swedish version of contractview.
 */
public class SwedishContractView implements ContractView {
  private Scanner input;

  public SwedishContractView(Scanner input) {
    this.input = input;
  }

  /**
   * Creates and returns a contract.
   */
  public Contract createContract(int currentDay) throws Exception {
    try {
      int startDay = getStartDay();
      int endDay = getContractPeriod() + startDay;
      return new model.Contract(startDay, endDay);
    } catch (Exception e) {
      throw e;
    }
  }
  
  /**
   * Gets start day for a contract.
   */
  public int getStartDay() {
    System.out.println("Vilken dag vill du starta ditt kontrakt?");
    String startDay = input.nextLine();
    return Integer.parseInt(startDay);
  }

  /**
   * Gets contract lenght.
   */
  public int getContractPeriod() {
    System.out.println("Hur många dagar vill du låna prylen?");
    String contractLenght = input.nextLine();
    return Integer.parseInt(contractLenght);
  }
  
}
