package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.Consumable;
import game.interfaces.Tradable;

/**
 * A PowerStar class is one of the magical items used in the game
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class PowerStar extends Item implements Tradable, Consumable {
    /**
     * The price of PowerStar is set as a constant 600
     */
    private static final int PRICE = 600;

    /**
     * The name is set as a constant "Power Star" / whatever you think is appropriate
     */
    private static final String NAME = "Power Star";

    /**
     * The display character of PowerStar is set as a constant '*'
     */
    private static final char POWER_STAR_CHAR = '*';

    /**
     * The number of turns for PowerStar to fade away
     */
    private int turn = 10;
    /**
     * The number of turns for Player to stay in Invincible mode
     */
    private static int invincibleTurn;

    /***
     * Constructor.
     */
    public PowerStar() {
        super(NAME, POWER_STAR_CHAR, true);
        this.addToConsumableItemManager();  // add to consumable item list
    }

    /**
     * Get the price of PowerStar
     *
     * @return the price of PowerStar
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Add UNDROPPABLE capability to PowerStar which is specific to the trading action only
     */
    @Override
    public void addCapabilityDuringTrading() {
        this.addCapability(Status.UNDROPPABLE);
    }

    /**
     * PowerStar will not be dropped to the ground if it has the capability of UNDROPPABLE
     *
     * @param actor the actor dropping this PowerStar
     * @return a DropItemAction if PowerStar does not contain the UNDROPPABLE status
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        if(this.hasCapability(Status.UNDROPPABLE))
            return null;
        return new DropItemAction(this);
    }

    /**
     * Remove PowerStar from the actor's inventory within 10 turns
     *
     * @param currentLocation The location of the actor carrying this PowerStar.
     * @param actor The actor carrying this PowerStar.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        turn -= 1;  // decrement turn
        // if use up 10 turns
        if (turn < 1){
            actor.removeItemFromInventory(this);    // remove from inventory
        }
    }

    /**
     * Remove PowerStar from the current location within 10 turns
     *
     * @param currentLocation The location of the ground on which PowerStar lie.
     */
    @Override
    public void tick(Location currentLocation){
        turn -= 1;  // decrement turn
        // if use up 10 turns
        if (turn < 1){
            currentLocation.removeItem(this);   // remove from current location
        }
    }

    /**
     * Add INVINCIBLE capability to the actor who consumes PowerStar and heal the actor with 200 hp
     *
     * @param consumedActor Actor who consumes PowerStar
     */
    @Override
    public void consumeMagicalItems(Actor consumedActor) {
        consumedActor.addCapability(Status.INVINCIBLE);
        consumedActor.heal(200);
        invincibleTurn = 10;
    }

    /**
     * Get number of turns of player would be in Invincible state
     *
     * @return number of turns of player in Invincible state
     */
    public static int getInvincibleTurn(){
        return invincibleTurn;
    }

    /**
     * Decrement number of turns by 1
     */
    public static void subtractInvincibleTurn(){
        invincibleTurn--;
    }

    /**
     * Get the string of PowerStar object
     *
     * @return a string that represents PowerStar object
     */
    @Override
    public String toString(){
        return "Power Star";
    }
}
