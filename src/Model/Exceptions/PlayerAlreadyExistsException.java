package Model.Exceptions;

public class PlayerAlreadyExistsException extends RuntimeException {
    /**
     * Constructor for PlayerAlreadyExistsException
     * Team member(s) responsible: Deborah
     */
    public PlayerAlreadyExistsException() {
        super("Player already exists");
    }
}
