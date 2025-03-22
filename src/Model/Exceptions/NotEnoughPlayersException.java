package Model.Exceptions;

public class NotEnoughPlayersException extends RuntimeException {
    public NotEnoughPlayersException() {
        super("Not enough players to start the game");
    }
}
