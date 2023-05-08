package view;

import java.util.Scanner;

/**
 * Swedish version of contractview.
 */
public class SwedishContractView implements ContractView {
  private Scanner input = new Scanner(System.in, "utf-8");

  /**
   * Creates and returns a contract.
   */
  public model.Contract createContract(int currentDay) throws Exception {
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
