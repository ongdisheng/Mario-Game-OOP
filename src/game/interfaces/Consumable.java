package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.managers.ConsumableItemManager;

/**
 * An interface for representing the Magical items that are consumable
 *
 * @author Kennedy Tan
 * @version 1.0
 */
public interface Consumable {

    /**
     * Method to consume Magical items
     *
     * @param actor actor that consumes Magical items
     */
    public void consumeMagicalItems(Actor actor);

    /**
     * Method to add Consumable Magical items to consumableList
     * in ConsumableItemManager
     */
    default void addToConsumableItemManager(){
        ConsumableItemManager.getInstance().appendConsumableItem(this);
    }

}
