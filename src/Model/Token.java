package Model;

/**
 * Represents a player's token on the Monopoly board
 */
public class Token {
    private String type;
    private int position;
    private Player owner;

    public Token(String type) {
        this.type = type;
        this.position = 0;
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