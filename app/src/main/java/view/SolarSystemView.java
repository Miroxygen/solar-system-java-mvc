package view;

import java.util.List;
import java.util.Scanner;

import model.Planet;
import model.SolarSystem;

public class SolarSystemView implements ISolarsystemView {
  private final Scanner input = new Scanner(System.in, "utf-8");

  @Override
  public SolarSystem createSolarSystem() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createSolarSystem'");
  }

  @Override
  public <T extends model.SolarSystem> void displaySolarSystems(Iterable<T> solarSystems) {
    for(model.SolarSystem s : solarSystems) {
      System.out.println(" Name : " + s.getName());
    }
  }

  @Override
  public <T extends SolarSystem> T selectSolarSystem(Iterable<T> list) {
    try {
      int index = 0;
      for (model.SolarSystem s : list) {
        System.out.println(index + " | " + s.getName());
        index++;
      }
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }

  @Override
  public <T extends model.SolarSystem> void displaySolarSystemDetails(T solarSystem) {
    System.out.println("Solar System: " + solarSystem.getName());
    model.Sun centralStar = solarSystem.getCentralStar();
    System.out.println("Central Star: " + centralStar.getName() + ", Radius: " + centralStar.getRadius() + " km");
    List<Planet> planets = solarSystem.getPlanets();
    for (Planet planet : planets) {
        displayPlanetAndMoons(planet);
    }
  }

  @Override
  public <T extends model.Planet> void displayPlanetAndMoons(T planet) {
    System.out.println("Planet: " + planet.getName() + ", Radius: " + planet.getRadius() + " km, Orbit Radius: " + planet.getOrbitRadius() + " km");
    List<model.Moon> moons = planet.getMoons();
    for (model.Moon moon : moons) {
        System.out.println("  Moon: " + moon.getName() + ", Radius: " + moon.getRadius() + " km, Orbit Radius: " + moon.getOrbitRadius() + " km");
    }
  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'showMessage'");
  }

  @Override
  public String deleteMember() {
    System.out.print("Enter the name of the member (Sun, Planet, or Moon) to delete: ");
    String memberName = input.nextLine();
    return memberName;
  }

}
