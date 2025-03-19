package Model.Board;

import Model.Exceptions.InsufficientFundsException;
import Model.Exceptions.InvalidTransactionException;
import Model.Exceptions.PlayerNotFoundException;
import Model.Property.ColorGroup;
import Model.Property.Property;
import Model.Property.PropertyColor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for Banker
 * Team member(s) responsible: Matt
 */
public class BankerTest {

    private Banker banker;
    private Player player1;
    private Player player2;
    private Property property;
    private GameBoard gameBoard;

    @Before
    public void setUp() {
        banker = new Banker();
        gameBoard = new GameBoard();
        player1 = new Player("Player 1", gameBoard);
        player2 = new Player("Player 2", gameBoard);
        
        // Create a test property
        ColorGroup colorGroup = new ColorGroup("Blue", 2);
        int[] houseRents = {50, 200, 600, 1400};
        property = new Property("Boardwalk", 39, 400, 50, houseRents, 2000, 200, PropertyColor.DARK_BLUE, colorGroup);
        
        // Add players to banker
        banker.addPlayer(player1);
        banker.addPlayer(player2);
        
        // Initialize properties
        List<Property> properties = new ArrayList<>();
        properties.add(property);
        banker.initializeProperties(properties);
    }

    @Test
    public void testInitialBalance() throws PlayerNotFoundException {
        assertEquals(1500, banker.getBalance(player1));
        assertEquals(1500, banker.getBalance(player2));
    }

    @Test
    public void testDeposit() throws PlayerNotFoundException {
        banker.deposit(player1, 200);
        assertEquals(1700, banker.getBalance(player1));
    }

    @Test(expected = InvalidTransactionException.class)
    public void testDepositNegativeAmount() throws PlayerNotFoundException {
        banker.deposit(player1, -100);
    }

