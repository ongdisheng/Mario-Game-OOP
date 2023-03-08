package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.Behaviour;

/**
 * A PatrolBehaviour class that allows an Actor to patrol in a predefined routes
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class PatrolBehaviour implements Behaviour {
    /**
     * The maximum y coordinates is set as a constant 16
     */
    private static final int MAX_Y_COORD = 16;

    /**
     * The minimum y coordinates is set as a constant 12
     */
    private static final int MIN_Y_COORD = 12;

    /**
     * The direction of patrol
     */
    private boolean patrolDirFlag = true; // true - move upwards; false - move downwards

    /**
     * Return MoveActorAction with different direction depending on which direction the actor is patrolling
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return MoveActorAction with different direction depending on which direction the actor is patrolling
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if actor reaches bottom, then set direction flag to true
        if (reachedBottom(actor, map)){
            patrolDirFlag = true;
        }

        // if actor reaches top, then set direction flag to false
        else if (reachedTop(actor, map)){
            patrolDirFlag = false;
        }

        // get the next available y coordinates to move to
        int nextYCoord = getNextAvailableYCoord(patrolDirFlag, map, actor);

        // if there is no available y coordinates, then change patrol direction
        if (nextYCoord == -1){
            if (patrolDirFlag){
                patrolDirFlag = false;
            }
            else {
                patrolDirFlag = true;
            }
            return new DoNothingAction();
        }

        else {
            // if patrolling to north
            if (patrolDirFlag) {
                return new MoveActorAction(map.at(map.locationOf(actor).x(), nextYCoord), "North");
            } else {
                return new MoveActorAction(map.at(map.locationOf(actor).x(), nextYCoord), "South");
            }
        }

    }

    /**
     * Return boolean value whether the actor reached the top of the route
     * @param actor the patrolling actor
     * @param map the map that contains the patrolling actor
     * @return boolean value whether the actor reached the top of the route
     */
    private boolean reachedTop(Actor actor, GameMap map){
        return map.locationOf(actor).y() == MIN_Y_COORD;
    }

    /**
     * Return boolean value whether the actor reached the bottom of the route
     *
     * @param actor the patrolling actor
     * @param map the map that contains the patrolling actor
     * @return boolean value whether the actor reached the bottom of the route
     */
    private boolean reachedBottom(Actor actor, GameMap map){
        return map.locationOf(actor).y() == MAX_Y_COORD;
    }

    /**
     * Get the maximum y coordinates of the patrol route
     *
     * @return the maximum y coordinates of the patrol route
     */
    public static int getMaxYCoord(){
        return MAX_Y_COORD;
    }

    /**
     * Get the next available y coordinates for the actor to move to
     *
     * @param patrolDirFlag the current direction of the patrol
     * @param map the map that contains the patrolling actor
     * @param actor the patrolling actor
     * @return
     */
    private int getNextAvailableYCoord(boolean patrolDirFlag, GameMap map, Actor actor){
        if (patrolDirFlag){
            for (int i = map.locationOf(actor).y() - 1; i >= MIN_Y_COORD; i--){
                if (map.at(map.locationOf(actor).x(), i).canActorEnter(actor)){
                    return i;
                }
            }
        }
        else {
            for (int i = map.locationOf(actor).y() + 1; i <= MAX_Y_COORD; i++){
                if (map.at(map.locationOf(actor).x(), i).canActorEnter(actor)){
                    return i;
                }
            }
        }
        return -1;
    }
}
