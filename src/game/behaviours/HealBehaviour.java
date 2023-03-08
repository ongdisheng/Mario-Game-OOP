package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.HealAction;
import game.interfaces.Behaviour;

/**
 * A HealBehaviour class that gives certain actor to heal target
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class HealBehaviour implements Behaviour {
    /**
     * The target to be healed
     */
    private Actor target;

    /**
     * Constructor
     *
     * @param target the target to be healed
     */
    public HealBehaviour(Actor target){
        this.target = target;
    }

    /**
     * Get HealAction to heal the target
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return HealAction to heal the target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return new HealAction(target);
    }
}
