package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A BlinkAction class that enables player to blink to a random steps away
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class BlinkAction extends Action {
    /**
     * The cardinal number of steps is set as a constant 4
     */
    private static final int CARDINAL_NUM_OF_STEPS = 4;

    /**
     * The ordinal number of steps is set as a constant 2
     */
    private static final int ORDINAL_NUM_OF_STEPS = 2;

    /**
     * Implement the logic behind BlinkAction
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string describing the action of blinking
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // get the destination location to blink to
        Location destinationLocation = getDestinationLocation(map.locationOf(actor));
        // if there exists a destination location, then blink the player to that location
        if (destinationLocation != null) {
            map.moveActor(actor, destinationLocation);
            return actor + " blinks to " + destinationLocation.getGround().toString() + " at (" + destinationLocation.x() + ", " + destinationLocation.y() + ")";
        }

        // if there is no destination location, then return corresponding message
        else {
            return "There is no location (" + CARDINAL_NUM_OF_STEPS + " steps away) for " + actor + " to blink";
        }
    }

    /**
     * Return a string describing the action of blinking to several steps away
     *
     * @param actor The actor performing the action.
     * @return a string describing the action of blinking to several steps away
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " blinks to random location (" + CARDINAL_NUM_OF_STEPS + " steps away)";
    }

    /**
     * Get the destination location for the player to blink to
     *
     * @param currentLocation current location of player
     * @return destination location for the player to blink to
     */
    private Location getDestinationLocation(Location currentLocation){
        ArrayList<Location> validLocations = new ArrayList<>();

        int curXCoord = currentLocation.x();
        int curYCoord = currentLocation.y();

        // handle cardinal direction
        getCardinalLocation(currentLocation.map(), validLocations, curXCoord, curYCoord);

        // handle ordinal direction
        getOrdinalLocation(currentLocation.map(), validLocations, curXCoord, curYCoord);

        // if there are some valid locations to blink to, return the destination location randomly
        if (!validLocations.isEmpty()) {
            return validLocations.get(new Random().nextInt(validLocations.size()));
        }
        else {
            return null;
        }
    }

    /**
     * Get destination location in cardinal direction (north, south, east and west)
     *
     * @param map the map that contains the player
     * @param validLocations current valid locations for the player to blink to
     * @param curXCoord current x coordinates of the player
     * @param curYCoord current y coordinates of the player
     */
    private void getCardinalLocation(GameMap map, ArrayList<Location> validLocations, int curXCoord, int curYCoord) {
        Actor actor = map.getActorAt(map.at(curXCoord, curYCoord));

        // north & south
        if (map.getXRange().contains(curXCoord)){
            if (map.getYRange().contains(curYCoord + CARDINAL_NUM_OF_STEPS) && map.at(curXCoord, curYCoord + CARDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord, curYCoord + CARDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - CARDINAL_NUM_OF_STEPS) && map.at(curXCoord, curYCoord - CARDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord, curYCoord - CARDINAL_NUM_OF_STEPS));
            }
        }

        // east & west
        if (map.getYRange().contains(curYCoord)){
            if (map.getXRange().contains(curXCoord - CARDINAL_NUM_OF_STEPS) && map.at(curXCoord - CARDINAL_NUM_OF_STEPS, curYCoord).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - CARDINAL_NUM_OF_STEPS, curYCoord));
            }
            if (map.getXRange().contains(curXCoord + CARDINAL_NUM_OF_STEPS) && map.at(curXCoord + CARDINAL_NUM_OF_STEPS, curYCoord).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + CARDINAL_NUM_OF_STEPS, curYCoord));
            }
        }
    }

    /**
     * Get destination location in ordinal direction (NE, SE, NW and SW)
     *
     * @param map the map that contains the player
     * @param validLocations current valid locations for the player to blink to
     * @param curXCoord current x coordinates of the player
     * @param curYCoord current y coordinates of the player
     */
    private void getOrdinalLocation(GameMap map, ArrayList<Location> validLocations, int curXCoord, int curYCoord){
        Actor actor = map.getActorAt(map.at(curXCoord, curYCoord));

        // NE & SE
        if (map.getXRange().contains(curXCoord + ORDINAL_NUM_OF_STEPS)){
            if (map.getYRange().contains(curYCoord + ORDINAL_NUM_OF_STEPS) && map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - ORDINAL_NUM_OF_STEPS) && map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord + ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS));
            }
        }

        // NW & SW
        if (map.getXRange().contains(curXCoord - ORDINAL_NUM_OF_STEPS)){
            if (map.getYRange().contains(curYCoord + ORDINAL_NUM_OF_STEPS) && map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord + ORDINAL_NUM_OF_STEPS));
            }
            if (map.getYRange().contains(curYCoord - ORDINAL_NUM_OF_STEPS) && map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS).canActorEnter(actor)){
                validLocations.add(map.at(curXCoord - ORDINAL_NUM_OF_STEPS, curYCoord - ORDINAL_NUM_OF_STEPS));
            }
        }
    }
}
