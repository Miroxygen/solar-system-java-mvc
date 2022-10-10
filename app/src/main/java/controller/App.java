package controller;

import com.google.common.base.Optional;
import dao.ItemDao;
import dao.MemberDao;
import model.Member;
import model.MembersItemList;

/**
 * Responsible for staring the application.
 */
public class App {
  /**
   * Application starting point.

   * @param args command line arguments.
   */
  public static void main(String[] args) {
    MemberDao memberDao = new MemberDao();
    ItemDao itemDao = new ItemDao();
    Member albin = memberDao.get(0).get();
    albin.setItemList(new MembersItemList());
    model.Item axe = itemDao.get(0).get();
    albin.getItemList().addItem(axe);
    MainController controller = new MainController();
    controller.startMenu();
  }
}
