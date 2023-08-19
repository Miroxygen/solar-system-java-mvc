package model;

import java.util.ArrayList;
import java.util.List;
import model.Planet.MutablePlanet;

/**
 * Represents a solarsystem.
 */
public class SolarSystem {
  private String name;
  private Sun centralStar;
  private List<Planet> planets;
  List<String> usedNames;

  /**
   * Constructor.
   *
   * @param name String.
   */
  public SolarSystem(String name, model.Sun centralStar) {
    setName(name);
    setCentralStar(centralStar);
    planets = new ArrayList<>();
    usedNames = new ArrayList<>();
  }

  /**
   * For child object.
   *
   * @param solarSystem Object.
   */
  private SolarSystem(SolarSystem solarSystem) {
    this.name = solarSystem.getName();
    this.centralStar = solarSystem.getCentralStar();
    this.planets = solarSystem.getRealPlanets();
    this.usedNames = solarSystem.getUsedNamed();
  }

  /**
   * For getting used names.
   * Only for constructor use.
   *
   * @return List of strings.
   */
  private List<String> getUsedNamed() {
    List<String> usedNamesCopy = new ArrayList<>(usedNames);
    return usedNamesCopy;
  }

  /**
   * Sets solarsystems name.
   *
   * @param name String.
   */
  private void setName(String name) {
    if (name != null && !name.isEmpty()) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Name cannot be null or empty.");
    }
  }

  /**
   * For getting name.
   *
   * @return String.
   */
  public String getName() {
    return name;
  }

  /**
   * For getting the central star.
   *
   * @return Object.
   */
  public Sun.MutableSun getCentralStar() {
    Sun.MutableSun sunCopy = new Sun.MutableSun(centralStar);
    return sunCopy;
  }

  /**
   * Sets the central star.
   *
   * @param centralStar Object.
   */
  private void setCentralStar(Sun centralStar) {
    if (this.centralStar != null) {
      throw new IllegalStateException("A central star is already set for this solar system.");
    }
    if (centralStar == null) {
      throw new IllegalArgumentException("Central star cannot be null.");
    }
    this.centralStar = centralStar;
  }

  /**
   * For getting mutable version of the planets, to sort etc.
   *
   * @return List of objects.
   */
  public List<MutablePlanet> getMutablePlanets() {
    List<Planet.MutablePlanet> mutablePlanets = new ArrayList<>();
    for (Planet p : planets) {
      mutablePlanets.add(new Planet.MutablePlanet(p));
    }
    return mutablePlanets;
  }

  /**
   * For iteration purposes.
   *
   * @return Iterable.
   */
  public Iterable<Planet> getPlanets() {
    return new ArrayList<>(planets);
  }

  /**
   * Get noniterable for childconstructor.
   *
   * @return List of objects.
   */
  private List<Planet> getRealPlanets() {
    return new ArrayList<>(planets);
  }

  /**
   * Adds a planet.
   *
   * @param planet Object.
   */
  public void addPlanet(Planet planet) {
    if (planet != null) {
      if (usedNames.contains(planet.getName())) {
        throw new IllegalArgumentException("Planet name already exists.");
      } else {
        usedNames.add(planet.getName());
        planets.add(planet);
      }
    }
  }

  /**
   * Deletes a planet.
   *
   * @param planet Object.
   */
  public void deletePlanet(Planet planet) {
    planets.remove(planet);
  }

  /**
   * Mutable version of Solarsystem.
   */
  public static class MutableSolarSystem extends SolarSystem {

    /**
     * Constructor.
     *
     * @param solarSystem Object.
     */
    public MutableSolarSystem(SolarSystem solarSystem) {
      super(solarSystem);
    }
    
  }
}
