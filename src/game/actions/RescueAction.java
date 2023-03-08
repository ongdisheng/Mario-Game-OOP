package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

public class RescueAction extends Action {
    /**
     * The Actor that is to be rescued
     */
    protected Actor target;

    /**
     * Constructor.
     * @param target to be destroyed
     */
    public RescueAction(Actor target) {
        this.target = target;
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
        //congrats message on console
        output = "Congratulations! " + target + " has been saved.";
        actor.removeCapability(Status.VICTORY);
        //remove actor to end the game
        map.removeActor(actor);
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
        return actor + " unlock " + target + "'s handcuffs.";
    }
}
