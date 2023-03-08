package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.items.Coin;

/**
 * A PickUpCoinAction class which enables the player to pick up a coin
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class PickUpCoinAction extends PickUpItemAction {

    /**
     * The coin to pick up
     */
    private Coin coin;

    /**
     * Constructor.
     *
     * @param coin the coin to pick up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    /**
     * Picking up a coin from the location and add money to the player's balance
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a brief description on picking up a coin of a certain value
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Wallet.addBalance(coin.getValue()); // add money to the player's balance
        map.locationOf(actor).removeItem(coin); // remove coin from the location
        return menuDescription(actor);
    }

    /**
     * Display the message of picking up the coin in the menu
     *
     * @param actor The actor performing the action.
     * @return the message of picking up the coin
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up a $" + coin.getValue() + " coin";
    }
}

