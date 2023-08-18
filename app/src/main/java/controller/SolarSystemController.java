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
      boolean running = true;
      while (running) {
        try {
          view.SolarSystemView.Menu action = view.showMenu();
          if (action == null) {
            throw new Exception("Invalid input. Please choose a valid option.");
          }
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
                  break;
              default:
                  System.out.println("Invalid input. Please choose a valid menu option.");
                  break;
          }
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
      }
    }

    /**
     * Menu for individual solarsystem.
     */
    private void solarSystemMenu() {
      Boolean running = true;
      currentSolarSystem = selectSpecificSolarSystem();
      while(currentSolarSystem == null) {
        currentSolarSystem = selectSpecificSolarSystem();
      }
        while (running) {
          try {
            view.SolarSystemView.SolarSystemMenu action = view.showSolarSystemMenu();
            if (action == null) {
              throw new Exception("Invalid input. Please choose a valid menu option.");
            }
            switch (action) {
              case View:
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
                view.showMessage("Invalid input. Please choose a valid menu option.");
                break;
            }
          } catch (Exception e) {
            view.showMessage(e.getMessage());
          }
      }
    }

    /**
     * For getting user input about sorting.
     *
     * @return Enum.
     */
    private SortCriteria chooseSortCriteria() {
      try {
        ListMenu menuChoice = view.showListMenu();
        if (menuChoice == null) {
              throw new Exception("Invalid input. Please choose a valid menu option.");
            }
        switch (menuChoice) {
            case Size:
                return SortCriteria.Size;
            case Orbit:
                return SortCriteria.OrbitRadius;
            default:
                view.showMessage("Invalid input. Please choose a valid menu option.");
                return null;
        }
      } catch (Exception e) {
        view.showMessage(e.getMessage());
      }
      return null;
    }

    /**
     * Menu for adding a member.
     */
    private void addMemberMenu() {
      try {
        view.SolarSystemView.AddMemberMenu action = view.showAddMemberMenu();
        if (action == null) {
              throw new Exception("Invalid input. Please choose a valid menu option.");
            }
        switch (action) {
          case Planet:
            createPlanet();
            break;
          case Moon:
            createMoon();
            break;
          default:
            view.showMessage("Invalid input. Please choose a valid menu option.");
            break;
        }
      } catch (Exception e) {
        view.showMessage(e.getMessage());
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
     * Creates a planet.
     *
     * @return Planet object.
     */
    private void createPlanet() {
      try {
        model.Sun sun = currentSolarSystem.getCentralStar();
        currentSolarSystem.addPlanet(view.createPlanet(sun.getRadius()));
      } catch (Exception e) {
        view.showMessage("Error creating planet.");
      }
      
    }

    /**
     * Creates a moon.
     */
    private void createMoon() {
      try {
        model.Planet planet = view.selectPlanet(currentSolarSystem.getPlanets());
        if(planet != null) {
          planet.addMoon(view.createMoon(planet.getRadius()));
        }
      } catch (Exception e) {
        view.showMessage("Error creating moon.");
      }
    }

    /**
     * Deletes solarsystem from controller.
     *
     * @param solarSystem Object.
     */
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
      try {
        if(solarSystemList.size() != 0) {
          model.SolarSystem selectedSolarSystem = view.selectSolarSystem(solarSystemList);
          if(selectedSolarSystem != null) {
            return selectedSolarSystem;
          } else {
            throw new Exception("Please select an available solarsystem.");
          }
        } else {
          view.showMessage("No available solarsystems.");
        }
      } catch (Exception e) {
        view.showMessage(e.getMessage());
      }
      return null;
    }

    /**
     * Displays details of one solarsystem.
     */
    private void displaySolarSystemDetails(model.SolarSystem solarSystem) {
      SortCriteria criteria = chooseSortCriteria();
      List<model.Planet> planets = getSortedPlanetsAndMoons(criteria);
      view.displaySolarSystemDetails(solarSystem);
      if(planets.size() > 0) {
        for (model.Planet planet : planets) {
          List<model.Moon> moons = sortMoons(criteria, planet);
          view.displayPlanetAndMoons(planet, moons);
        } 
      } else {
        view.showMessage("No planets.");
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
     * Sorts planets and moons.
     *
     * @param sortCriteria Enum.
     */
    private List<Planet> getSortedPlanetsAndMoons(SortCriteria sortCriteria) {
      List<model.Planet> planets = currentSolarSystem.getPlanets();
      Comparator<Planet> planetComparator = sortCriteria.getPlanetComparator();
      planets.sort(planetComparator);
      for (model.Planet planet : planets) {
        sortMoons(sortCriteria, planet);
      }
      return planets;
    }

    /**
     * Sorts moons.
     *
     * @param sortCriteria Enum
     * @param planet Object.
     */
    private List<model.Moon> sortMoons(SortCriteria sortCriteria, model.Planet planet) {
      List<model.Moon> moons = planet.getMoons();
      Comparator<Moon> moonComparator = sortCriteria.getComparatorForMoons();
      moons.sort(moonComparator);
      return moons;
    }
}

