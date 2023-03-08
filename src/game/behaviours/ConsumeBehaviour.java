package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.waters.HealingWater;
import game.waters.PowerWater;

/**
 * A ConsumeBehaviour class which is responsible for the enemies to consume either PowerWater or HealingWater
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class ConsumeBehaviour implements Behaviour {
    /**
     * Get ConsumeAction if enemies step on either PowerFountain or HealthFountain
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return ConsumeAction if enemies step on either PowerFountain or HealthFountain
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if current location of enemy is PowerFountain, then consume PowerWater
        if (map.locationOf(actor).getGround().hasCapability(Status.POWER)){
            return new ConsumeAction(new PowerWater());
        }
        // if current location of enemy is HealingFountain, then consume HealingWater
        else if (map.locationOf(actor).getGround().hasCapability(Status.HEAL)){
            return new ConsumeAction(new HealingWater());
        }
        // otherwise, null
        return null;
    }
}
