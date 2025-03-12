package Model.Exceptions;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException() {
        super("Invalid transaction");
    }
}
