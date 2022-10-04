package model;

public class Time {
    protected int currentDay;

    public int getCurrentDay() {
        return currentDay;
    }

    public void advanceTime(int advancement) {
        this.currentDay += advancement;
    }
}
