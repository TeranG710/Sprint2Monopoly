package Model.Exceptions;

public class InsufficientFundsException extends RuntimeException {

    /**
     * Constructor for InsufficientFundsException
     * Team member(s) responsible: Jamell
     */
    public InsufficientFundsException() {
        super("Insufficient funds");
    }
}
