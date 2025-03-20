package Model.Property;

import Model.Board.Banker;
import Model.Board.GameBoard;
import Model.Board.HumanPlayer;
import Model.Exceptions.PlayerNotFoundException;
import Model.Board.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyTest {

    private Property property;
    private ColorGroup colorGroup;
    private Player owner;
    private Player otherPlayer;
    private Banker banker;


    @BeforeEach
    public void setUp() throws PlayerNotFoundException {
        GameBoard board = new GameBoard();
        owner = new HumanPlayer("TestOwner", board);
        otherPlayer = new HumanPlayer("TestPlayer", board);
        Banker banker = new Banker();
        banker.addPlayer(owner);
        banker.addPlayer(otherPlayer);
        colorGroup = new ColorGroup(PropertyColor.DARK_BLUE, 2);
        property = new Property(
                "Boardwalk",
                39,
                400,
                50,
                new int[]{200, 600, 1400, 1700},
                2000,
                200,
                PropertyColor.DARK_BLUE,
                colorGroup);
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
    public void testMortgage() throws PlayerNotFoundException {
        property.setOwner(owner);
        assertTrue(property.mortgage());
        assertTrue(property.isMortgaged());
        assertEquals(0, property.calculateRent());
    }

    @Test
    public void testOnLandingNoOwner() throws PlayerNotFoundException {
        int initialMoney = banker.getBalance(otherPlayer);
        property.onLanding(otherPlayer);
        assertEquals(initialMoney, banker.getBalance(otherPlayer));
    }

    @Test
    public void testOnLandingWithOwner() throws PlayerNotFoundException {
        property.setOwner(owner);
        int initialOtherPlayerMoney = banker.getBalance(otherPlayer);
        int initialOwnerMoney = banker.getBalance(owner);
        property.onLanding(otherPlayer);
        assertTrue(banker.getBalance(otherPlayer) < initialOtherPlayerMoney);
        assertTrue(banker.getBalance(owner)> initialOwnerMoney);
    }
}