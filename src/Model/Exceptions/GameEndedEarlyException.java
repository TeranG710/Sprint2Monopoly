package Model.Exceptions;

public class GameEndedEarlyException extends RuntimeException {
    public GameEndedEarlyException() {
        super("Game ended early!");
    }
}
