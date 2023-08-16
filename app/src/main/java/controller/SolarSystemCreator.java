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
        SolarSystem solarSystem = new SolarSystem("Milky Way");
        Sun sun = new Sun("Sun", 696340);
        solarSystem.setCentralStar(sun);
        Planet earth = new Planet("Earth", 6371, 149600000); 
        earth.addMoon(new Moon("Moon", 1737, 384400));
        Planet uranus = new Planet("Uranus", 25362, 287099000);
        uranus.addMoon(new Moon("Titania", 788, 436300));
        uranus.addMoon(new Moon("Miranda", 236, 129900));
        uranus.addMoon(new Moon("Oberon", 761, 584000));
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
      SolarSystem solarSystem = new SolarSystem("Andromeda");
      Sun sun = new Sun("Eldritch Star", 823910);
      solarSystem.setCentralStar(sun);
      Planet fantasyPlanet1 = new Planet("Cthulhu", 5432, 1792345);
      Planet fantasyPlanet2 = new Planet("Azathoth", 8743, 2376789);
      fantasyPlanet2.addMoon(new Moon("Yog-Sothoth", 162, 67989));
      fantasyPlanet2.addMoon(new Moon("Shub-Niggurath", 324, 15621));
      fantasyPlanet2.addMoon(new Moon("Azagoth", 278, 37521));
      fantasyPlanet2.addMoon(new Moon("Nyarlathotep", 98, 26789));
      solarSystem.addPlanet(fantasyPlanet1);
      solarSystem.addPlanet(fantasyPlanet2);
      return solarSystem;
  }
}
