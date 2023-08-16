package controller;

import java.util.Comparator;
import java.util.List;

import model.Moon;
import model.Planet;
import view.ISolarsystemView.ListMenu;
import view.SolarSystemView;

public class SolarSystemController {
    private List<model.SolarSystem> solarSystemList;
    private SolarSystemView view;
    private model.SolarSystem currentSolarSystem;

    public SolarSystemController(SolarSystemView view, List solarSystems) {
        this.solarSystemList = solarSystems;
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

    /**
     * Menu for individual solarsystem.
     */
    private void solarSystemMenu() {
      try {
        Boolean running = true;
        currentSolarSystem = selectSpecificSolarSystem();
        if(currentSolarSystem == null) {
          running = false;
        }
        while (running) {
          view.SolarSystemView.SolarSystemMenu action = view.showSolarSystemMenu();
          switch (action) {
            case View:
              SortCriteria criteria = chooseSortCriteria();
              sortPlanetsAndMoons(criteria);
              displaySolarSystemDetails(currentSolarSystem);
              break;
            case Add:
              addMemberMenu();
              break;
            case Delete:
              Boolean centralStarRemoved = deleteMemberOfSolarSystem(currentSolarSystem);
              if(centralStarRemoved) {
                running = false;
              }
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

    /**
     * Menu for adding a member.
     */
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
     * For selecting sortcriteria for listing.
     */
    private enum SortCriteria {
      Size(Comparator.comparingInt(model.Planet::getRadius), Comparator.comparingInt(model.Moon::getRadius)),
      OrbitRadius(Comparator.comparingInt(model.Planet::getOrbitRadius), Comparator.comparingInt(model.Moon::getOrbitRadius));

      private final Comparator<model.Planet> planetComparator;
      private final Comparator<model.Moon> moonComparator;

      SortCriteria(Comparator<model.Planet> planetComparator, Comparator<model.Moon> moonComparator) {
          this.planetComparator = planetComparator;
          this.moonComparator = moonComparator;
      }

      private Comparator<Planet> getPlanetComparator() {
          return planetComparator;
      }

      private Comparator<Moon> getComparatorForMoons() {
          return moonComparator;
      }
    }

    /**
     * For getting user input about sorting.
     *
     * @return Enum.
     */
    private SortCriteria chooseSortCriteria() {
      ListMenu menuChoice = view.showListMenu();
      switch (menuChoice) {
          case Size:
              return SortCriteria.Size;
          case Orbit:
              return SortCriteria.OrbitRadius;
          default:
              return null;
      }
    }

    /**
     * Sorts planets and moons.
     *
     * @param sortCriteria Enum.
     */
    private void sortPlanetsAndMoons(SortCriteria sortCriteria) {
      List<model.Planet> planets = currentSolarSystem.getPlanets();
      Comparator<Planet> planetComparator = sortCriteria.getPlanetComparator();
      planets.sort(planetComparator);
      for (model.Planet planet : planets) {
        sortMoons(sortCriteria, planet);
      }
    }

    /**
     * Sorts moons.
     *
     * @param sortCriteria Enum
     * @param planet Object.
     */
    private void sortMoons(SortCriteria sortCriteria, model.Planet planet) {
      List<model.Moon> moons = planet.getMoons();
      Comparator<Moon> moonComparator = sortCriteria.getComparatorForMoons();
      moons.sort(moonComparator);
    }

    /**
     * Creates a new object of Solarsystem.
     */
    private void createSolarSystem() {
      model.SolarSystem newSolarSystem = view.createSolarSystem();
      solarSystemList.add(newSolarSystem);
    }

    private void deleteSolarSystem(model.SolarSystem solarSystem) {
      this.solarSystemList.remove(solarSystem);
    }

    /**
     * Deletes an object.
     *
     * @param solarSystem Solarsystem object.
     * @return Boolean if the entire system is deleted or not.
     */
    private Boolean deleteMemberOfSolarSystem(model.SolarSystem solarSystem) {
      String memberName = view.deleteMember();
      Boolean centralStarRemoved = false;
      if(solarSystem.getCentralStar().getName().equalsIgnoreCase(memberName)) {
        deleteSolarSystem(solarSystem);
        centralStarRemoved = true;
        view.showMessage("Entire solarsystem removed.");
        return centralStarRemoved;
      } else {
        for (model.Planet planet : solarSystem.getPlanets()) {
          if (planet.getName().equalsIgnoreCase(memberName)) {
              solarSystem.deletePlanet(planet);
              view.showMessage(memberName +" removed.");
              return centralStarRemoved;
          } else {
              for (model.Moon moon : planet.getMoons()) {
                  if (moon.getName().equalsIgnoreCase(memberName)) {
                      planet.deleteMoon(moon);
                      view.showMessage(memberName +" removed.");
                      return centralStarRemoved;
                  }
              }
            }
          }
      }
      view.showMessage("Could not find member with that name.");
      return false;
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
      model.Sun sun = currentSolarSystem.getCentralStar();
      currentSolarSystem.addPlanet(view.createPlanet(sun.getRadius()));
    }

    /**
     * Creates a moon.
     */
    private void createMoon() {
      model.Planet planet = view.selectPlanet(currentSolarSystem.getPlanets());
      if(planet != null) {
        planet.addMoon(view.createMoon(planet.getRadius()));
      }
    }
}

