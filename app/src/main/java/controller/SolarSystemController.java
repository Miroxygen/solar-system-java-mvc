package controller;

import java.util.ArrayList;
import java.util.List;
import view.SolarSystemView;

public class SolarSystemController {
    private List<model.SolarSystem> solarSystemList;
    private SolarSystemView view;
    private model.SolarSystem currentSolarSystem;

    public SolarSystemController(SolarSystemView view) {
        this.solarSystemList = new ArrayList<>();
        this.view = view;
    }

    /**
     * Starting point of application.
     */
    public void startApp() {
      try {
        Boolean running = true;
        while(running) {
          view.SolarSystemView.Menu action = view.showMenu();
          switch (action) {
            case List:
              displaySolarSystem();
              break;
            case Add:
              createSolarSystem();
              break;
            case Inspect:
              solarSystemMenu();
              break;
            case Quit:
              running = false;
            default:
              running = false;
              break;
          }
        }
      } catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }

    private void solarSystemMenu() {
      try {
        Boolean running = true;
        currentSolarSystem = selectSpecificSolarSystem();
        while (running) {
          view.SolarSystemView.SolarSystemMenu action = view.showSolarSystemMenu();
          switch (action) {
            case View:
              displaySolarSystemDetails(currentSolarSystem);
              break;
            case Add:
              addMemberMenu();
              break;
            case Delete:
              deleteMemberOfSolarSystem(currentSolarSystem);
              break;
            case Back:
              running = false;
              break;
            default:
              running = false;
              break;
          }
        }
      } catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }

    private void addMemberMenu() {
      view.SolarSystemView.AddMemberMenu action = view.showAddMemberMenu();
      switch (action) {
        case Planet:
          createPlanet();
          break;
        case Moon:
          createMoon();
          break;
        default:
          break;
      }
    }

    /**
     * Creates a new object of Solarsystem.
     */
    private void createSolarSystem() {
      model.SolarSystem newSolarSystem = view.createSolarSystem();
      solarSystemList.add(newSolarSystem);
    }

    /**
     * Deletes an object.
     *
     * @param solarSystem Solarsystem object.
     */
    private void deleteMemberOfSolarSystem(model.SolarSystem solarSystem) {
      String memberName = view.deleteMember();
      if(solarSystem.getCentralStar().getName().equalsIgnoreCase(memberName)) {
        solarSystem = null;
      } else {
        for (model.Planet planet : solarSystem.getPlanets()) {
          if (planet.getName().equalsIgnoreCase(memberName)) {
              solarSystem.getPlanets().remove(planet);
              break;
          } else {
              for (model.Moon moon : planet.getMoons()) {
                  if (moon.getName().equalsIgnoreCase(memberName)) {
                      planet.getMoons().remove(moon);
                      break;
                  }
              }
            }
          }
      }
    }

    /**
     * Displays all solar systems.
     */
    private void displaySolarSystem() {
      view.displaySolarSystems(solarSystemList);
    }

    /**
     * Pick a specific solarsystem for actions.
     *
     * @return Solarsystem object.
     */
    private model.SolarSystem selectSpecificSolarSystem() {
      return view.selectSolarSystem(solarSystemList);
    }

    /**
     * Displays details of one solarsystem.
     */
    private void displaySolarSystemDetails(model.SolarSystem solarSystem) {
      view.displaySolarSystemDetails(solarSystem);
    }

    /**
     * Creates a planet.
     *
     * @return Planet object.
     */
    private void createPlanet() {
      currentSolarSystem.addPlanet(view.createPlanet());
    }

    private void createMoon() {
      model.Planet planet = view.selectPlanet(currentSolarSystem.getPlanets());
      planet.addMoon(view.createMoon());
    }
}

