package Model.Board;

/**
 * Represents a human player in a Monopoly game.
 * Inherits from the abstract Player class.
 * Team member(s) responsible: Matt
 */
public class HumanPlayer extends Player {

    /**
     * Constructor for HumanPlayer.
     *
     * @param name  Player's name
     * @param board The game board
     * Team member(s) responsible: Matt
     */
    public HumanPlayer(String name, GameBoard board) {
        super(name, board);
    }

    /**
     * Defines how a human player takes their turn.
     * Team member(s) responsible: Matt
     */
    @Override
    public void takeTurn() {
        System.out.println(getName() + " (Human) is taking their turn.");
        // Implement human-specific turn logic
    }
}
