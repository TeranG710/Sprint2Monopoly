
public class Dice {
    private int faceValue;

    public Dice() {
        this.faceValue = 1;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void roll() {
        faceValue = (int) (Math.random() * 6) + 1;
    }
}
