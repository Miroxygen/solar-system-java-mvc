package model;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    private String name;
    private Sun centralStar;
    private List<Planet> planets;

    public SolarSystem(String name) {
        this.name = name;
        planets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Sun getCentralStar() {
      return centralStar;
    }

    public void setCentralStar(Sun centralStar) {
        this.centralStar = centralStar;
    }

    public List<Planet> getPlanets() {
      return new ArrayList<>(planets);
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }
}
