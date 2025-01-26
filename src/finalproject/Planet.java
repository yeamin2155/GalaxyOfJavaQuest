
package finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Planet extends SpaceEntity {
    private PlanetType type;
    private List<Resource> resources;
    private List<Alien> inhabitants;
    private boolean explored;
    private Random random;

    // Constructor
    public Planet(String name, PlanetType type) {
        super(name);
        this.type = type;
        this.resources = new ArrayList<>();
        this.inhabitants = new ArrayList<>();
        this.explored = false;
        this.random = new Random();
        generateResources();
        generateInhabitants();
    }

    private void generateResources() {
        // Generate resources based on planet type
        switch (type) {
            case TERRESTRIAL:
                addRandomResource(ResourceType.MINERALS, "Crystal Formation");
                addRandomResource(ResourceType.FUEL, "Natural Gas");
                break;
            case GAS_GIANT:
                addRandomResource(ResourceType.FUEL, "Hydrogen Deposits");
                addRandomResource(ResourceType.TECHNOLOGY, "Atmospheric Processor");
                break;
            case ICE_WORLD:
                addRandomResource(ResourceType.MINERALS, "Frozen Minerals");
                addRandomResource(ResourceType.RARE_ARTIFACT, "Ancient Ice Artifact");
                break;
            case DESERT_WORLD:
                addRandomResource(ResourceType.MINERALS, "Desert Minerals");
                addRandomResource(ResourceType.TECHNOLOGY, "Ancient Technology");
                break;
        }
    }

    private void addRandomResource(ResourceType type, String baseName) {
        int quantity = random.nextInt(50) + 10;  // 10 to 59 units
        double value = random.nextDouble() * 100 + 50;  // 50 to 149.99 value
        resources.add(new Resource(baseName, type, quantity, value));
    }

    private void generateInhabitants() {
        // 50% chance to have inhabitants
        if (random.nextBoolean()) {
            AlienType type = AlienType.values()[random.nextInt(AlienType.values().length)];
            boolean isHostile = random.nextBoolean();
            String homeWorld = getName(); // Use planet name as home world
            inhabitants.add(new Alien("Native of " + getName(), type, isHostile, homeWorld));
        }
    }

    // Exploration methods
    public void explore() {
        explored = true;
    }

    // Getters
    public PlanetType getType() {
        return type;
    }

    public List<Resource> getResources() {
        return new ArrayList<>(resources);  // Return a copy to prevent modification
    }

    public List<Alien> getInhabitants() {
        return new ArrayList<>(inhabitants);  // Return a copy to prevent modification
    }

    public boolean isExplored() {
        return explored;
    }

    // Resource collection method
    public Resource collectResource(int index) {
        if (index >= 0 && index < resources.size()) {
            return resources.get(index);
        }
        return null;
    }

    @Override
    public String getDescription() {
        String explorationStatus = explored ? "Explored" : "Unexplored";
        return String.format("Planet %s - Type: %s, Status: %s, Resources: %d, Inhabitants: %d", 
            getName(), type, explorationStatus, resources.size(), inhabitants.size());
    }

    // toString method
    @Override
    public String toString() {
        return getDescription();
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        
        Planet other = (Planet) obj;
        return type == other.type && explored == other.explored;
    }

    // hashCode method
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (explored ? 1 : 0);
        return result;
    }
}