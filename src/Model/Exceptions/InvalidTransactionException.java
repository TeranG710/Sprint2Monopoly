package Model.Exceptions;

public class InvalidTransactionException extends RuntimeException {

    /**
     * Constructor for InvalidTransactionException
     * Team member(s) responsible: Jamell
     */
    public InvalidTransactionException() {
        super("Invalid transaction");
    }
}
