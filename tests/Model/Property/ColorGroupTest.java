package Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ColorGroupTest {
    private ColorGroup colorGroup;
    private Property boardwalk;
    private Property parkPlace;
    private Player owner;

    @Before
    public void setUp() {
        colorGroup = new ColorGroup("DARK_BLUE", 2);
        boardwalk = new Property(
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
        parkPlace = new Property(
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
        owner = new Player("TestOwner", 2000);
    }

    @Test
    public void testConstructor() {
        assertEquals("DARK_BLUE", colorGroup.getColor());
        assertEquals(2, colorGroup.getPropertiesInGroup());
        assertTrue(colorGroup.getProperties().isEmpty());
    }

    @Test
    public void testAddProperty() {
        colorGroup.addProperty(boardwalk);
        assertEquals(1, colorGroup.getProperties().size());
        assertTrue(colorGroup.getProperties().contains(boardwalk));
    }

    @Test
    public void testHasMonopolyTrue() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        assertTrue(colorGroup.hasMonopoly(owner));
    }

    @Test
    public void testHasMonopolyFalse() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(new Player("OtherPlayer", 2000));
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        assertFalse(colorGroup.hasMonopoly(owner));
    }

    @Test
    public void testCanAddHouseEvenBuild() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        
        assertTrue(colorGroup.canAddHouse(boardwalk));
        boardwalk.addHouse();
        assertFalse(colorGroup.canAddHouse(boardwalk)); // Can't add second house until Park Place has one
        assertTrue(colorGroup.canAddHouse(parkPlace));
    }

    @Test
    public void testCanAddHotel() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        
        // Add 4 houses to both properties
        for (int i = 0; i < 4; i++) {
            boardwalk.addHouse();
            parkPlace.addHouse();
        }
        
        assertTrue(colorGroup.canAddHotel(boardwalk));
    }

    @Test
    public void testGetMinHouses() {
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        
        boardwalk.addHouse();
        boardwalk.addHouse();
        parkPlace.addHouse();
        
        assertEquals(1, colorGroup.getMinHouses());
    }

    @Test
    public void testGetHotelCount() {
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        
        // Add 4 houses and a hotel to boardwalk
        for (int i = 0; i < 4; i++) {
            boardwalk.addHouse();
        }
        boardwalk.addHotel();
        
        assertEquals(1, colorGroup.getHotelCount());
    }

    @Test
    public void testIsFullyMortgaged() {
        boardwalk.setOwner(owner);
        parkPlace.setOwner(owner);
        colorGroup.addProperty(boardwalk);
        colorGroup.addProperty(parkPlace);
        
        boardwalk.mortgage();
        parkPlace.mortgage();
        
        assertTrue(colorGroup.isFullyMortgaged());
    }
}