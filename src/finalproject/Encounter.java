package finalproject;

/**
 * Interface defining possible interactions with alien species.
 */
public interface Encounter {
    boolean negotiate(CrewMember diplomat);
    boolean trade(Resource resource);
    boolean fight(CrewMember securityOfficer);
}
