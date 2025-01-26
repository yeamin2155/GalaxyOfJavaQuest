package finalproject;

public class GalaxyOfJavaQuestGame {

    public static void main(String[] args) {
        // Create crew members
        CrewMember captain = createCrewMember("Kirk", CrewRole.CAPTAIN);
        CrewMember engineer = createCrewMember("Scotty", CrewRole.ENGINEER);

        // Print crew details
        System.out.println(captain);
        System.out.println(engineer);

        // Create some resources
        Resource fuel = createResource("Starship Fuel", ResourceType.FUEL, 100, 50.0);
        System.out.println(fuel);
    }

    /**
     * Create a crew member with the given name and role.
     */
    private static CrewMember createCrewMember(String name, CrewRole role) {
        return new CrewMember(name, role);
    }

    /**
     * Create a resource with the given attributes.
     */
    private static Resource createResource(String name, ResourceType type, int quantity, double value) {
        return new Resource(name, type, quantity, value);
    }
}
