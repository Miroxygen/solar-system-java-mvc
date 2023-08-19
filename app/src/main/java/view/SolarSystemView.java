package view;

import java.util.List;
import java.util.Scanner;

/**
 * View for Solarsystem app.
 */
public class SolarSystemView implements ISolarsystemView {
  private final Scanner input = new Scanner(System.in, "utf-8");

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
    if (key.equals(list)) {
      return Menu.List;
    } else if (key.equals(add)) {
      return Menu.Add;
    } else if (key.equals(inspect)) {
      return Menu.Inspect;
    } else if (key.equals(quit)) {
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
    if (key.equals(view)) {
      return SolarSystemMenu.View;
    } else if (key.equals(add)) {
      return SolarSystemMenu.Add;
    } else if (key.equals(delete)) {
      return SolarSystemMenu.Delete;
    } else if (key.equals(back)) {
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
    if (key.equals(planet)) {
      return AddMemberMenu.Planet;
    } else if (key.equals(moon)) {
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
    if (key.equals(size)) {
      return ListMenu.Size;
    } else if (key.equals(orbit)) {
      return ListMenu.Orbit;
    }
    return null;
  }

  /**
   * Input validation for string.
   *
   * @param prompt String
   * @return Input value.
   */
  private String getNonEmptyInput(String prompt) {
    while (true) {
      System.out.println(prompt);
      String value = input.nextLine().trim();
      if (!value.isEmpty()) {
        return value;
      }
      System.out.println("Invalid input. Please enter a non-empty value.");
    }
  }

  /**
   * Input validation for integer.
   *
   * @param prompt String.
   * @return Input in integer.
   */
  private int getIntegerInput(String prompt) {
    while (true) {
      try {
        System.out.println(prompt);
        return Integer.parseInt(input.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
      }
    }
  }

  
  /**
   * For creating a solarsystem.
   */
  public model.SolarSystem createSolarSystem() {
    try {
      String starName = getNonEmptyInput("Enter the name of the central star: ");
      int starRadius = getIntegerInput("Enter the radius of the central star (in km): ");
      model.Sun centralStar = new model.Sun(starName, starRadius);
      String solarSystemName = getNonEmptyInput("Enter the name of the solar system: ");
      model.SolarSystem newSolarSystem = new model.SolarSystem(solarSystemName, centralStar);
      return newSolarSystem;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null; 
  }

  /**
   * For creating a planet.
   */
  public model.Planet createPlanet(int sunRadius) {
    try {
      String planetName = getNonEmptyInput("Enter name of the planet:");
      int planetRadius = getIntegerInput("Enter radius of planet:");
      int planetOrbitRadius = getIntegerInput("Enter orbit radius of planet:");
      model.Planet newPlanet = new model.Planet(planetName, planetRadius, planetOrbitRadius, sunRadius);
      return newPlanet;
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
      String moonName = getNonEmptyInput("Enter name of moon :");
      int moonRadius = getIntegerInput("Enter radius of moon:");
      int moonOrbitRadius = getIntegerInput("Enter orbit radius of moon:");
      model.Moon newMoon = new model.Moon(moonName, moonRadius, moonOrbitRadius, planetRadius);
      return newMoon;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  /**
   * For displaying solarsystems.
   */
  public <T extends model.SolarSystem> void displaySolarSystems(Iterable<T> solarSystems) {
    int iterator = 0;
    for (model.SolarSystem s : solarSystems) {
      iterator++;
      System.out.println(" Name : " + s.getName());
    }
    if (iterator == 0) {
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
      int key = getIntegerInput("Which solarsystem do you want to inspect?");
      index = 0;
      for (T s : list) {
        if (index == key) {
          return s;
        }
        index++;
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
    System.out.println("-*-*- Central Star: " 
        + centralStar.getName() + ", Radius: " + centralStar.getRadius() + " km");
  }

  /**
   * For displaying planets and moons.
   */
  public <T extends model.Planet> void displayPlanetAndMoons(T planet, List<? extends model.Moon> moons) {
    System.out.println("-*-*--*-*- Planet: " + planet.getName() + ", Radius: " 
        + planet.getRadius() + " km, Orbit Radius: " + planet.getOrbitRadius() + " km");
    if (moons.size() > 0) {
      for (model.Moon moon : moons) {
        System.out.println("-*-*--*-*--*-*- Moon: " + moon.getName() + ", Radius: " + moon.getRadius()
            + " km, Orbit Radius: " + moon.getOrbitRadius() + " km");
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
   * For selecting a planet.
   */
  public <T extends model.Planet> T selectPlanet(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Planet p : list) {
        System.out.println(index + " | " + p.getName());
        index++;
      }
      if (index == 0) {
        System.out.println("No planets available.");
      } else {
        int key = getIntegerInput("Which planet do you want to add a moon to?");
        index = 0;
        for (T s : list) {
          if (index == key) {
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
}
