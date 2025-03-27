package Model.Exceptions;

public class GameNotInProgressException extends RuntimeException {
    /**
     * Constructor for GameNotInProgressException
     * Team member(s) responsible: Deborah
     */
    public GameNotInProgressException() {
        super("Game is not in progress");
    }
}
