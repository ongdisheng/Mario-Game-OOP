package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A SuicideAction class indicating possibility of the actor being killed during reset or commit suicide in each turn
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class SuicideAction extends Action {
    /**
     * The location where the player commits suicide
     */
    private Location suicideLocation;

    /**
     * Constructor for creating suicide action
     *
     * @param location the location of the player committing suicide
     */
    public SuicideAction(Location location){
        this.suicideLocation = location;
    }

    /**
     * Display the message of an actor committing suicide at a specific location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the message of an actor committing suicide at a specific location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * A brief description on the location of the player who commits suicide
     *
     * @param actor The actor performing the action.
     * @return description on the location of the player who commits suicide
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (" + suicideLocation.x() + ", " + suicideLocation.y() + ") commits suicide";
    }
}