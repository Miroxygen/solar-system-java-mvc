package model;

public class Contract {
    protected int startDay;
    protected int endDay;
    public Item item;
    protected Member lender;

    public Contract(int startDay, int endDay, Item item, Member lender) {
        this.startDay = startDay;
        this.endDay = endDay;
        this.item = item;
        this.lender = lender;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public Item getItem() {
        return item;
    }

    public Member getLender() {
        return lender;
    }
}
