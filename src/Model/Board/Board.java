package Model.Board;

public class Board {
    private Dice dice;
    public Board() {
        this.dice = new Dice();
    }
    public Dice getDice() {
        return dice;
    }


}
