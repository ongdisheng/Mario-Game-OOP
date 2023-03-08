package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.enums.Status;
import game.waters.PowerWater;

/**
 * A PowerFountain class that provides PowerWater for the player to refill
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class PowerFountain extends Fountain {
    /**
     * The display character is set as a constant A
     */
    private static final char DISPLAY_CHAR = 'A';

    /**
     * The name is set as a constant called Power Fountain
     */
    private static final String NAME = "Power Fountain";

    /**
     * Constructor.
     */
    public PowerFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.POWER);   // add POWER status (used in ConsumeBehaviour)
    }

    /**
     * Allows player to refill if it still has water remaining
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return RefillAction if there is still PowerWater in the fountain
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // if there is still water
        if (this.getSlot() > 0) {
            // if player with bottle in his inventory steps on the PowerFountain, then allow the player to refill
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY) && location.getActor() == actor) {
                actions.add(new RefillAction(new PowerWater(), this));
            }
        }

        return actions;
    }

    /**
     * Handle the recharge logic
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    /**
     * Return the name of this fountain
     *
     * @return the name of this fountain
     */
    @Override
    public String toString() {
        return NAME;
    }
}
