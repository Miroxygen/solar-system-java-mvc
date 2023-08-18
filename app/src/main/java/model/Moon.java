package model;

public class Moon {
  private String name;
  private int radius;
  private int orbitRadius;
  private int planetRadius;

  public Moon(String name, int radius, int orbitRadius, int planetRadius) {
    this.planetRadius = planetRadius;
    setName(name);
    setOrbitRadius(orbitRadius);
    setRadius(radius);
  }

  public String getName() {
    return name;
  }

  private void setName(String name) {
    if (name != null && !name.isEmpty()) {
        this.name = name;
    } else {
        throw new IllegalArgumentException("Name cannot be null or empty.");
    }
  }

  public int getRadius() {
    return radius;
  }

  private void setRadius(int radius) {
    if (radius > 10 && radius <= planetRadius / 17) {
        this.radius = radius;
    } else {
        throw new IllegalArgumentException("Moon must be larger than 10km and 17x less than the planet radius.");
    }
  }

  public int getOrbitRadius() {
    return orbitRadius;
  }

  private void setOrbitRadius(int orbitRadius) {
    if (orbitRadius >= planetRadius * 5) {
        this.orbitRadius = orbitRadius;
    } else {
        throw new IllegalArgumentException("Moon's orbit radius must be 5x the planet radius.");
    }
  }
}

