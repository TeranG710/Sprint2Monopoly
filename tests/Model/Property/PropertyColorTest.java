package Model.Property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyColorTest {

    @Test
    public void testPropertiesInGroup() {
        assertEquals(2, PropertyColor.BROWN.getPropertiesInGroup());
        assertEquals(3, PropertyColor.RED.getPropertiesInGroup());
        assertEquals(2, PropertyColor.DARK_BLUE.getPropertiesInGroup());
    }

    @Test
    public void testEnumValues() {
        PropertyColor[] colors = PropertyColor.values();
        assertEquals(8, colors.length);

        assertTrue(contains(colors, PropertyColor.BROWN));
        assertTrue(contains(colors, PropertyColor.LIGHT_BLUE));
        assertTrue(contains(colors, PropertyColor.PINK));
        assertTrue(contains(colors, PropertyColor.ORANGE));
        assertTrue(contains(colors, PropertyColor.RED));
        assertTrue(contains(colors, PropertyColor.YELLOW));
        assertTrue(contains(colors, PropertyColor.GREEN));
        assertTrue(contains(colors, PropertyColor.DARK_BLUE));
    }

    private boolean contains(PropertyColor[] colors, PropertyColor color) {
        for (PropertyColor c : colors) {
            if (c == color) return true;
        }
        return false;
    }
}