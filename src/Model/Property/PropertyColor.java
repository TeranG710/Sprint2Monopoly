/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class is an enum representing the different property colors in Monopoly
 * Team Member(s) responsible: Matt
 * */


package Model.Property;

/**
 * Enum representing the different property colors in Monopoly
 */
public enum PropertyColor {
    BROWN(2),    // Mediterranean, Baltic
    LIGHT_BLUE(3), // Oriental, Vermont, Connecticut
    PINK(3),     // St. Charles, States, Virginia
    ORANGE(3),   // St. James, Tennessee, New York
    RED(3),      // Kentucky, Indiana, Illinois
    YELLOW(3),   // Atlantic, Ventnor, Marvin Gardens
    GREEN(3),    // Pacific, North Carolina, Pennsylvania
    DARK_BLUE(2); // Park Place, Boardwalk

    private final int propertiesInGroup;

    /**
     * Constructor for PropertyColor
     *
     * @param propertiesInGroup Number of properties in this color group
     *                          Team member(s) responsible: Matt
     */
    PropertyColor(int propertiesInGroup) {
        this.propertiesInGroup = propertiesInGroup;
    }

    /**
     * Get the number of properties in this color group
     *
     * @return Number of properties
     * Team member(s) responsible: Matt
     */
    public int getPropertiesInGroup() {
        return propertiesInGroup;
    }
}