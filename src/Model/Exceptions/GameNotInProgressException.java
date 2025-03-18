package Model.Exceptions;

public class GameNotInProgressException extends RuntimeException {
    public GameNotInProgressException() {
        super("Game is not in progress");
    }
}
