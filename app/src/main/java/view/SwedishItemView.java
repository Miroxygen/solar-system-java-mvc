package view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Item;

public class SwedishItemView implements ItemView {
  private Scanner input = new Scanner(System.in, "utf-8");
  private final String category = "1";
  private final String name = "2";
  private final String description = "3";
  private final  String cost = "4";
  
  @SuppressFBWarnings(value = "VARIABLE_DECLARATION_DISTANCE", justification = "Value wont be altered.")
  public model.Item createItem(int currentDay) throws Exception {
    try {
      System.out.println("=== Välj en kategori för din pryl : (Verktyg, Fordon, Spel, Leksak, Sport, eller Annat)");
      String category = input.nextLine();
      wrongCategory(category);
      System.out.println("=== Ge din pryl ett namn :");
      String name = input.nextLine();
      System.out.println("=== Ge en kort beskrivning av din pryl : ");
      String description = input.nextLine();
      System.out.println("=== Vad kostar din pryl per dag? (10-100 daler)");
      int costPerDay = input.nextInt();
      wrongCost(costPerDay);
      input.nextLine();
      return new model.Item(category, name, description, currentDay, costPerDay);
    } catch (Exception e) {
      throw e;
    }
  }

  
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

  
  public Edit editItem() {
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

  
  public String getNewValue() {
    System.out.println(" Skriv in ett nytt värde : ");
    String value = input.nextLine();
    return value;
  }

  
  public void wrongCategory(String category) throws Exception {
    String[] categories = {"Verktyg", "verktyg", "Fordon", "fordon",
      "Spel", "spel", "Leksak", "leksak", "Sport", "sport", "Annat", "annat"};
    List<String> categoryList = Arrays.asList(categories);
    if (!categoryList.contains(category)) {
      throw new Exception("Fel kategori");
    }
  }

  
  public void wrongCost(int cost) throws Exception {
    if (cost < 10 || cost > 100) {
      throw new Exception("Fel kostnad.");
    }
  }

  
  public void showOneItem(Item item, int currentDay) {
    System.out.println("Kategori : " + item.getCategory() + " Namn : " + item.getName() 
        + " Beskrivning : " + item.getDescription()
        + " Skapad dag : " + item.getDayOfCreation() + " Kostnad per dag : " + item.getCostPerday());
    for (model.Contract c : item.getContractList().getContracts()) {
      if (c.getStartDay() > currentDay) {
        System.out.println(" Framtida kontrakt :  Startdag : " + c.getStartDay() + " Slutdag : " 
            + c.getEndDay() + " Lånare : " + c.getLender().getName());
      } else if (c.getEndDay() < currentDay) {
        System.out.println(" Gamla kontrakt : Startdag : " + c.getStartDay() + " Slutdag : " 
            + c.getEndDay() + " Lender : " + c.getLender().getName());
      } else {
        System.out.println(" Nuvarande kontrakt : Startdag : " + c.getStartDay() + " Slutdag : "
            + c.getEndDay() + " Lånare : " + c.getLender().getName());
      }
    }
  }


  public void displayMessage(String message) {
    System.out.print(message);
  }
  
}
