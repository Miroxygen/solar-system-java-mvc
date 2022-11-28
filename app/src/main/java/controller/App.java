package controller;

import model.MemberList;
import model.Time;
import view.ItemView;
import view.MemberView;
import view.View;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    view.View view = new View();
    model.MemberList list = new MemberList();
    view.MemberView memberView = new MemberView();
    view.ItemView itemView = new ItemView();
    Member member = new Member(itemView);
    Time time = new Time();
    LendingClub controller = new LendingClub(view, list, memberView, member, time);
    controller.start();
  }
}
