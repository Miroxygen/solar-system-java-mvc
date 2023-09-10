import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Moon;
import model.Planet;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemTests {

    private model.SolarSystem solarSystem;

    @BeforeEach
    public void setUp() {
        model.Sun sun = new model.Sun("Sun", 20001);
        solarSystem = new model.SolarSystem("Test Solar System", sun);
    }

    @Test
    public void testDeletePlanetRemovesMoons() {
      model.Planet planet = new model.Planet("Test Planet", 1001, 210000, 20001);
      model.Moon moon1 = new model.Moon("Moon 1", 100, 200000, 5000);
      model.Moon moon2 = new model.Moon("Moon 2", 50, 200000, 5000);
      planet.addMoon(moon1);
      planet.addMoon(moon2);
      solarSystem.addPlanet(planet);
      solarSystem.deletePlanet(planet);
      assertTrue(solarSystem.getMutablePlanets().isEmpty());
    }

    @Test
    public void testSetOrbitRadiusThrowsException() {
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          Planet planet = new Planet("Planet", 5000, 10000, 696340);
      });
      String expectedMessage = "Planet's orbit radius must be larger than 10x the sun radius.";
      String actualMessage = exception.getMessage();
      assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testMoonNamesAreUniqueWithinPlanet() {
      model.Planet planet = new model.Planet("Test Planet", 1001, 210000, 20001);
      Moon moon1 = new Moon("Moon", 100, 200000, 5000);
      Moon moon2 = new Moon("Moon", 100, 200000, 5000);
      planet.addMoon(moon1);
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        planet.addMoon(moon2);
      });
      String expectedMessage = "Moon name already exists.";
      String actualMessage = exception.getMessage();
      assertTrue(actualMessage.contains(expectedMessage));
    }
}

