package controller;

import model.Contract;
import model.Item;
import model.Member;
import view.ContractView;

public class ContractController {
    view.ContractView conractUI = new ContractView();

    public Contract establishNewContract(Member lender, Item item) {
        int startDay = conractUI.getContractStartDate();
        int lengthOfTime = conractUI.getContractLength();
        Contract newContract = new Contract(startDay, startDay + lengthOfTime, item, lender);
        return newContract;
    }
}
