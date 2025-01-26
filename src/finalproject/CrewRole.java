package GalaxyOfJavaQuest;

/**
 * Enum representing different crew roles in the game.
 */
public enum CrewRole {
    CAPTAIN("Leadership and Strategic Decision Making"),
    ENGINEER("Technical Repairs and System Maintenance"),
    SCIENTIST("Research and Scientific Analysis"),
    SECURITY_OFFICER("Combat and Ship Protection");

    private final String description;

    CrewRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
