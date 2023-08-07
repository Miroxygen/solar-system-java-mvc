package controller;

import model.SolarSystem;
import view.SolarSystemView;

public class App {
  
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    SolarSystem solarSystem = new SolarSystem("My Solar System");
    SolarSystemView view = new SolarSystemView();
    SolarSystemController controller = new SolarSystemController(solarSystem, view);

    controller.createSolarSystem();
    controller.displaySolarSystem();
  }

}
