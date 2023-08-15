package view;

import java.util.List;
import java.util.Scanner;

/**
 * View for Solarsystem app.
 */
public class SolarSystemView implements ISolarsystemView {
  private final Scanner input = new Scanner(System.in, "utf-8");
  private final String hieraricalStringSeperator = "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500";

  /**
   * Shows starting menu.
   *
   * @return Enum.
   */
  public Menu showMenu() {
    final String list = "L";
    final String add = "A";
    final String inspect = "I";
    final String quit = "Q";
    System.out.println("-*-*- Solarsystem app -*-*-");
    System.out.println("-*-*- " + list + " List all solarsystems");
    System.out.println("-*-*- " + add + " Add new solarsystem");
    System.out.println("-*-*- " + inspect + " Look at detail information on specific solarsystem");
    System.out.println("-*-*- " + quit + " Quit application.");
    String key = input.nextLine();
    if(key.equals(list)) {
      return Menu.List;
    } else if(key.equals(add)) {
      return Menu.Add;
    } else if(key.equals(inspect)) {
      return Menu.Inspect;
    } else if(key.equals(quit)) {
      return Menu.Quit;
    }
    return null;
  }

  /**
   * Menu for looking at individual solarsystems.
   *
   * @return Enum.
   */
  public SolarSystemMenu showSolarSystemMenu() {
    final String view = "V";
    final String add = "A";
    final String delete = "D";
    final String back = "B";
    System.out.println("-*-*- " + view + " View solarsystem details");
    System.out.println("-*-*- " + add + " Add new members to solarsystem");
    System.out.println("-*-*- " + delete + " Delete members of solarsystem.");
    System.out.println("-*-*- " + back + " Back to main menu.");
    String key = input.nextLine();
    if(key.equals(view)) {
      return SolarSystemMenu.View;
    } else if(key.equals(add)) {
      return SolarSystemMenu.Add;
    } else if(key.equals(delete)) {
      return SolarSystemMenu.Delete;
    } else if(key.equals(back)) {
      return SolarSystemMenu.Back;
    }
    return null;
  }

  /**
   * Menu for adding member.
   *
   * @return Enum.
   */
  public AddMemberMenu showAddMemberMenu() {
    final String planet = "P";
    final String moon = "M";
    System.out.println("-*-*- " + planet + " Add planet.");
    System.out.println("-*-*- " + moon + " Add moon.");
    String key = input.nextLine();
    if(key.equals(planet)) {
      return AddMemberMenu.Planet;
    } else if(key.equals(moon)) {
      return AddMemberMenu.Moon;
    } 
    return null;
  }

