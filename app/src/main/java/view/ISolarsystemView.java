package view;

import java.util.List;

import model.SolarSystem;

public interface ISolarsystemView {
  
  public static enum Menu {
    List,
    Add,
    Inspect
  }

  void displaySolarSystems(List<SolarSystem> solarSystems);

  SolarSystem createSolarSystem();

  void displaySolarSystemDetails(SolarSystem solarSystem);

  void showMessage(String message);
}
