package Model.Exceptions;

public class GameInProgressException extends RuntimeException {
    /**
     * Constructor for GameInProgressException
     * Team member(s) responsible: Deborah
     */
    public GameInProgressException() {
        super("Game is already in progress");
    }
}
