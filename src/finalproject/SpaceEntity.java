package GalaxyOfJavaQuest;

/**
 * Abstract base class for all entities in the Galaxy of JavaQuest game.
 */
public abstract class SpaceEntity {
    private String name;
    private int uniqueId;

    // Constructor
    public SpaceEntity(String name) {
        this.name = name;
        this.uniqueId = generateUniqueId();
    }

    private int generateUniqueId() {
        return (int) (Math.random() * 100000);
    }

    public String getName() {
        return name;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public abstract String getDescription();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpaceEntity that = (SpaceEntity) obj;
        return uniqueId == that.uniqueId && name.equals(that.name);
    }

    @Override
    public String toString() {
        return "Entity: " + name + " (ID: " + uniqueId + ")";
    }
}
