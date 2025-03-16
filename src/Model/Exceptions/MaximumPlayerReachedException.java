package Model.Exceptions;

public class MaximumPlayerReachedException extends RuntimeException {
    public MaximumPlayerReachedException() {
        super("Maximum number of players reached");
    }
}
