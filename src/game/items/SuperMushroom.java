package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;
import game.interfaces.Consumable;
import game.interfaces.Tradable;

/**
 * A SuperMushroom class is one of the magical items used in the game
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class SuperMushroom extends Item implements Tradable, Consumable {
    /**
     * The price of SuperMushroom is set as a constant $400
     */
    private static final int PRICE = 400;

    /**
     * The name is set as a constant "Super Mushroom" / whatever you think is appropriate
     */
    private static final String NAME = "Super Mushroom";

    /**
     * The display character of SuperMushroom is set as '^'
     */
    private static final char SUPER_MUSHROOM_CHAR = '^';


    /***
     * Constructor.
     */
    public SuperMushroom() {
        super(NAME, SUPER_MUSHROOM_CHAR, true);
        this.addToConsumableItemManager();  // add to consumable item list
    }

    /**
     * Get the price of SuperMushroom
     *
     * @return the price of SuperMushroom
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Add UNDROPPABLE capability to SuperMushroom which is specific to the trading action only
     */
    @Override
    public void addCapabilityDuringTrading() {
        this.addCapability(Status.UNDROPPABLE);
    }

    /**
     * SuperMushroom will not be dropped to the ground if it has the capability of UNDROPPABLE
     *
     * @param actor the actor dropping this SuperMushroom
     * @return a DropItemAction if SuperMushroom does not contain the UNDROPPABLE status
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(this.hasCapability(Status.UNDROPPABLE))
            return null;
        return new DropItemAction(this);
    }

    /**
     * Add TALL capability to the actor who consumes SuperMushroom and increase the max hp of actor by 50
     * @param consumedActor Actor who consumes SuperMushroom
     */
    @Override
    public void consumeMagicalItems(Actor consumedActor) {
        consumedActor.addCapability(Status.TALL);
        consumedActor.increaseMaxHp(50);
    }

    /**
     * Get the string of SuperMushroom object
     *
     * @return a string that represents SuperMushroom object
     */
    @Override
    public String toString(){
        return "SuperMushroom";
    }
}
