package controller;

import model.Moon;
import model.Planet;
import model.SolarSystem;
import model.Sun;

/**
 * For populating app with data.
 */
public class SolarSystemCreator {
  
  /**
   * Creates the Milky Way.
   *
   * @return Object.
   */
  public SolarSystem createMilkyWay() {
    final Sun sun = new Sun("Sun", 696340);
    final SolarSystem solarSystem = new SolarSystem("Milky Way", sun);
    final Planet earth = new Planet("Earth", 6371, 149600000, 6963400); 
    earth.addMoon(new Moon("Moon", 337, 384400, 6371));
    final Planet uranus = new Planet("Uranus", 2536, 287099000, 6963400);
    uranus.addMoon(new Moon("Titania", 123, 436300, 2536));
    uranus.addMoon(new Moon("Miranda", 78, 129900, 2536));
    uranus.addMoon(new Moon("Oberon", 109, 584000, 2536));
    solarSystem.addPlanet(earth);
    solarSystem.addPlanet(uranus);
    return solarSystem;
  }

  /**
   * Creates Andromeda.
   *
   * @return Object.
   */
  public SolarSystem createAndromeda() {
    final Sun sun = new Sun("Eldritch Star", 823910);
    final SolarSystem solarSystem = new SolarSystem("Andromeda", sun);
    final Planet elderPlanet1 = new Planet("Cthulhu", 5432, 17923450, 823910);
    final Planet elderPlanet2 = new Planet("Azathoth", 8743, 23767890, 823910);
    elderPlanet2.addMoon(new Moon("Yog-Sothoth", 162, 67989, 8743));
    elderPlanet2.addMoon(new Moon("Shub-Niggurath", 324, 65621, 8743));
    elderPlanet2.addMoon(new Moon("Azagoth", 278, 77521, 8743));
    elderPlanet2.addMoon(new Moon("Nyarlathotep", 98, 86789, 8743));
    solarSystem.addPlanet(elderPlanet1);
    solarSystem.addPlanet(elderPlanet2);
    return solarSystem;
  }
}
