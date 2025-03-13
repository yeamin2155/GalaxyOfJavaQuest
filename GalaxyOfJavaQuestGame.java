public class GalaxyOfJavaQuestGame {
    public GalaxyOfJavaQuestGame() {
    }

    public static void main(String[] args) {
        try {
            // Create crew members with validation
            CrewMember captain = createCrewMember("Kirk", CrewRole.CAPTAIN);
            CrewMember engineer = createCrewMember("Scotty", CrewRole.ENGINEER);
            
            // Print crew information
            System.out.println("Crew Information:");
            System.out.println("-----------------");
            System.out.println(captain);
            System.out.println(engineer);
            
            // Create and validate resource
            Resource fuel = createResource("Starship Fuel", ResourceType.FUEL, 100, 50.0);
            System.out.println("\nResource Information:");
            System.out.println("-------------------");
            System.out.println(fuel);
            
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating game elements: " + e.getMessage());
        }
    }

    private static CrewMember createCrewMember(String name, CrewRole role) {
        // Add validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Crew member name cannot be empty");
        }
        if (role == null) {
            throw new IllegalArgumentException("Crew role cannot be null");
        }
        return new CrewMember(name, role);
    }

    private static Resource createResource(String name, ResourceType type, int quantity, double value) {
        // Add validation
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Resource name cannot be empty");
        }
        if (type == null) {
            throw new IllegalArgumentException("Resource type cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        return new Resource(name, type, quantity, value);
    }
} 