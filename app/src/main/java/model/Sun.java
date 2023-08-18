package model;

/**
 * Implements a sun.
 */
public class Sun {
  private String name;
  private int radius;

  public Sun(String name, int radius) {
    setName(name);
    setRadius(radius);
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
   * Sets suns name.
   *
   * @param name String.
   */
  private void setName(String name) {
    if(name != null && !name.isEmpty()) {
        this.name = name;
    } else {
        throw new IllegalArgumentException("Name cannot be null or empty.");
    }
  }

  /**
   * Gets radius.
   *
   * @return Integer.
   */
  public int getRadius() {
      return radius;
  }

  /**
   * Sets suns radius.
   *
   * @param radius Integer.
   */
  private void setRadius(int radius) {
    if(radius <= 20000) {
            throw new IllegalArgumentException("Sun radius must be larger than 20,000 km");
        } else {
            this.radius = radius;
        }
  }
}

