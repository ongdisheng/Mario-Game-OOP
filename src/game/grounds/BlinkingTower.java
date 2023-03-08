package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BlinkAction;
import game.enums.Status;

/**
 * A BlinkingTower class that allows the player to blink to several steps away
 *
 * @author Ong Di Sheng
 * @version 1.0
 *
 */
public class BlinkingTower extends HighGround {
    /**
     * The jump chance related to a BlinkingTower (used for jumping)
     */
    private static final double JUMP_CHANCE = 1.0;
    /**
     * The fall damage related to a BlinkingTower (used for jumping)
     */
    private static final int FALL_DAMAGE = 0;

    /**
     * Constructor.
     */
    public BlinkingTower() {
        super('?');
        setJumpChance(JUMP_CHANCE);
        setFallDamage(FALL_DAMAGE);
        this.addCapability(Status.BLINKABLE);   // add BLINKABLE status
    }

    /**
     * Allow the player to blink if the player steps on this blinking tower
     *
     * @param actor the Actor performing an action
     * @param location the current Location of the High Ground
     * @param direction the direction of the High Ground from the Actor
     * @return BlinkAction if the player steps on this blinking tower
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() && location.getActor() == actor){
            actions.add(new BlinkAction());
        }
        return actions;
    }

    /**
     * Return the name of this blinking tower
     *
     * @return the name of this blinking tower
     */
    @Override
    public String toString() {
        return "Blinking Tower";
    }
}
