package GalaxyOfJavaQuest;

/**
 * Enum representing different alien types in the game.
 */
public enum AlienType {
    HUMANOID("Bipedal, human-like species"),
    GASEOUS("Non-corporeal, energy-based lifeform"),
    CRYSTALLINE("Silicon-based intelligent life"),
    ROBOTIC("Artificial intelligence-driven beings"),
    AQUATIC("Water-dwelling intelligent species");

    private final String description;

    AlienType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
