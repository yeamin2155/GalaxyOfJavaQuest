package finalproject;

/**
 * Enum representing different types of resources in the game.
 */
public enum ResourceType {
    FUEL("Starship Fuel"),
    MINERALS("Raw Minerals"),
    TECHNOLOGY("Advanced Technology"),
    FOOD("Crew Provisions"),
    MEDICINAL("Medical Supplies"),
    RARE_ARTIFACT("Rare Space Artifact");

    private final String description;

    ResourceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
