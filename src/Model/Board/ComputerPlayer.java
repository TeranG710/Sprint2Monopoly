package Model.Board;

/**
 * Represents a computer-controlled player in a Monopoly game.
 * Inherits from the abstract Player class.
 * Team member(s) responsible: Matt
 */
public class ComputerPlayer extends Player {

    /**
     * Constructor for ComputerPlayer.
     *
     * @param name  Player's name
     * @param board The game board
     * Team member(s) responsible: Matt
     */
    public ComputerPlayer(String name, GameBoard board) {
        super(name, board);
    }

}
