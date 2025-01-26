package GalaxyOfJavaQuest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the starship in the Galaxy of JavaQuest game.
 */
public class Starship {
    private String name;
    private int fuelLevel;
    private int maxFuelCapacity;
    private List<CrewMember> crew;
    private Map<ResourceType, Integer> cargo;
    private int maxCargoCapacity;

    public Starship(String name) {
        this.name = name;
        this.fuelLevel = 100;
        this.maxFuelCapacity = 200;
        this.crew = new ArrayList<>();
        this.cargo = new HashMap<>();
        this.maxCargoCapacity = 500;
    }

    public void addCrewMember(CrewMember member) {
        if (crew.size() < 4) {
            crew.add(member);
        } else {
            throw new IllegalStateException("Maximum crew capacity reached");
        }
    }

    public List<CrewMember> getCrew() {
        return new ArrayList<>(crew);
    }

    public void addResource(ResourceType type, int amount) {
        int currentAmount = cargo.getOrDefault(type, 0);
        if (getTotalCargoLoad() + amount <= maxCargoCapacity) {
            cargo.put(type, currentAmount + amount);
        } else {
            throw new IllegalStateException("Cargo capacity exceeded");
        }
    }

    public int getResourceQuantity(ResourceType type) {
        return cargo.getOrDefault(type, 0);
    }

    public int getTotalCargoLoad() {
        return cargo.values().stream().mapToInt(Integer::intValue).sum();
    }

    public void consumeFuel(int amount) {
        if (fuelLevel - amount >= 0) {
            fuelLevel -= amount;
        } else {
            throw new IllegalStateException("Insufficient fuel");
        }
    }

    public void refuel(int amount) {
        fuelLevel = Math.min(fuelLevel + amount, maxFuelCapacity);
    }

    public void upgradeCargoCapacity(int additionalCapacity) {
        maxCargoCapacity += additionalCapacity;
    }

    public String getName() {
        return name;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public int getMaxCargoCapacity() {
        return maxCargoCapacity;
    }

    @Override
    public String toString() {
        return "Starship: " + name + 
               " (Fuel: " + fuelLevel + "/" + maxFuelCapacity + 
               ", Crew: " + crew.size() + 
               ", Cargo: " + getTotalCargoLoad() + "/" + maxCargoCapacity + ")";
    }
}
