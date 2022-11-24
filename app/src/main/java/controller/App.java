package controller;

import model.MemberList;
import view.TestItemView;
import view.TestMemberView;
import view.TestView;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    view.TestView view = new TestView();
    model.MemberList list = new MemberList();
    view.TestMemberView memberView = new TestMemberView();
    view.TestItemView itemView = new TestItemView();
    TestMember member = new TestMember(itemView);
    TestLc controller = new TestLc(view, list, memberView, member);
    controller.start();
  }
}
