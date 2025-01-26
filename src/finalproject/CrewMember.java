//Implemented by Dayan Kijege and Yeamin
//Cosc242501-Asg9
//Professor McCurry 
package finalproject;

/**
 * Represents a crew member in the Galaxy of JavaQuest game.
 */
public class CrewMember implements CrewAbility {
    private String name;
    private CrewRole role;
    private int strength;
    private int intelligence;
    private int agility;
    private int technicalSkill;

    public CrewMember(String name, CrewRole role) {
        this.name = name;
        this.role = role;
        initializeSkills();
    }

    private void initializeSkills() {
        switch(role) {
            case CAPTAIN:
                strength = 80;
                intelligence = 90;
                agility = 70;
                technicalSkill = 60;
                break;
            case ENGINEER:
                strength = 60;
                intelligence = 85;
                agility = 50;
                technicalSkill = 95;
                break;
            case SCIENTIST:
                strength = 50;
                intelligence = 95;
                agility = 60;
                technicalSkill = 85;
                break;
            case SECURITY_OFFICER:
                strength = 90;
                intelligence = 70;
                agility = 85;
                technicalSkill = 55;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public CrewRole getRole() {
        return role;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getTechnicalSkill() {
        return technicalSkill;
    }

    @Override
    public boolean performSpecialAction(CrewMember member) {
        switch(member.getRole()) {
            case CAPTAIN:
                return diplomacy();
            case ENGINEER:
                return repair();
            case SCIENTIST:
                return analyze();
            case SECURITY_OFFICER:
                return combat();
            default:
                return false;
        }
    }

    private boolean diplomacy() {
        System.out.println(name + " attempts diplomatic negotiation.");
        return Math.random() > 0.3;
    }

    private boolean repair() {
        System.out.println(name + " is performing equipment repairs.");
        return Math.random() > 0.2;
    }

    private boolean analyze() {
        System.out.println(name + " is conducting scientific analysis.");
        return Math.random() > 0.1;
    }

    private boolean combat() {
        System.out.println(name + " is preparing for potential combat.");
        return Math.random() > 0.4;
    }

    @Override
    public String toString() {
        return "Crew Member: " + name + 
               " (Role: " + role + 
               ", Strength: " + strength + 
               ", Intelligence: " + intelligence + ")";
    }
}
