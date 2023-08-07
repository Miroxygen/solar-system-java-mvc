package model;

public class Planet {
  private String name;
  private int radius;
  private int orbitRadius;

  public Planet(String name, int radius, int orbitRadius) {
      this.name = name;
      this.radius = radius;
      this.orbitRadius = orbitRadius;
  }

  public String getName() {
      return name;
  }

  public int getRadius() {
      return radius;
  }

  public int getOrbitRadius() {
      return orbitRadius;
  }
}

