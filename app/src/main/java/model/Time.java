package model;

/**
 * Respresents a daycounter.
 */
public class Time {
  private int currentDay;

  /**
   * Returns current int for counter.
   *
   * @return Int.
   */
  public int getCurrentDay() {
    return currentDay;
  }

  /**
   * Increases counter.
   *
   * @param advancement Int.
   */
  public void advanceTime(int advancement) {
    this.currentDay += advancement;
  }
}
