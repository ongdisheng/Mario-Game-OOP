package game.interfaces;

/**
 * A Tradable interface which should be implemented by items that are tradable with the toad
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public interface Tradable {
    /**
     * Get the price of the tradable items
     *
     * @return the price of the tradable items
     */
    public int getPrice();

    /**
     * Add a capability to tradable item which is specific to the trading action only
     */
    public void addCapabilityDuringTrading();
}
