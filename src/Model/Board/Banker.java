package Model.Board;
import Model.Exceptions.InsufficientFundsException;
import Model.Exceptions.InvalidTransactionException;
import Model.Player;
import Model.Exceptions.PlayerNotFoundException;
import java.util.HashMap;

public class Banker {

    private HashMap<Player, Integer> playerBalances;

    public Banker() {
        this.playerBalances = new HashMap<>();
    }

    /**
     * Adds a player to the banker's list of players
     * if player is already in the list, throws PlayerNotFoundException
     * @param player Player to add
     * Team member(s) responsible: Jamell
     * */
    public void addPlayer(Player player) throws PlayerNotFoundException {
        if (playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        playerBalances.put(player, 1500);
    }

    /**
     * removes a player from thr banker's list of players
     * if player is not in the list, throws PlayerNotFoundException
     * @param player Player to remove
     * Team member(s) responsible: Jamell
     * */
    public void removePlayer(Player player) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        playerBalances.remove(player);
    }

    /**
     * Gets a player's balance from the banker
     * if player is not in the list, throws PlayerNotFoundException
     * @param player Player to get balance of
     * @return int Player's balance
     * Team member(s) responsible: Jamell
     */
    public int getBalance(Player player) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        return playerBalances.get(player);
    }

    /**
     * Deposits money into a player's account
     * if player is not in the list, throws PlayerNotFoundException
     * @param player Player to deposit money into
     * @param amount Amount to deposit
     * Team member(s) responsible: Jamell
     * */
    public void deposit(Player player, int amount) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if (amount < 0) {
            throw new InvalidTransactionException();
        }
        playerBalances.put(player, playerBalances.get(player) + amount);
    }

    /**
     * Withdraws money from a player's account
     * if player is not in the list, throws PlayerNotFoundException
     * @param player Player to withdraw money from
     * @param amount Amount to withdraw
     * Team member(s) responsible: Jamell
     * */
    public void withdraw(Player player, int amount) throws PlayerNotFoundException, InvalidTransactionException, InsufficientFundsException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if(amount < 0){
            throw new InvalidTransactionException();
        }
        if (playerBalances.get(player) < amount) {
            throw new InsufficientFundsException();
        }
        playerBalances.put(player,playerBalances.get(player) - amount);
    }

}
