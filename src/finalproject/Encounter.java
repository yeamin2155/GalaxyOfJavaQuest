//Implemented by Dayan Kijege and Yeamin
//Cosc242501-Asg9
//Professor McCurry 
package finalproject;

/**
 * Interface defining possible interactions with alien species.
 */
public interface Encounter {
    boolean negotiate(CrewMember diplomat);
    boolean trade(Resource resource);
    boolean fight(CrewMember securityOfficer);
}
