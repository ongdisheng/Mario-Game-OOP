package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.enums.Status;
import game.interfaces.Consumable;
import game.waters.Water;

import java.util.ArrayList;

/**
 * A Bottle class that is able to hold the water
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Bottle extends Item implements Consumable {
    /**
     * The name is set as a constant called Bottle
     */
    private static final String NAME = "Bottle";
    /**
     * The display character is set as a constant b
     */
    private static final char DISPLAY_CHAR = 'b';

    /**
     * The bottle can hold an infinite amount of water
     */
    private static ArrayList<Water> waters = new ArrayList<>();

    /***
     * Constructor.
     */
    public Bottle() {
        super(NAME, DISPLAY_CHAR, false);
        this.addCapability(Status.NON_REMOVABLE_FROM_INVENTORY);    // can remove from inventory
        this.addToConsumableItemManager();  // easier retrieval of current contents in the bottle
    }

    /**
     * Fill water in the bottle
     *
     * @param water water to be added into the bottle
     */
    public static void fillWater(Water water){
        waters.add(water);
    }

    /**
     * Return the water being drank
     *
     * @return the water being drank
     */
    public static Water drinkWater(){
        Water lastWater = waters.get(waters.size() - 1);    // get the last water
        waters.remove(waters.size() - 1);   // remove the last water
        return lastWater;
    }

    /**
     * Get the number of water in the bottle
     *
     * @return the number of water in the bottle
     */
    public static int getNumOfWaters(){
        return waters.size();
    }

    /**
     * Consume the last water in the bottle
     *
     * @param actor actor that consumes Magical items
     */
    @Override
    public void consumeMagicalItems(Actor actor) {
        waters.get(waters.size() - 1).consumeMagicalItems(actor);
    }

    /**
     * Bottle cannot be picked up
     *
     * @param actor player
     * @return null indicating that bottle cannot be picked up
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    /**
     * Bottle cannot be dropped
     *
     * @param actor player
     * @return null indicating that bottle cannot be dropped
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Return the current contents in the bottle
     *
     * @return the current contents in the bottle
     */
    @Override
    public String toString(){
        String res = "";

        for (int i = 0; i < waters.size(); i++){
            if (i > 0){
                res += ", ";
            }

            res += waters.get(i);
        }

        return "Bottle[" + res + "]";
    }
}

