package model;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    private String name;
    private Sun centralStar;
    private List<Planet> planets;
    List<String> usedNames;

    public SolarSystem(String name) {
        this.name = name;
        planets = new ArrayList<>();
        usedNames = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Sun getCentralStar() {
      //make copy
      return centralStar;
    }

    public void setCentralStar(Sun centralStar) {
        this.centralStar = centralStar;
    }

    public List<Planet> getPlanets() {
      return new ArrayList<>(planets);
    }

    public void addPlanet(Planet planet) {
      if(usedNames.contains(planet.getName())) {
        throw new IllegalArgumentException("Planet name already exists.");
      } else {
        usedNames.add(planet.getName());
        planets.add(planet);
      }
    }

    public void deletePlanet(Planet planet) {
      planets.remove(planet);
    }
}
