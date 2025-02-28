package Model.Property;

import Model.Board.Board;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {
    private Property property;
    private ColorGroup colorGroup;
    private Player owner;
    private Player otherPlayer;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        owner = new Player("TestOwner", board);
        otherPlayer = new Player("TestPlayer", board);

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
        assertEquals(50, property.calculateRent());
    }

    @Test
    public void testMortgage() {
        property.setOwner(owner);
        assertTrue(property.mortgage());
        assertTrue(property.isMortgaged());
        assertEquals(0, property.calculateRent());
    }

    @Test
    public void testOnLandingNoOwner() {
        int initialMoney = otherPlayer.getMoney();
        property.onLanding(otherPlayer);
        assertEquals(initialMoney, otherPlayer.getMoney());
    }

    @Test
    public void testOnLandingWithOwner() {
        property.setOwner(owner);
        int initialOtherPlayerMoney = otherPlayer.getMoney();
        int initialOwnerMoney = owner.getMoney();
        property.onLanding(otherPlayer);
        assertTrue(otherPlayer.getMoney() < initialOtherPlayerMoney);
        assertTrue(owner.getMoney() > initialOwnerMoney);
    }
}