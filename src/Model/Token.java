package Model;

/**
 * Represents a player's token on the Monopoly board
 */
public class Token {
    private final String type;  // e.g., "Car", "Dog", "Ship"
    private int position; // Current position on board (0-39)
    private Player owner;

    public Token(String type) {
        this.type = type;
        this.position = 0; // Start at GO
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getType() {
        return type;
    }
}