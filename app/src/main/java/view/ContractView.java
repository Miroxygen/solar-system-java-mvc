package view;

import java.util.Scanner;

/**
 * Represents contract view.
 */
public class ContractView {
  private Scanner userInput = new Scanner(System.in, "utf-8");

  /**
   * Getting contract start date.
   *
   * @return int.
   */
  public int getContractStartDate() {
    System.out.println("=== On which day do you want to start your lending period?");
    int startDay = userInput.nextInt();
    userInput.nextLine();
    return startDay;
  }

  /**
   * For getting length.
   *
   * @return int.
   */
  public int getContractLength() {
    System.out.println("=== And for how many days do you want to lend it?");
    int lenghtOfTime = userInput.nextInt();
    userInput.nextLine();
    return lenghtOfTime;
  }
}
