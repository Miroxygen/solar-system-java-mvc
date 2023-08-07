package model;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
    private String name;
    private List<Sun> suns;

    public SolarSystem(String name) {
        this.name = name;
        suns = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Sun> getSuns() {
      return new ArrayList<>(suns);
    }

    public void addSun(Sun sun) {
        suns.add(sun);
    }
}
