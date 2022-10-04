package view;

import java.util.Scanner;

public class ContractView {
    private Scanner userInput = new Scanner(System.in, "utf-8");

    public int getContractStartDate() {
        System.out.println("=== On which day do you want to start your lending period?");
        int startDay = userInput.nextInt();
        userInput.nextLine();
        return startDay;
    }

    public int getContractLength() {
        System.out.println("=== And for how many days do you want to lend it?");
        int lenghtOfTime = userInput.nextInt();
        userInput.nextLine();
        return lenghtOfTime;
    }
}
