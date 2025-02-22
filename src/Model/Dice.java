package Model;

import java.util.Random;

public class Dice {

    private Random random;
    private int die1;
    private int die2;
    private int doubleRollCounter;

    public Dice() {
        this.random = new Random();
        doubleRollCounter = 0;
    }

    public void roll() {
        die1 = rollDie();
        die2 = rollDie();
    }

    public int rollDie() {
        return random.nextInt(6) + 1;
    }

    public boolean goToJail() {
        return doubleRollCounter == 3;
    }

    public int getSum() {
        return die1 + die2;
    }

    public boolean isDouble() {
        incrementDoubleRollCounter();
        return die1 == die2;
    }

    public void incrementDoubleRollCounter() {
        if (doubleRollCounter == 3) {
            doubleRollCounter = 0;
        }
        doubleRollCounter++;
    }

    public int getDie1() {
        return die1;
    }

    public int getDie2() {
        return die2;
    }
}
