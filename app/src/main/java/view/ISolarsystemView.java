package view;

import java.util.List;

/**
 * Interface for SolarSystemView.
 */
public interface ISolarsystemView {
  
  /**
   * For showing menu.
   */
  static enum Menu {
    List,
    Add,
    Inspect,
    Quit
  }

  /**
   * For showing menu.
   */
  static enum SolarSystemMenu {
    View,
    Add,
    Delete,
    Back,
  }

  /**
   * For showing menu.
   */
  static enum AddMemberMenu {
    Planet,
    Moon
  }

  /**
   * For showing menu.
   */
  static enum ListMenu {
    Size,
    Orbit
  }

  <T extends model.SolarSystem> void displaySolarSystems(Iterable<T> solarSystems);

  model.SolarSystem createSolarSystem();

  /**
   * Select a solarsystem from a list. 
   *
   * @param <T> Member.
   * @param list List of member.
   * @return Member.
   */
  <T extends model.SolarSystem> T selectSolarSystem(Iterable<T> list);

  <T extends model.SolarSystem>  void displaySolarSystemDetails(T solarSystem);

  <T extends model.Planet>  void displayPlanetAndMoons(T planet, List<? extends model.Moon> moons);

  model.Planet createPlanet(int sunRadius);

  <T extends model.Planet> T selectPlanet(Iterable<T> list);

  model.Moon createMoon(int planetRadius);

  String deleteMember();

  void showMessage(String message);
}
