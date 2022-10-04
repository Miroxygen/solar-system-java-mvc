package controller;

import java.util.ArrayList;

import model.Contract;
import model.Item;
import model.Member;
import view.ContractView;

public class ContractController {
    view.ContractView conractUI = new ContractView();
    ArrayList <Contract> savedContracts = new ArrayList<Contract>();

    public Contract establishNewContract(Member lender, Item item) {
        int startDay = conractUI.getContractStartDate();
        int lengthOfTime = conractUI.getContractLength();
        Contract newContract = new Contract(startDay, startDay + lengthOfTime, item, lender);
        savedContracts.add(newContract);
        return newContract;
    }

    public void moveExpiredContract(model.Time time) {
        for(Contract c : savedContracts) {
            if(c.getEndDay() == time.getCurrentDay()) {
                c.getItem().moveExpiredContract();
                savedContracts.remove(c);
            }
        }
    }
}
