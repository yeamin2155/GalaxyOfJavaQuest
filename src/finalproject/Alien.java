
package finalproject;

/**
 * Represents an alien species in the Galaxy of JavaQuest game.
 * Extends SpaceEntity and implements Encounter interface.
 */
public class Alien extends SpaceEntity implements Encounter {
    private AlienType type;
    private boolean isHostile;
    private String homeWorld;

    // Constructor
    public Alien(String name, AlienType type, boolean isHostile, String homeWorld) {
        super(name);
        this.type = type;
        this.isHostile = isHostile;
        this.homeWorld = homeWorld;
    }

    // Getters
    public AlienType getType() {
        return type;
    }

    public boolean isHostile() {
        return isHostile;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    // Implement SpaceEntity abstract method
    @Override
    public String getDescription() {
        return "Alien: " + getName() + 
               " (Type: " + type + 
               ", Hostility: " + (isHostile ? "Hostile" : "Peaceful") + 
               ", Homeworld: " + homeWorld + ")";
    }

    // Encounter interface methods
    @Override
    public boolean negotiate(CrewMember diplomat) {
        System.out.println("Attempting to negotiate with " + getName());
        return !isHostile && Math.random() > 0.2; // 80% success if not hostile
    }

    @Override
    public boolean trade(Resource resource) {
        System.out.println("Trading with " + getName());
        return !isHostile && Math.random() > 0.3; // 70% success if not hostile
    }

    @Override
    public boolean fight(CrewMember securityOfficer) {
        System.out.println("Engaging in combat with " + getName());
        return isHostile && Math.random() > 0.5; // 50% success if hostile
    }
}
