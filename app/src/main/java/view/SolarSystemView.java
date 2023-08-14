package view;

import java.util.List;
import java.util.Scanner;

import org.checkerframework.checker.units.qual.m;

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

  @Override
  public model.SolarSystem createSolarSystem() {
    System.out.print("Enter the name of the solar system: ");
    String solarSystemName = input.nextLine();
    model.SolarSystem newSolarSystem = new model.SolarSystem(solarSystemName);
    System.out.print("Enter the name of the central star: ");
    String starName = input.nextLine();
    System.out.print("Enter the radius of the central star (in km): ");
    int starRadius = Integer.parseInt(input.nextLine());
    model.Sun centralStar = new model.Sun(starName, starRadius);
    newSolarSystem.setCentralStar(centralStar);
    return newSolarSystem;
  }

  @Override
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

  @Override
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

  @Override
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

  @Override
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

  @Override
  public String deleteMember() {
    System.out.print("Enter the name of the member (Sun, Planet, or Moon) to delete: ");
    String memberName = input.nextLine();
    return memberName;
  }

  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }

  public model.Planet createPlanet() {
    System.out.println("Enter name of the planet :");
    String planetName = input.nextLine();
    System.out.println("Enter radius of planet : ");
    int planetRadius = Integer.parseInt(input.nextLine());
    System.out.println("Enter orbit radius of planet :");
    int planetOrdbitRadius = Integer.parseInt(input.nextLine());
    model.Planet newPlanet = new model.Planet(planetName, planetRadius, planetOrdbitRadius);
    return newPlanet;
  }

  @Override
  public <T extends model.Planet> T selectPlanet(Iterable<T> list) {
    try {
      int index = 0;
      for (model.Planet p : list) {
        System.out.println(index + " | " + p.getName());
        index++;
      }
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  public model.Moon createMoon() {
    System.out.println("Enter name of moon :");
    String moonName = input.nextLine();
    System.out.println("Enter radius of moon : ");
    int moonRadius = Integer.parseInt(input.nextLine());
    System.out.println("Enter orbit radius of moon :");
    int moonOrdbitRadius = Integer.parseInt(input.nextLine());
    model.Moon newMoon = new model.Moon(moonName, moonRadius, moonOrdbitRadius);
    return newMoon;
  }

}
