package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.enums.Status;
import game.interfaces.Resettable;

/**
 * A Coin class that represents the physical currency that the player collects
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Coin extends Item implements Resettable {
    /**
     * The integer value of the coin
     */
    private int value;

    /**
     * The name is set as a constant "Coin" / whatever you think is appropriate
     */
    private static final String NAME = "Coin";

    /**
     * The display character of the coin is set as a constant '$' / whatever you think is appropriate
     */
    private static final char COIN_CHAR = '$';

    /***
     * Constructor.
     *  @param value the value of this Coin
     */
    public Coin(int value) {
        super(NAME, COIN_CHAR, false);
        this.value = value;
        this.addAction(new PickUpCoinAction(this)); // coin can be picked up
        this.registerInstance();    // add to the resettable list
    }

    /**
     * Get the value of the coin
     *
     * @return the value of the coin
     */
    public int getValue() {
        return value;
    }

    /**
     * Check if the coin has the capability of RESET. If there is, we need to remove it from the current location
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET)){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Add RESET status to the coin when the game is being reset
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
