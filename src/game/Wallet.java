package game;

/**
 * A Wallet class storing the player's balance
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Wallet {
    /**
     * the current balance of the player
     */
    private static int balance;

    /**
     * Add money to the player's wallet
     *
     * @param value the value of the money to be added to the wallet
     */
    public static void addBalance(int value){
        balance += value;
    }

    /**
     * Subtract money from the player's wallet
     *
     * @param value the value of the money to be subtracted from the wallet
     */
    public static void subtractBalance(int value){
        balance -= value;
    }

    /**
     * Get the current balance of the player
     *
     * @return current balance in the player's wallet
     */
    public static int getBalance(){
        return balance;
    }

}
