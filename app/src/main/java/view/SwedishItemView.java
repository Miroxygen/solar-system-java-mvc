package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.C;

import model.Item;

/**
 * The Swedish itemview.
 */
public class SwedishItemView implements ItemView {
  private Scanner input;

  public SwedishItemView(Scanner input) {
    this.input = input;
  }
  
  /**
   * For creating an item.
   */
  public model.Item createItem(int currentDay) throws Exception {
    try {
      return new model.Item(chooseCategory(), chooseName(), chooseDescription(), currentDay, chooseCost());
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Choose category for item.
   *
   * @return String.
   */
  public String chooseCategory() throws Exception {
    try {
      System.out.println("=== Välj en kategori för din pryl : (Verktyg, Fordon, Spel, Leksak, Sport, eller Annat)");
      String category = input.nextLine();
      wrongCategory(category);
      return category;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Choose name for item.
   *
   * @return String.
   */
  public String chooseName() {
    System.out.println("=== Ge din pryl ett namn :");
    String name = input.nextLine();
    return name;
  }

  /**
   * Choose description for item.
   *
   * @return String.
   */
  public String chooseDescription() {
    System.out.println("=== Ge en kort beskrivning av din pryl : ");
    String description = input.nextLine();
    return description;
  }

  /**
   * Choose cost for item.
   *
   * @return Integer.
   */
  public int chooseCost() throws Exception {
    try {
      System.out.println("=== Vad kostar din pryl per dag? (10-100 daler)");
      String costPerDay = input.nextLine();
      int costPerDayInt = Integer.parseInt(costPerDay);
      wrongCost(costPerDayInt);
      return costPerDayInt;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * For selecting an item.
   */
  public <T extends Item> T selectItem(Iterable<T> list) {
    int index = 0;
    for (model.Item i : list) {
      System.out.println(index + " | Namn : " + i.getName());
      index++;
    }
    System.out.println("Ange siffran för rätt pryl");
    String key = input.nextLine();
    int intKey = Integer.parseInt(key);
    index = 0;
    for (T i : list) {
      if (index == intKey) {
        return i;
      }
      index++;
    }
    return null;
  }

  /** 
   * For editing an item.
  */
  public Edit editItem() {
    final String category = "1";
    final String name = "2";
    final String description = "3";
    final  String cost = "4";
    try {
      System.out.println(" Vad vill du ändra på? ");
      System.out.println(category + " Kategori | " + name + " Namn | " 
          + description + " Beskrvining | " + cost + " Kostnad |");
      String key = input.nextLine();
      if (key.equals(category)) {
        return view.ItemView.Edit.Category;
      } else if (key.equals(name)) {
        return view.ItemView.Edit.Name;
      } else if (key.equals(description)) {
        return view.ItemView.Edit.Description;
      } else if (key.equals(cost)) {
        return view.ItemView.Edit.Cost;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /** 
   * For getting a String value.
  */
  public String getNewValue() {
    System.out.println(" Skriv in ett nytt värde : ");
    String value = input.nextLine();
    return value;
  }

  /**
   * To detect a wrong category.
   */
  public void wrongCategory(String category) throws Exception {
    String[] categories = {"Verktyg", "verktyg", "Fordon", "fordon",
      "Spel", "spel", "Leksak", "leksak", "Sport", "sport", "Annat", "annat"};
    List<String> categoryList = Arrays.asList(categories);
    if (!categoryList.contains(category)) {
      throw new Exception("Fel kategori");
    }
  }

  /**
   * For detecting a wrong cost.
   */
  public void wrongCost(int cost) throws Exception {
    if (cost < 10 || cost > 100) {
      throw new Exception("Fel kostnad.");
    }
  }

  /**
   * Shows one item.
   */
  public void showOneItem(Item item, int currentDay) {
    System.out.println("Kategori : " + item.getCategory() + " Namn : " + item.getName() 
        + " Beskrivning : " + item.getDescription()
        + " Skapad dag : " + item.getDayOfCreation() + " Kostnad per dag : " + item.getCostPerday());
    for (model.Contract c : item.getContractList().getContracts()) {
      if (c.getStartDay() > currentDay) {
        System.out.println(" Framtida kontrakt :  Startdag : " + c.getStartDay() + " Slutdag : " 
            + c.getEndDay() + " Lånare : " + "c.getLender().getName()");
      } else if (c.getEndDay() < currentDay) {
        System.out.println(" Gamla kontrakt : Startdag : " + c.getStartDay() + " Slutdag : " 
            + c.getEndDay() + " Lender : " + "c.getLender().getName()");
      } else {
        System.out.println(" Nuvarande kontrakt : Startdag : " + c.getStartDay() + " Slutdag : "
            + c.getEndDay() + " Lånare : " + "c.getLender().getName()");
      }
    }
  }


  /**
   * For displaying a message.
   */
  public void displayMessage(String message) {
    System.out.print(message);
  }
  
}
