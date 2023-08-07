package controller;

import java.util.Scanner;
import model.SolarSystem;
import model.Sun;
import view.SolarSystemView;

public class SolarSystemController {
    private SolarSystem solarSystem;
    private SolarSystemView view;

    public SolarSystemController(SolarSystem solarSystem, SolarSystemView view) {
        this.solarSystem = solarSystem;
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

          solarSystem = newSolarSystem;
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
    }

    public void displaySolarSystem() {
        view.displaySolarSystem(solarSystem);
    }

    // Other methods to add planets and moons
}

