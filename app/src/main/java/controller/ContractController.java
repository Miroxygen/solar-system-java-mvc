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
        Contract newContract = new Contract(getStartDate(), getEndDate(), item, lender);
        savedContracts.add(newContract);
        return newContract;
    }

    public int getStartDate() {
        int startDate = conractUI.getContractStartDate();
        return startDate;
    }

    public int getEndDate() {
        int endDate = getStartDate() + conractUI.getContractLength();
        return endDate;
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
