package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.SuperMushroom;

/**
 * DestroyAction used to destroy Koopa's shell when Koopa is in dormant state.
 *
 * @author Mark Manlangit
 * @version 1.0
 */
public class DestroyAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param target    target to be destroyed
     * @param direction String representing the direction of the target
     */
    public DestroyAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return output, a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output;

        if (Math.random() < actor.getWeapon().chanceToHit()) {
            //add item SuperMushroom on that location
            map.locationOf(target).addItem(new SuperMushroom());
            //remove target from map
            map.removeActor(target);
            //String displayed to console
            output = actor + " destroyed " + target + " with a wrench ";
        } else {
            output = actor + " misses " + target + ".";
        }
        return output;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " destroys Koopa shell at " + direction;
    }
}
