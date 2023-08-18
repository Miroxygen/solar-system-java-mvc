package model;

/**
 * Implements a sun.
 */
public class Sun {
  private String name;
  private int radius;

  public Sun(String name, int radius) {
    if (radius <= 20000) {
        throw new IllegalArgumentException("Sun radius must be larger than 20,000 km");
    }
    this.name = name;
    this.radius = radius;
  }

  /**
   * Gets name.
   *
   * @return String.
   */
  public String getName() {
      return name;
  }

  /**
   * Gets radius.
   *
   * @return Integer.
   */
  public int getRadius() {
      return radius;
  }
}

