package controller;

import java.util.Scanner;

import model.MemberList;
import model.Time;
import view.EnglishItemView;
import view.EnglishMemberView;
import view.EnglishView;
import view.SwedishContractView;
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
    Scanner input = new Scanner(System.in, "utf-8");
    view.SwedishView view = new SwedishView();
    model.MemberList list = new MemberList();
    SwedishMemberView memberView = new SwedishMemberView();
    SwedishItemView itemView = new SwedishItemView(input);
    view.SwedishContractView contractView = new SwedishContractView(input);
    Member member = new Member(itemView);
    Time time = new Time();
    LendingClub controller = new LendingClub(view, contractView, list, memberView, member, time);
    controller.start();
  }
}
