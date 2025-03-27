package Model.Exceptions;

public class GameEndedEarlyException extends RuntimeException {
    /**
     * Constructor for GameEndedEarlyException
     * Team member(s) responsible: Deborah
     */
    public GameEndedEarlyException() {
        super("Game ended early!");
    }
}
