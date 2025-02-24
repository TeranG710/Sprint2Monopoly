package Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyTest {
    private Property property;
    private ColorGroup colorGroup;
    private Player owner;
    private Player otherPlayer;

    @Before
    public void setUp() {
        colorGroup = new ColorGroup("DARK_BLUE", 2);
        property = new Property(
            "Boardwalk",
            39,
            400,
            50,
            new int[]{200, 600, 1400, 1700},
            2000,
            200,
            PropertyColor.DARK_BLUE,
            colorGroup
        );
        colorGroup.addProperty(property);
        owner = new Player("TestOwner", 2000);
        otherPlayer = new Player("TestPlayer", 2000);
    }

    @Test
    public void testConstructor() {
        assertEquals("Boardwalk", property.getName());
        assertEquals(39, property.getPosition());
        assertEquals(400, property.getPurchasePrice());
        assertNull(property.getOwner());
        assertFalse(property.isMortgaged());
        assertEquals(0, property.getNumHouses());
        assertFalse(property.hasHotel());
    }

    @Test
    public void testRentCalculationNoHouses() {
        property.setOwner(owner);
        assertEquals(50, property.calculateRent()); // Base rent
    }

    @Test
    public void testRentCalculationWithMonopoly() {
        Property parkPlace = new Property(
            "Park Place",
            37,
            350,
            35,
            new int[]{175, 500, 1100, 1300},
            1500,
            175,
            PropertyColor.DARK_BLUE,
            colorGroup
        );
        colorGroup.addProperty(parkPlace);
        
        property.setOwner(owner);
        parkPlace.setOwner(owner);
        
        assertEquals(100, property.calculateRent()); // Double rent with monopoly
    }

    @Test
    public void testHouseAddition() {
        Property parkPlace = new Property(
            "Park Place",
            37,
            350,
            35,
            new int[]{175, 500, 1100, 1300},
            1500,
            175,
            PropertyColor.DARK_BLUE,
            colorGroup
        );
        colorGroup.addProperty(parkPlace);
        
        property.setOwner(owner);
        parkPlace.setOwner(owner);
        
        assertTrue(property.addHouse());
        assertEquals(1, property.getNumHouses());
        assertEquals(200, property.calculateRent()); // Rent with one house
    }

    @Test
    public void testHotelAddition() {
        property.setOwner(owner);
        // Add 4 houses first
        for (int i = 0; i < 4; i++) {
            property.addHouse();
        }
        assertTrue(property.addHotel());
        assertTrue(property.hasHotel());
        assertEquals(0, property.getNumHouses());
        assertEquals(2000, property.calculateRent()); // Hotel rent
    }

    @Test
    public void testMortgage() {
        property.setOwner(owner);
        int initialMoney = owner.getMoney();
        assertTrue(property.mortgage());
        assertTrue(property.isMortgaged());
        assertEquals(initialMoney + 200, owner.getMoney()); // Mortgage value added
        assertEquals(0, property.calculateRent()); // No rent while mortgaged
    }

    @Test
    public void testUnmortgage() {
        property.setOwner(owner);
        property.mortgage();
        int moneyBeforeUnmortgage = owner.getMoney();
        assertTrue(property.unmortgage());
        assertFalse(property.isMortgaged());
        assertEquals(moneyBeforeUnmortgage - 220, owner.getMoney()); // Mortgage value + 10% interest
    }

    @Test
    public void testCannotAddHousesWhileMortgaged() {
        property.setOwner(owner);
        property.mortgage();
        assertFalse(property.addHouse());
    }

    @Test
    public void testOnLanding() {
        property.setOwner(owner);
        int initialOtherPlayerMoney = otherPlayer.getMoney();
        int initialOwnerMoney = owner.getMoney();
        property.onLanding(otherPlayer);
        assertTrue(otherPlayer.getMoney() < initialOtherPlayerMoney); // Player paid rent
        assertTrue(owner.getMoney() > initialOwnerMoney); // Owner received rent
    }
}