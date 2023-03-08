package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour {

    private Actor followedTarget;

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (map.locationOf(actor) != null) {
            for (Exit exit : map.locationOf(actor).getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    Actor target = destination.getActor();
                    if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                        target.addCapability(Status.INJURED);
                        followedTarget = target;
                        actor.addCapability(Status.AGGRESSIVE);
                        return new AttackAction(target, exit.getName());
                    }
                }
            }
        }

        if (followedTarget != null && actor.hasCapability(Status.AGGRESSIVE) && !actor.hasCapability(Status.NOT_FOLLOWING)){
            return new FollowBehaviour(followedTarget).getAction(actor, map);
        }

        return null;
    }
}
