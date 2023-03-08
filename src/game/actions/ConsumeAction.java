package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;
import game.interfaces.Consumable;
import game.items.Bottle;
import game.managers.ConsumableItemManager;

/**
 * A class that Player uses to consume consumable magical items (e.g Power Star & SuperMushroom)
 *
 * @author Kennedy Tan
 * @version 1.0
 */
public class ConsumeAction extends Action {

    /**
     * An instance of Consumable Interface
     */
    private Consumable consumable;

    /**
     * Constructor.
     *
     * @param consumable object with type Consumable
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //upcasting consumable
        Item item = ((Item) consumable);

        //invoke consumeMagicalItems() method from consumable interface
        consumable.consumeMagicalItems(actor);

        if (item.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY)){
            return actor + " consume " + Bottle.drinkWater();
        }

        //remove consumable from inventory once consumed by Player
        actor.removeItemFromInventory(item);

        //remove consumed items from consumableList
        ConsumableItemManager.getInstance().removeConsumableItem(item);

        //return output to console
        return actor + " consume Magical Items: " + consumable;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
