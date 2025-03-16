package Model.Exceptions;

public class GameInProgressException extends RuntimeException {
    public GameInProgressException() {
        super("Game is already in progress");
    }
}