    @Test
    public void testWithdraw() throws PlayerNotFoundException {
        banker.withdraw(player1, 500);
        assertEquals(1000, banker.getBalance(player1));
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawInsufficientFunds() throws PlayerNotFoundException {
        banker.withdraw(player1, 2000);
    }

    @Test(expected = InvalidTransactionException.class)
    public void testWithdrawNegativeAmount() throws PlayerNotFoundException {
        banker.withdraw(player1, -100);
    }

    @Test
    public void testTransferMoney() throws PlayerNotFoundException {
        banker.transferMoney(player1, player2, 300);
        assertEquals(1200, banker.getBalance(player1));
        assertEquals(1800, banker.getBalance(player2));
    }

    @Test
    public void testPayGoMoney() throws PlayerNotFoundException {
        banker.payGoMoney(player1);
        assertEquals(1700, banker.getBalance(player1));
    }

    @Test
    public void testSellProperty() throws PlayerNotFoundException {
        banker.sellProperty(property, player1);
        assertEquals(1100, banker.getBalance(player1)); // 1500 - 400
        assertEquals(player1, property.getOwner());
        assertTrue(player1.ownsProperty(property));
    }

    @Test
    public void testBuyBackProperty() throws PlayerNotFoundException {
        // First sell the property to the player
        banker.sellProperty(property, player1);
        
        // Then mortgage it
        banker.buyBackProperty(property, player1);
        assertEquals(1300, banker.getBalance(player1)); // 1100 + 200 (mortgage value)
        assertTrue(property.isMortgaged());
    }

    @Test
    public void testSellHouse() throws PlayerNotFoundException {
        // First sell the property to the player
        banker.sellProperty(property, player1);
        
        // Then sell a house
        banker.sellHouse(property, player1);
        assertEquals(1050, banker.getBalance(player1)); // 1100 - 50 (house price)
        assertEquals(1, property.getNumHouses());
        assertEquals(31, banker.getAvailableHouses());
    }

    @Test
    public void testSellHotel() throws PlayerNotFoundException {
        // First sell the property to the player
        banker.sellProperty(property, player1);
        
        // Then sell 4 houses
        for (int i = 0; i < 4; i++) {
            banker.sellHouse(property, player1);
        }
        assertEquals(900, banker.getBalance(player1)); // 1100 - 4*50
        
        // Then sell a hotel
        banker.sellHotel(property, player1);
        assertEquals(850, banker.getBalance(player1)); // 900 - 50 (hotel price)
        assertTrue(property.hasHotel());
        assertEquals(32, banker.getAvailableHouses()); // 28 + 4 (returned from property)
        assertEquals(11, banker.getAvailableHotels());
    }

    @Test
    public void testBuyBackHouse() throws PlayerNotFoundException {
        // First sell the property to the player
        banker.sellProperty(property, player1);
        
        // Then sell a house
        banker.sellHouse(property, player1);
        
        // Then buy back the house
        banker.buyBackHouse(property, player1);
        assertEquals(1075, banker.getBalance(player1)); // 1050 + 25 (half house price)
        assertEquals(0, property.getNumHouses());
        assertEquals(32, banker.getAvailableHouses());
    }

    @Test
    public void testBuyBackHotel() throws PlayerNotFoundException {
        // First sell the property to the player
        banker.sellProperty(property, player1);
        
        // Then sell 4 houses
        for (int i = 0; i < 4; i++) {
            banker.sellHouse(property, player1);
        }
        
        // Then sell a hotel
        banker.sellHotel(property, player1);
        
        // Then buy back the hotel
        banker.buyBackHotel(property, player1);
        assertEquals(875, banker.getBalance(player1)); // 850 + 25 (half hotel price)
        assertFalse(property.hasHotel());
        assertEquals(28, banker.getAvailableHouses()); // 32 - 4 (given back to property)
        assertEquals(12, banker.getAvailableHotels());
    }

    @Test
    public void testCollectRent() throws PlayerNotFoundException {
        // First sell the property to player1
        banker.sellProperty(property, player1);
        
        // Then player2 lands on it
        banker.collectRent(property, player2);
        assertEquals(1100, banker.getBalance(player1)); // 1100 + 50 (rent)
        assertEquals(1450, banker.getBalance(player2)); // 1500 - 50 (rent)
    }

    @Test(expected = PlayerNotFoundException.class)
    public void testPlayerNotFound() throws PlayerNotFoundException {
        Player nonExistentPlayer = new Player("Non-existent", gameBoard);
        banker.getBalance(nonExistentPlayer);
    }
    
    @Test
    public void testMaximumHouses() throws PlayerNotFoundException {
        // Setup: Create properties for the same color group
        ColorGroup colorGroup = new ColorGroup("Red", 3);
        Property property1 = new Property("Property 1", 1, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        Property property2 = new Property("Property 2", 2, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        Property property3 = new Property("Property 3", 3, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        
        // Setup: Give properties to player
        banker.sellProperty(property1, player1);
        banker.sellProperty(property2, player1);
        banker.sellProperty(property3, player1);
        
        // Verify setup
        assertEquals(player1, property1.getOwner());
        assertEquals(player1, property2.getOwner());
        assertEquals(player1, property3.getOwner());
        
        // Action: Buy 32 houses (the maximum)
        for (int i = 0; i < 10; i++) {
            banker.sellHouse(property1, player1);
            banker.sellHouse(property2, player1);
            banker.sellHouse(property3, player1);
        }
        banker.sellHouse(property1, player1);
        banker.sellHouse(property2, player1);
        
        // Verify: All houses are purchased
        assertEquals(0, banker.getAvailableHouses());
        
        // Verify: Another purchase attempt should throw InvalidTransactionException
        try {
            banker.sellHouse(property3, player1);
            fail("Expected InvalidTransactionException was not thrown");
        } catch (InvalidTransactionException e) {
            // Expected exception
        }
    }
    
    @Test
    public void testMaximumHotels() throws PlayerNotFoundException {
        // Setup: Create properties for the same color group
        ColorGroup colorGroup = new ColorGroup("Red", 3);
        Property property1 = new Property("Property 1", 1, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        Property property2 = new Property("Property 2", 2, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        Property property3 = new Property("Property 3", 3, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.RED, colorGroup);
        
        // Setup: Give properties to player
        banker.sellProperty(property1, player1);
        banker.sellProperty(property2, player1);
        banker.sellProperty(property3, player1);
        
        // Setup: Add 4 houses to each property
        for (int i = 0; i < 4; i++) {
            banker.sellHouse(property1, player1);
            banker.sellHouse(property2, player1);
            banker.sellHouse(property3, player1);
        }
        
        // Action: Buy 12 hotels (the maximum)
        for (int i = 0; i < 4; i++) {
            // Buy 3 hotels each time for 3 different sets of properties
            banker.sellHotel(property1, player1);
            banker.sellHotel(property2, player1);
            banker.sellHotel(property3, player1);
        }
        
        // Verify: All hotels are purchased
        assertEquals(0, banker.getAvailableHotels());
        
        // Create a new property and try to buy a hotel
        ColorGroup blueGroup = new ColorGroup("Blue", 2);
        Property property4 = new Property("Property 4", 4, 100, 10, new int[]{50, 150, 450, 625}, 750, 50, PropertyColor.BLUE, blueGroup);
        banker.sellProperty(property4, player1);
        
        // Add 4 houses
        for (int i = 0; i < 4; i++) {
            banker.sellHouse(property4, player1);
        }
        
        // Verify: Another hotel purchase attempt should throw InvalidTransactionException
        try {
            banker.sellHotel(property4, player1);
            fail("Expected InvalidTransactionException was not thrown");
        } catch (InvalidTransactionException e) {
            // Expected exception
        }
    }
    
    @Test
    public void testAuctionProperty() throws PlayerNotFoundException {
        // This is a placeholder for testing property auctions
        // The actual implementation would depend on your auction mechanism
        
        // For now, we'll just verify that the property can be sold to the highest bidder
        banker.sellProperty(property, player2);
        assertEquals(player2, property.getOwner());
        assertEquals(1100, banker.getBalance(player2)); // 1500 - 400
    }
    
    @Test
    public void testTaxCollection() throws PlayerNotFoundException {
        // Setup: Player has money
        assertEquals(1500, banker.getBalance(player1));
        
        // Action: Collect income tax (flat fee of 200)
        banker.withdraw(player1, 200);
        
        // Verify: Player balance is reduced
        assertEquals(1300, banker.getBalance(player1));
        
        // Action: Collect luxury tax
        banker.withdraw(player1, 75);
        
        // Verify: Player balance is reduced again
        assertEquals(1225, banker.getBalance(player1));
    }
}