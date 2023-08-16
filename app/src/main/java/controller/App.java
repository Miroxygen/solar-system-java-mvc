package controller;

import java.util.ArrayList;
import java.util.List;
import model.SolarSystem;
import view.SolarSystemView;

public class App {
  
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    SolarSystemView view = new SolarSystemView();
    List<SolarSystem> solarSystems = new ArrayList<>();
    SolarSystemCreator creator = new SolarSystemCreator();
    SolarSystem milkyWay = creator.createMilkyWay();
    SolarSystem andromeda = creator.createAndromeda();
    solarSystems.add(andromeda);
    solarSystems.add(milkyWay);
    SolarSystemController controller = new SolarSystemController(view, solarSystems);
    controller.startApp();
  }
}