  /**
   * Menu for showing lists.
   *
   * @return Enum.
   */
  public ListMenu showListMenu() {
    final String size = "S";
    final String orbit = "O";
    System.out.println("-*-*- " + size + " List by size.");
    System.out.println("-*-*- " + orbit + " List by orbitradius.");
    String key = input.nextLine();
    if(key.equals(size)) {
      return ListMenu.Size;
    } else if(key.equals(orbit)) {
      return ListMenu.Orbit;
    }
    return null;
  }

  
  /**
   * For creating a solarsystem.
   */
  public model.SolarSystem createSolarSystem() {
    System.out.print("Enter the name of the solar system: ");
    String solarSystemName = input.nextLine();
    while(solarSystemName.trim().isEmpty()) {
      System.out.println("Please enter a non-empty name for the solarsystem.");
      solarSystemName = input.nextLine();
    }
    model.SolarSystem newSolarSystem = new model.SolarSystem(solarSystemName);
    System.out.print("Enter the name of the central star: ");
    String starName = input.nextLine();
    while(starName.trim().isEmpty()) {
      System.out.println("Please enter a non-empty name for the central star.");
      starName = input.nextLine();
    }
    int starRadius;
    while (true) {
          try {
              System.out.print("Enter the radius of the central star (in km): ");
              starRadius = Integer.parseInt(input.nextLine());
              if (starRadius >= 20000) {
                break;
              } else {
                  System.out.println("Invalid input. Star radius must be at least 20000 km.");
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
          }
      }
    model.Sun centralStar = new model.Sun(starName, starRadius);
    newSolarSystem.setCentralStar(centralStar);
    return newSolarSystem;
  }

  /**
   * For displaying solarsystems.
   */
  public <T extends model.SolarSystem> void displaySolarSystems(Iterable<T> solarSystems) {
    int iterator = 0;
    for(model.SolarSystem s : solarSystems) {
      iterator++;
        System.out.println(" Name : " + s.getName());
    }
    if(iterator == 0) {
      System.out.println("No solarsystems.");
    }
  }

  /**
   * For selecting a particular solarsystem.
   */
  public <T extends model.SolarSystem> T selectSolarSystem(Iterable<T> list) {
    try {
      int index = 0;
      for (model.SolarSystem s : list) {
        System.out.println(index + " | " + s.getName());
        index++;
      }
      if(index == 0) {
        System.out.println("No solarsystems.");
      } else {
        System.out.println("Which solarsystem do you want to inspect?");
        String key = input.nextLine();
        int intKey = Integer.parseInt(key);
        index = 0;
        for (T s : list) {
          if (index == intKey) {
            return s;
          }
          index++;
        }
      } 
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * For displaying details about a solarsystem.
   */
  public <T extends model.SolarSystem> void displaySolarSystemDetails(T solarSystem) {
    System.out.println("Solar System: " + solarSystem.getName());
    model.Sun centralStar = solarSystem.getCentralStar();
    System.out.println(hieraricalStringSeperator + " Central Star: " + centralStar.getName() + ", Radius: " + centralStar.getRadius() + " km");
    List<model.Planet> planets = solarSystem.getPlanets();
    if(planets.size() > 0) {
      for (model.Planet planet : planets) {
        displayPlanetAndMoons(planet);
      } 
    } else {
      System.out.println("No planets.");
    }
  }

  /**
   * For displaying planets and moons.
   */
  public <T extends model.Planet> void displayPlanetAndMoons(T planet) {
    System.out.println(hieraricalStringSeperator + hieraricalStringSeperator + " Planet: " + planet.getName() + ", Radius: " + planet.getRadius() + " km, Orbit Radius: " + planet.getOrbitRadius() + " km");
    List<model.Moon> moons = planet.getMoons();
    if(moons.size() > 0) {
      for (model.Moon moon : moons) {
        System.out.println(hieraricalStringSeperator + hieraricalStringSeperator + hieraricalStringSeperator + " Moon: " + moon.getName() + ", Radius: " + moon.getRadius() + " km, Orbit Radius: " + moon.getOrbitRadius() + " km");
      }
    } else {
      System.out.println("This planets has no moons.");
    }
  }


  /**
   * For deleting a member.
   */
  public String deleteMember() {
    System.out.print("Enter the name of the member (Sun, Planet, or Moon) to delete: ");
    String memberName = input.nextLine();
    return memberName;
  }

  /**
   * For showing text to user.
   */
  public void showMessage(String message) {
    System.out.println(message);
  }

  /**
   * For creating a planet.
   */
  public model.Planet createPlanet(int sunRadius) {
    try {
      System.out.println("Enter name of the planet:");
      String planetName = input.nextLine();
      while (planetName.trim().isEmpty()) {
        System.out.println("Invalid input. Please enter a non-empty name for the planet:");
        planetName = input.nextLine();
      }
      int planetRadius;
      while (true) {
          try {
              System.out.println("Enter radius of planet:");
              planetRadius = Integer.parseInt(input.nextLine());
              if(planetRadius >= 1000 && planetRadius <= (sunRadius / 10)) {
                break;
              } else {
                System.out.println("Invalid input. Planet radius must be at least 10.000 km and 10x less than it's suns radius of " + sunRadius);
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
          }
      }
      int planetOrbitRadius;
      while (true) {
          try {
              System.out.println("Enter orbit radius of planet:");
              planetOrbitRadius = Integer.parseInt(input.nextLine());
              if(planetOrbitRadius >= (sunRadius * 10)) {
                break;
              } else {
                System.out.println("Invalid input. Planet orbit radius must be 10x larger than it's suns radius of " + sunRadius);
              }
              break;
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
          }
      }
      model.Planet newPlanet = new model.Planet(planetName, planetRadius, planetOrbitRadius);
      return newPlanet;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * For selecting a planet.
   */
  public <T extends model.Planet> T selectPlanet(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Planet p : list) {
        System.out.println(index + " | " + p.getName());
        index++;
      }
      if(index == 0) {
        System.out.println("No planets available.");
      } else {
        System.out.println("Which planet do you want to add a moon to?");
        String key = input.nextLine();
        int intKey = Integer.parseInt(key);
        index = 0;
        for (T s : list) {
          if (index == intKey) {
            return s;
          }
          index++;
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * For creating a moon.
   */
  public model.Moon createMoon(int planetRadius) {
    try {
      System.out.println("Enter name of moon :");
      String moonName = input.nextLine();
      while(moonName.trim().isEmpty()) {
        System.out.println("Invalid input. Please enter a non-empty name for the moon:");
        moonName = input.nextLine();
      }
     int moonRadius;
      while (true) {
          try {
              System.out.println("Enter radius of moon:");
              moonRadius = Integer.parseInt(input.nextLine());
              if(moonRadius >= 10 && moonRadius <= (planetRadius / 17)) {
                break;
              } else {
                System.out.println("Invalid input. Moons radius must be at least 10 km and 17x less than it's planets radius of " + planetRadius);
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
          }
      }
      int moonOrbitRadius;
      while (true) {
          try {
              System.out.println("Enter orbit radius of moon:");
              moonOrbitRadius = Integer.parseInt(input.nextLine());
              if(moonOrbitRadius >= (planetRadius * 5)) {
                break;
              } else {
                System.out.println("Invalid input. Moons radius must be 5x larger than it's planets radius of " + planetRadius);
              }
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter a valid integer.");
          }
      }
      model.Moon newMoon = new model.Moon(moonName, moonRadius, moonOrbitRadius);
      return newMoon;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
