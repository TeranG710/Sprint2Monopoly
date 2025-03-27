package Model.Exceptions;

public class NotEnoughPlayersException extends RuntimeException {
    /**
     * Constructor for NotEnoughPlayersException
     * Team member(s) responsible: Deborah
     */
    public NotEnoughPlayersException() {
        super("Not enough players to start the game");
    }
}
