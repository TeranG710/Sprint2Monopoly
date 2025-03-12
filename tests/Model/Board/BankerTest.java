package Model.Board;

import Model.Exceptions.InsufficientFundsException;
import Model.Exceptions.InvalidTransactionException;
import Model.Exceptions.PlayerNotFoundException;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankerTest {

    @Test
    public void testAddPlayerAndGetBalance() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        assertEquals(1500, banker.getBalance(player));
    }

    @Test
    public void testRemovePlayer() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        banker.removePlayer(player);
        assertThrows(PlayerNotFoundException.class, () -> banker.getBalance(player));
    }

    @Test
    public void testAddPlayerTwice() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        Player player2 = new Player("TestPlayer2", new GameBoard());
        banker.addPlayer(player);
        banker.addPlayer(player2);
        assertEquals(1500,banker.getBalance(player));
        assertEquals(1500,banker.getBalance(player2));
    }

    @Test
    public void testDepositToPlayer() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        banker.deposit(player, 500);
        assertEquals(2000, banker.getBalance(player));
    }

    @Test
    public void testWithdrawFromPlayer() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        banker.withdraw(player, 500);
        assertEquals(1000, banker.getBalance(player));
    }

    @Test
    public void testWithdrawToAPlayerWhoDoesntExist() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        assertThrows(PlayerNotFoundException.class, () -> banker.withdraw(player, 500));
    }

    @Test
    public void testDepositToAPlayerWhoDoesntExist() throws PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        assertThrows(PlayerNotFoundException.class, () -> banker.deposit(player, 500));
    }

    @Test
    public void testDepositWithNegativeAmount() throws InvalidTransactionException, PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        assertThrows(InvalidTransactionException.class, () -> banker.deposit(player, -500));
    }

    @Test
    public void testWithdrawFromAPlayerWithNegativeAmount() throws InvalidTransactionException, PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        assertThrows(InvalidTransactionException.class, () -> banker.withdraw(player, -500));
    }

    @Test
    public void testWithdrawFromAPlayerWithInsufficientFunds() throws InsufficientFundsException, PlayerNotFoundException {
        Banker banker = new Banker();
        Player player = new Player("TestPlayer", new GameBoard());
        banker.addPlayer(player);
        assertThrows(InsufficientFundsException.class, () -> banker.withdraw(player, 1600));
    }
}