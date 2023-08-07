package controller;

import model.SolarSystem;
import view.SolarSystemView;

public class App {
  
  /**
   * Application starting point.

   * @param args Not used.
   */
  public static void main(String[] args) {
    SolarSystemView view = new SolarSystemView();
    SolarSystemController controller = new SolarSystemController(view);
    controller.createSolarSystem();
    controller.displaySolarSystem();
  }

}
