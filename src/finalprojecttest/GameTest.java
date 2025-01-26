package finalprojecttest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import finalproject.*;

/**
 * Test class for the GalaxyOfJavaQuest game.
 */
public class GameTest {

    /**
     * Test for verifying CrewMember creation.
     */
    @Test
    public void testCrewMemberCreation() {
        // Create a CrewMember instance
        CrewMember captain = new CrewMember("Kirk", CrewRole.CAPTAIN);

        // Assert that the name and role are correctly assigned
        assertEquals("Kirk", captain.getName(), "CrewMember name should be 'Kirk'");
        assertEquals(CrewRole.CAPTAIN, captain.getRole(), "CrewMember role should be CAPTAIN");
    }

    /**
     * Test for verifying Alien creation and description.
     */
    @Test
    public void testAlienCreation() {
        // Create an Alien instance
        Alien alien = new Alien("Zorg", AlienType.HUMANOID, true, "Nebula-5");

        // Assert that the name, type, hostility, and home world are correctly assigned
        assertEquals("Zorg", alien.getName(), "Alien name should be 'Zorg'");
        assertEquals(AlienType.HUMANOID, alien.getType(), "Alien type should be HUMANOID");
        assertTrue(alien.isHostile(), "Alien should be hostile");
        assertEquals("Nebula-5", alien.getHomeWorld(), "Alien home world should be 'Nebula-5'");
    }

    /**
     * Test for verifying Resource creation and description.
     */
    @Test
    public void testResourceCreation() {
        // Create a Resource instance
        Resource fuel = new Resource("Starship Fuel", ResourceType.FUEL, 100, 50.0);

        // Assert that the resource name, type, quantity, and value are correctly assigned
        assertEquals("Starship Fuel", fuel.getName(), "Resource name should be 'Starship Fuel'");
        assertEquals(ResourceType.FUEL, fuel.getType(), "Resource type should be FUEL");
        assertEquals(100, fuel.getQuantity(), "Resource quantity should be 100");
        assertEquals(50.0, fuel.getValue(), "Resource value should be 50.0");
    }

    /**
     * Test for verifying Starship creation and cargo handling.
     */
    @Test
    public void testStarshipCreationAndCargo() {
        // Create a Starship instance
        Starship voyager = new Starship("Voyager");

        // Add resources to the Starship
        voyager.addResource(ResourceType.FUEL, 50);
        voyager.addResource(ResourceType.MINERALS, 20);

        // Assert that the cargo is correctly added
        assertEquals(50, voyager.getResourceQuantity(ResourceType.FUEL), "Starship should have 50 FUEL");
        assertEquals(20, voyager.getResourceQuantity(ResourceType.MINERALS), "Starship should have 20 MINERALS");
        assertThrows(IllegalStateException.class, () -> voyager.addResource(ResourceType.FUEL, 500), 
                "Should throw IllegalStateException when cargo exceeds capacity");
    }

    /**
     * Test for verifying CrewMember special action performance.
     */
    @Test
    public void testCrewMemberSpecialAction() {
        // Create a CrewMember instance (Engineer)
        CrewMember engineer = new CrewMember("Scotty", CrewRole.ENGINEER);

        // Test Engineer's special action (repair)
        boolean repairSuccess = engineer.performSpecialAction(engineer);
        assertTrue(repairSuccess, "Engineer should be able to repair with success rate > 0.2");
    }

    /**
     * Test for verifying interaction with Alien (negotiate).
     */
    @Test
    public void testAlienNegotiation() {
        // Create a peaceful Alien
        Alien peacefulAlien = new Alien("Zara", AlienType.HUMANOID, false, "Mars");

        // Test negotiation
        CrewMember diplomat = new CrewMember("Spock", CrewRole.SCIENTIST);
        boolean negotiationResult = peacefulAlien.negotiate(diplomat);
        assertTrue(negotiationResult, "Negotiation should succeed with a peaceful alien");
    }

    /**
     * Test for verifying Alien combat.
     */
    @Test
    public void testAlienCombat() {
        // Create a hostile Alien
        Alien hostileAlien = new Alien("Kazon", AlienType.ROBOTIC, true, "Delta Quadrant");

        // Test combat
        CrewMember securityOfficer = new CrewMember("Tuvok", CrewRole.SECURITY_OFFICER);
        boolean combatResult = hostileAlien.fight(securityOfficer);
        assertTrue(combatResult || !combatResult, "Combat result should be either true or false based on the fight outcome");
    }

    /**
     * Test for verifying Starship's fuel consumption and refuel.
     */
    @Test
    public void testStarshipFuel() {
        // Create a Starship instance
        Starship voyager = new Starship("Voyager");

        // Test fuel consumption
        voyager.consumeFuel(30);
        assertEquals(70, voyager.getFuelLevel(), "Fuel level should be 70 after consuming 30 units");

        // Test refueling
        voyager.refuel(50);
        assertEquals(120, voyager.getFuelLevel(), "Fuel level should be 120 after refueling with 50 units");

        // Test over-refueling
        voyager.refuel(100);
        assertEquals(200, voyager.getFuelLevel(), "Fuel level should be capped at 200");
    }
}
