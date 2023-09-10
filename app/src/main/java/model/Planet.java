package model;

import java.util.ArrayList;
import java.util.List;
import model.Moon.MutableMoon;

/**
 * Planet class.
 */
public class Planet {
  private String name;
  private int radius;
  private int orbitRadius;
  private int sunRadius;
  private List<Moon> moons;
  private List<String> usedNames;

  /**
   * Constructor.
   *
   * @param name Planets name.
   * @param radius Planets radius.
   * @param orbitRadius Planets orbitradius.
   */
  public Planet(String name, int radius, int orbitRadius, int sunRadius) {
    this.sunRadius = sunRadius;
    setName(name);
    setOrbitRadius(orbitRadius);
    setRadius(radius);
    moons = new ArrayList<>();
    usedNames = new ArrayList<>();
  }

  /**
   * Constructor for child.
   *
   * @param planet Object.
   */
  private Planet(Planet planet) {
    this.sunRadius = planet.getSunRadius();
    this.name = planet.getName();
    this.radius = planet.getRadius();
    this.orbitRadius = planet.getOrbitRadius();
    this.moons = planet.getRealMoons();
    this.usedNames = planet.getUsedNames();
  }

  /**
   * For getting used names.
   *
   * @return List of strings.
   */
  private List<String> getUsedNames() {
    List<String> usedNamesCopy = new ArrayList<>(usedNames);
    return usedNamesCopy;
  }

  /**
   * Gets the planets name.
   *
   * @return String.
   */
  public String getName() {
    return name;
  }

  /**
   * Set planets name.
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
   * Gets planets radius.
   *
   * @return Integer.
   */
  public int getRadius() {
    return radius;
  }

  /**
   * Set planet radius.
   *
   * @param radius Integer.
   */
  private void setRadius(int radius) {
    int maxPlanetRadius = sunRadius / 10;
    if (radius > 1000 && radius < maxPlanetRadius) {
      this.radius = radius;
    } else {
      throw new IllegalArgumentException("Planet must be larger than 1000km and 10x less than the sun radius.");
    }
  }

  /**
   * Gets planets orbitradius.
   *
   * @return Integer.
   */
  public int getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * Sets the planets orbitradius.
   *
   * @param orbitRadius Integer.
   */
  private void setOrbitRadius(int orbitRadius) {
    if (orbitRadius > 10 * sunRadius) {
      this.orbitRadius = orbitRadius;
    } else {
      throw new IllegalArgumentException("Planet's orbit radius must be larger than 10x the sun radius.");
    }
  }

  /**
   * Gets mutable version of planets moons, to sort etc.
   *
   * @return Arraylist.
   */
  public List<MutableMoon> getMutableMoons() {
    List<Moon.MutableMoon> mutableMoons = new ArrayList<>();
    for (Moon m : moons) {
      mutableMoons.add(new MutableMoon(m));
    }
    return mutableMoons;
  }

  /**
   * Return a list for iteration purposes.
   *
   * @return Iterable.
   */
  public Iterable<Moon> getMoons() {
    return new ArrayList<>(moons);
  }

  /**
   * Gets nonmutable for child constructor.
   *
   * @return List of object.
   */
  private List<Moon> getRealMoons() {
    return new ArrayList<>(moons);
  }

  /**
   * Adds a moon.
   *
   * @param moon Object.
   */
  public void addMoon(Moon moon) {
    if (moon != null) {
      if (usedNames.contains(moon.getName())) {
        throw new IllegalArgumentException("Moon name already exists.");
      } else {
        moons.add(moon);
        usedNames.add(moon.getName());
      }
    }
  }

  /**
   * Gets the suns radius.
   *
   * @return Integer.
   */
  private int getSunRadius() {
    return sunRadius;
  }

  /**
   * Deletes a moon.
   *
   * @param moon Object.
   */
  public void deleteMoon(Moon moon) {
    moons.remove(moon);
  }

  /**
   * Mutable planet.
   */
  public static class MutablePlanet extends Planet {

    public MutablePlanet(Planet planet) {
      super(planet);
    }
    
  }
}

