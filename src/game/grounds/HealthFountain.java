package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.enums.Status;
import game.waters.HealingWater;

/**
 * A HealthFountain class that provides HealingWater for player to refill
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class HealthFountain extends Fountain {
    /**
     * The display character is set as a constant H
     */
    private static final char DISPLAY_CHAR = 'H';

    /**
     * The name is set as a constant called Health Fountain
     */
    private static final String NAME = "Health Fountain";

    /**
     * Constructor.
     */
    public HealthFountain() {
        super(DISPLAY_CHAR);
        this.addCapability(Status.HEAL);    // add HEAL status (used in ConsumeBehaviour)
    }

    /**
     * Allows player to refill HealingWater if it still has water remaining
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return RefillAction if there is still HealingWater in the fountain
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // if there is still water
        if (this.getSlot() > 0) {
            // if player with bottle in his inventory steps on the HealthFountain, then allow the player to refill
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY) && location.getActor() == actor) {
                actions.add(new RefillAction(new HealingWater(), this));
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
