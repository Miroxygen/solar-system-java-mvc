package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.SolarSystem;
import model.Sun;
import view.SolarSystemView;

public class SolarSystemController {
    private List<SolarSystem> solarSystemList;
    private SolarSystemView view;

    public SolarSystemController(SolarSystemView view) {
        this.solarSystemList = new ArrayList<>();
        this.view = view;
    }

    public void createSolarSystem() {
        try (Scanner scanner = new Scanner(System.in)) {
          System.out.print("Enter the name of the solar system: ");
          String solarSystemName = scanner.nextLine();
          SolarSystem newSolarSystem = new SolarSystem(solarSystemName);

          System.out.print("Enter the name of the central star: ");
          String starName = scanner.nextLine();
          System.out.print("Enter the radius of the central star (in km): ");
          int starRadius = Integer.parseInt(scanner.nextLine());

          Sun centralStar = new Sun(starName, starRadius);
          newSolarSystem.setCentralStar(centralStar);

          solarSystemList.add(newSolarSystem);

        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
    }

    public void displaySolarSystem() {
        view.displaySolarSystem(solarSystemList);
    }

    // Other methods to add planets and moons
}

