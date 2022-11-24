package view;

/**
 * View for lists.
 */
public class TestList extends TestView {

  public <T extends model.Member> void listMembersSimple(Iterable<T> list) {
    for(model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail() + " Credits : " + m.getCredit() + " Number of items : " + m.getNumberOfItems());
    }
  }

  public <T extends model.Member> void listMembersVerbose(Iterable<T> list) {
    for(model.Member m : list) {
      System.out.println(" Name : " + m.getName() + " Email : " + m.getEmail());
      listMembersItems(m.getItems());
    }
  }

  private <T extends model.Item> void listMembersItems(Iterable<T> list) {
    for (model.Item i : list) {
      System.out.println(" Name : " + i.getName() + " Category : " + i.getCategory() + " Description : " + i.getDescription() + " Cost per day : " + i.getCostPerday());
    }
  }
}
