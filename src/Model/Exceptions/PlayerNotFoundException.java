package Model.Exceptions;

public class PlayerNotFoundException extends Exception {

    /**
     * Constructor for PlayerNotFoundException
     * Team member(s) responsible: Jamell
     */
    public PlayerNotFoundException() {
        super("Player not found");
    }

}
