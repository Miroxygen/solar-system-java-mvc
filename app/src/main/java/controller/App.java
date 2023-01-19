package controller;

import model.MemberList;
import model.Time;
import view.EnglishItemView;
import view.EnglishMemberView;
import view.EnglishView;
import view.SwedishItemView;
import view.SwedishMemberView;
import view.SwedishView;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    view.SwedishView view = new SwedishView();
    model.MemberList list = new MemberList();
    SwedishMemberView memberView = new SwedishMemberView();
    SwedishItemView itemView = new SwedishItemView();
    Member member = new Member(itemView);
    Time time = new Time();
    LendingClub controller = new LendingClub(view, list, memberView, member, time);
    controller.start();
  }
}
