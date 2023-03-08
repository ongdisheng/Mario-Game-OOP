package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A HealAction class that performs healing to a target
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class HealAction extends Action {
    /**
     * The target to be healed
     */
    private Actor target;
    /**
     * The healpoint is set as a constant 5
     */
    private static final int HEAL_POINT = 5;

    /**
     * Constructor
     *
     * @param target the target to be healed
     */
    public HealAction(Actor target){
        this.target = target;
    }

    /**
     * Heal the target with the constant healpoint
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string describing the healing action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        target.heal(HEAL_POINT);    // heal target
        return actor + " heal " + target + " (" + HEAL_POINT + "hp)";
    }

    /**
     * Return string describing the healing action
     *
     * @param actor The actor performing the action.
     * @return string describing the healing action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " heal " + target;
    }
}
