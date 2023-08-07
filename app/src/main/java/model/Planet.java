package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Planet class.
 */
public class Planet {
  private String name;
  private int radius;
  private int orbitRadius;
  private List<Moon> moons;

  /**
   * Constructor.
   *
   * @param name Planets name.
   * @param radius Planets radius.
   * @param orbitRadius Planets orbitradius.
   */
  public Planet(String name, int radius, int orbitRadius) {
      this.name = name;
      this.radius = radius;
      this.orbitRadius = orbitRadius;
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
   * Gets planets radius.
   *
   * @return Integer.
   */
  public int getRadius() {
      return radius;
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
   * Gets planets moons.
   *
   * @return Arraylist.
   */
  public List<Moon> getMoons() {
    return new ArrayList<>(moons);
  }

  /**
   * Adds a moon.
   *
   * @param moon Object.
   */
  public void addMoon(Moon moon) {
    moons.add(moon);
  }

  public void deleteMoon(Moon moon) {
    moons.remove(moon);
  }
}

