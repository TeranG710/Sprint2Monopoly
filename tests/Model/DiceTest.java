package Model;

import org.junit.jupiter.api.Test;

public class DiceTest {

    @Test
    public void testRollDie() {
        Dice dice = new Dice();
        int roll = dice.rollDie();
        assert(roll >= 1 && roll <= 6);
    }
}