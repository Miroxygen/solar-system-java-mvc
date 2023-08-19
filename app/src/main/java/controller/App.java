package controller;

import java.util.ArrayList;
import java.util.List;
import model.SolarSystem;
import view.SolarSystemView;

/**
 * App starting point.
 */
public class App {
  
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    try {
      SolarSystemView view = new SolarSystemView();
      List<SolarSystem.MutableSolarSystem> solarSystems = new ArrayList<>();
      SolarSystemCreator creator = new SolarSystemCreator();
      SolarSystem.MutableSolarSystem milkyWay = new SolarSystem.MutableSolarSystem(creator.createMilkyWay());
      SolarSystem.MutableSolarSystem andromeda = new SolarSystem.MutableSolarSystem(creator.createAndromeda());
      solarSystems.add(andromeda);
      solarSystems.add(milkyWay);
      SolarSystemController controller;
      controller = new SolarSystemController(view, solarSystems);
      controller.startApp();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
