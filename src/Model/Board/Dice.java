package Model.Board;

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

    /**
     * Rolls the dice
     */
    public void roll() {
        die1 = rollDie();
        die2 = rollDie();
    }

    /**
     * Rolls a single die from 1 to 6
     * @return the value of the die
     */
    public int rollDie() {
        return random.nextInt(6) + 1;
    }

    /**
     * Checks if the player has rolled doubles 3 times in a row
     * */
    public boolean goToJail() {
        return doubleRollCounter == 3;
    }

    /**
     * Returns the sum of the dice
     * @return the sum of the dice
     */
    public int getSum() {
        return die1 + die2;
    }

    /**
     * Checks if the player has rolled doubles
     * @return true if the player has rolled doubles
     */
    public boolean isDouble() {
        incrementDoubleRollCounter();
        return die1 == die2;
    }

    /**
     * Increments the double roll counter
     */
    public void incrementDoubleRollCounter() {
        if (doubleRollCounter == 3) {
            doubleRollCounter = 0;
        }
        doubleRollCounter++;
    }

    /**
     * returns the value of the dice
     * */
    public int getDie1() {
        return die1;
    }

    /**
     * returns the value of the dice
     * */
    public int getDie2() {
        return die2;
    }
}
