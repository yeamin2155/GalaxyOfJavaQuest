package finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Starship playerShip;
    private List<Planet> planets;
    private Planet currentPlanet;
    private Scanner scanner;
    private Random random;

    // Constructor
    public GameEngine() {
        scanner = new Scanner(System.in);
        random = new Random();
        planets = new ArrayList<>();
        initializeGame();
    }

    private void initializeGame() {
        // Create planets
        generatePlanets(5); // Generate 5 random planets
        
        // Create player's starship
        System.out.println("\n=== Welcome to Galaxy of JavaQuest! ===");
        System.out.print("Enter your starship's name: ");
        String shipName = scanner.nextLine();
        playerShip = new Starship(shipName, 1000, 50);
    }

    private void generatePlanets(int count) {
        PlanetType[] types = PlanetType.values();
        for (int i = 0; i < count; i++) {
            PlanetType randomType = types[random.nextInt(types.length)];
            Planet planet = new Planet("Planet-" + (i + 1), randomType);
            planets.add(planet);
        }
    }

    public void play() {
        boolean gameRunning = true;
        while (gameRunning) {
            displayMainMenu();
            int choice = getPlayerChoice(1, 6);
            
            try {
                switch (choice) {
                    case 1: explorePlanet(); break;
                    case 2: manageResources(); break;
                    case 3: manageCrew(); break;
                    case 4: encounterAliens(); break;
                    case 5: displayStatus(); break;
                    case 6: 
                        gameRunning = false;
                        System.out.println("\nThank you for playing Galaxy of JavaQuest!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== Galaxy of JavaQuest - Main Menu ===");
        System.out.println("1. Explore Planet");
        System.out.println("2. Manage Resources");
        System.out.println("3. Manage Crew");
        System.out.println("4. Interact with Aliens");
        System.out.println("5. Display Status");
        System.out.println("6. Exit Game");
        System.out.print("Enter your choice (1-6): ");
    }

    private int getPlayerChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private void explorePlanet() {
        if (planets.isEmpty()) {
            System.out.println("No planets available to explore!");
            return;
        }

        System.out.println("\n=== Available Planets ===");
        for (int i = 0; i < planets.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, planets.get(i).getDescription());
        }

        System.out.print("\nChoose a planet to explore (1-" + planets.size() + "): ");
        int choice = getPlayerChoice(1, planets.size());
        currentPlanet = planets.get(choice - 1);
        
        if (!currentPlanet.isExplored()) {
            currentPlanet.explore();
            System.out.println("\nExploring " + currentPlanet.getName() + "...");
            System.out.println("Resources discovered:");
            for (Resource resource : currentPlanet.getResources()) {
                System.out.println("- " + resource.toString());
            }
        } else {
            System.out.println("\nThis planet has already been explored.");
        }
    }

    private void manageResources() {
        if (currentPlanet == null || !currentPlanet.isExplored()) {
            System.out.println("\nNo explored planet selected. Please explore a planet first.");
            return;
        }

        System.out.println("\n=== Resource Management ===");
        List<Resource> planetResources = currentPlanet.getResources();
        
        if (planetResources.isEmpty()) {
            System.out.println("No resources available on this planet.");
            return;
        }

        System.out.println("Available resources on " + currentPlanet.getName() + ":");
        for (int i = 0; i < planetResources.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, planetResources.get(i));
        }
        
        System.out.print("Choose a resource to collect (1-" + planetResources.size() + "): ");
        int choice = getPlayerChoice(1, planetResources.size());
        
        try {
            Resource selectedResource = planetResources.get(choice - 1);
            playerShip.addResource(selectedResource);
            System.out.println("Resource collected successfully!");
        } catch (Exception e) {
            System.out.println("Failed to collect resource: " + e.getMessage());
        }
    }

    private void manageCrew() {
        while (true) {
            System.out.println("\n=== Crew Management ===");
            System.out.println("1. Add Crew Member");
            System.out.println("2. View Crew");
            System.out.println("3. Remove Crew Member");
            System.out.println("4. Back to Main Menu");
            
            int choice = getPlayerChoice(1, 4);
            
            switch (choice) {
                case 1: addCrewMember(); break;
                case 2: viewCrew(); break;
                case 3: removeCrewMember(); break;
                case 4: return;
            }
        }
    }

    private void addCrewMember() {
        System.out.print("Enter crew member name: ");
        String name = scanner.nextLine();
        
        System.out.println("\nChoose role:");
        CrewRole[] roles = CrewRole.values();
        for (int i = 0; i < roles.length; i++) {
            System.out.printf("%d. %s\n", i + 1, roles[i]);
        }
        
        int roleChoice = getPlayerChoice(1, roles.length);
        CrewMember newMember = new CrewMember(name, roles[roleChoice - 1]);
        
        try {
            playerShip.addCrewMember(newMember);
            System.out.println("Crew member added successfully!");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewCrew() {
        List<CrewMember> crew = playerShip.getCrew();
        if (crew.isEmpty()) {
            System.out.println("No crew members aboard.");
            return;
        }
        
        System.out.println("\nCurrent Crew:");
        for (CrewMember member : crew) {
            System.out.println("- " + member.toString());
        }
    }

    private void removeCrewMember() {
        List<CrewMember> crew = playerShip.getCrew();
        if (crew.isEmpty()) {
            System.out.println("No crew members to remove!");
            return;
        }

        System.out.println("\nSelect crew member to remove:");
        for (int i = 0; i < crew.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, crew.get(i));
        }
        
        int choice = getPlayerChoice(1, crew.size());
        playerShip.removeCrewMember(crew.get(choice - 1));
        System.out.println("Crew member removed successfully!");
    }

    private void encounterAliens() {
        if (currentPlanet == null || !currentPlanet.isExplored()) {
            System.out.println("\nNo explored planet selected. Please explore a planet first.");
            return;
        }

        List<Alien> aliens = currentPlanet.getInhabitants();
        if (aliens.isEmpty()) {
            System.out.println("\nNo aliens found on this planet.");
            return;
        }

        System.out.println("\n=== Alien Encounters ===");
        for (int i = 0; i < aliens.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, aliens.get(i).getDescription());
        }

        System.out.print("Choose an alien to interact with (1-" + aliens.size() + "): ");
        int alienChoice = getPlayerChoice(1, aliens.size());
        Alien selectedAlien = aliens.get(alienChoice - 1);

        System.out.println("\nChoose interaction:");
        System.out.println("1. Negotiate");
        System.out.println("2. Trade");
        System.out.println("3. Fight");
        
        int actionChoice = getPlayerChoice(1, 3);
        List<CrewMember> crew = playerShip.getCrew();
        
        if (crew.isEmpty()) {
            System.out.println("You need crew members to interact with aliens!");
            return;
        }

        CrewMember selectedCrew = crew.get(0); // For simplicity, use first crew member
        
        boolean success = false;
        switch (actionChoice) {
            case 1:
                success = selectedAlien.negotiate(selectedCrew);
                System.out.println(success ? "Negotiation successful!" : "Negotiation failed.");
                break;
            case 2:
                success = selectedAlien.trade(null); // Simplified trade
                System.out.println(success ? "Trade successful!" : "Trade failed.");
                break;
            case 3:
                success = selectedAlien.fight(selectedCrew);
                System.out.println(success ? "Combat successful!" : "Combat failed.");
                break;
        }
    }

    private void displayStatus() {
        System.out.println("\n=== Status Report ===");
        System.out.println(playerShip.toString());
        
        System.out.println("\nCrew Members:");
        for (CrewMember member : playerShip.getCrew()) {
            System.out.println("- " + member.toString());
        }

        if (currentPlanet != null) {
            System.out.println("\nCurrent Planet:");
            System.out.println(currentPlanet.getDescription());
        }
    }

    // Getter for testing purposes
    public Starship getPlayerShip() {
        return playerShip;
    }
}
