package game.waters;

import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Consumable;

/**
 * An abstract Water class which extends Item class and implements Consumable interface
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public abstract class Water extends Item implements Consumable {
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Water(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

}
