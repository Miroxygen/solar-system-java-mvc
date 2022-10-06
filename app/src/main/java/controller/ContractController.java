package controller;

import java.util.ArrayList;

import model.Contract;
import model.Item;
import model.Member;
import view.ContractView;

public class ContractController {
    view.ContractView conractUI = new ContractView();
    ArrayList <Contract> savedContracts = new ArrayList<Contract>();

    public Contract getNewContract(Member lender, Item item, int startDate, int endDate) {
        Contract newContract = new Contract(startDate, endDate, item, lender);
        savedContracts.add(newContract);
        return newContract;
    }

    public int getStartDate() {
        int startDate = conractUI.getContractStartDate();
        return startDate;
    }

    public int getContractLength() {
        int length = conractUI.getContractLength();
        return length;
    }

    public Iterable<Contract> getRunningContracts() {
        return savedContracts;
    }

    public void removeExpiredContract(Contract contract) {
        savedContracts.remove(contract);
    }
}
