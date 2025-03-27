package Model.Exceptions;

public class MaximumPlayerReachedException extends RuntimeException {
    /**
     * Constructor for MaximumPlayerReachedException
     * Team member(s) responsible: Deborah
     */
    public MaximumPlayerReachedException() {
        super("Maximum number of players reached");
    }
}
