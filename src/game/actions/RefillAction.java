package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Fountain;
import game.items.Bottle;
import game.waters.Water;

/**
 * A RefillAction class to refill water
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class RefillAction extends Action {
    /**
     * The water to be refilled
     */
    private Water water;

    /**
     * The fountain to be refilled from
     */
    private Fountain fountain;

    /**
     * Constructor to refill water
     *
     * @param water water to be refilled
     * @param fountain fountain to be refilled from
     */
    public RefillAction(Water water, Fountain fountain){
        this.water = water;
        this.fountain = fountain;
    }

    /**
     * The logic behind refilling water
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the message of refilling water successfully
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle.fillWater(water);    // refill water into bottle

        fountain.subtractSlot();    // subtract 1 water slot

        return actor + " refill " + water;
    }

    /**
     * Return the refill water string to be shown on the menu
     *
     * @param actor The actor performing the action.
     * @return refill water message on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + fountain + " (" + fountain.getSlot() + "/" + fountain.getMaxCapacity() + ")";
    }
}
