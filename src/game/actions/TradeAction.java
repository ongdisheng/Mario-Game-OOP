package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Wallet;
import game.interfaces.Tradable;

/**
 * A TradeAction class is responsible for the trading process between the player and the toad
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class TradeAction extends Action {
    /**
     * The item that is tradable from the toad
     */
    private Tradable item;

    /**
     * The constant message showing a success purchase
     */
    private static final String SUCCESS_PURCHASE_MSG = " has been added to the inventory successfully\nThank you. Please come again";

    /**
     * The constant message showing a fail purchase
     */
    private static final String FAIL_PURCHASE_MSG = "You don't have enough coins!";

    /**
     * Constructor
     *
     * @param item the item that can be traded from the toad
     */
    public TradeAction(Tradable item){
        this.item = item;
    }

    /**
     * Implement the logic behind the trading process
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message indicating whether it's a success or a fail purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // if player has enough money to purchase the item
        if (Wallet.getBalance() >= item.getPrice()){
            Wallet.subtractBalance(item.getPrice());    // subtract the money from the player's balance
            item.addCapabilityDuringTrading();  // add certain capability specific to the item during trading action
            actor.addItemToInventory((Item) item);  // add the item to the player's inventory
            return item + SUCCESS_PURCHASE_MSG;
        } else {
            return FAIL_PURCHASE_MSG;
        }

    }

    /**
     * Display the tradable item and its price in the menu
     *
     * @param actor The actor performing the action.
     * @return the description on the tradable item and its price
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + item + " ($" + item.getPrice() + ")";
    }
}

