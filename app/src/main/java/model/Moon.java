package model;

/**
 * Represents a moon.
 */
public class Moon {
  private String name;
  private int radius;
  private int orbitRadius;
  private int planetRadius;

  /**
   * Constructor.
   *
   * @param name String.
   * @param radius Integer.
   * @param orbitRadius Integer.
   * @param planetRadius Integer.
   */
  public Moon(String name, int radius, int orbitRadius, int planetRadius) {
    this.planetRadius = planetRadius;
    setName(name);
    setOrbitRadius(orbitRadius);
    setRadius(radius);
  }

  /**
   * Gets moons name.
   *
   * @return String
   */
  public String getName() {
    return name;
  }

  /**
   * Sets moons name.
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
   * Gets radius.
   *
   * @return Moons radius.
   */
  public int getRadius() {
    return radius;
  }

  /**
   * Sets moons radius.
   *
   * @param radius Integer.
   */
  private void setRadius(int radius) {
    if (radius > 10 && radius <= planetRadius / 17) {
      this.radius = radius;
    } else {
      throw new IllegalArgumentException("Moon must be larger than 10km and 17x less than the planet radius.");
    }
  }

  /**
   * Get moons orbitradius.
   *
   * @return Integer.
   */
  public int getOrbitRadius() {
    return orbitRadius;
  }

  /**
   * Sets moons orbitradius.
   *
   * @param orbitRadius Integer.
   */
  private void setOrbitRadius(int orbitRadius) {
    if (orbitRadius >= planetRadius * 5) {
      this.orbitRadius = orbitRadius;
    } else {
      throw new IllegalArgumentException("Moon's orbit radius must be 5x the planet radius.");
    }
  }

  /**
   * Gets planets radius.
   *
   * @return Integer.
   */
  private int getPlanetRadius() {
    return planetRadius;
  }

  /**
   * Mutable version of moon.
   */
  public static class MutableMoon extends Moon {

    /**
     * Constructor.
     *
     * @param moon Object.
     */
    public MutableMoon(Moon moon) {
      super(moon.getName(), moon.getRadius(), moon.getOrbitRadius(), moon.getPlanetRadius());
    }
    
  }
}

