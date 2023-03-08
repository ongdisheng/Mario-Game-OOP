package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviours.FollowBehaviour;
import game.behaviours.HealBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * A Yoshi class which acts as player's adventure partner
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Yoshi extends Actor implements Resettable {
    /**
     * The name is set as a constant called Yoshi
     */
    private static final String NAME = "Yoshi";
    /**
     * The display character is set as a constant !
     */
    private static final char DISPLAY_CHAR = '!';

    /**
     * The hash map that contains behaviours with priorities
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * The reset x coordinates is set as a constant 43
     */
    private static final int RESET_X_COORD = 43;

    /**
     * The reset y coordinates is set as a constant 10
     */
    private static final int RESET_Y_COORD = 10;


    /**
     * Constructor.
     */
    public Yoshi() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);
        this.addCapability(Status.CAN_ENTER_HIGH_GROUND);
        this.registerInstance();
    }

    /**
     * Follow the player in each turn if the player is nearby
     * Heal the player if the player is attacked by enemies
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action taken by Yoshi in each turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // deal with reset
        if (this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);

            // if reset coordinates contains actor, then remove it
            if (map.at(RESET_X_COORD, RESET_Y_COORD).containsAnActor()){
                map.removeActor(map.at(RESET_X_COORD, RESET_Y_COORD).getActor());
            }

            // move Yoshi to the reset coordinates
            map.moveActor(this, map.at(RESET_X_COORD, RESET_Y_COORD));
            display.println(this + " moves back to original position (" + RESET_X_COORD + ", " + RESET_Y_COORD + ")");

            return new DoNothingAction();
        }

        behaviours.clear();
        Actor player = findPlayer(map); // find player in the map
        // if player exists and nearby to Yoshi
        if (player != null && distance(map.locationOf(player), map.locationOf(this)) <= 2){
            this.behaviours.put(2, new FollowBehaviour(player));    // add follow behaviour
            // if player is injured, then heal the player
            if (player.hasCapability(Status.INJURED)){
                display.println(new HealBehaviour(player).getAction(this, map).execute(this, map));
                player.removeCapability(Status.INJURED);
            }
        }

        else {
            display.println(this + ": Hey, your adventure partner is right over here (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")");
        }

        //loop through behaviours and get actions
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        //return action that do nothing
        return new DoNothingAction();
    }

    /**
     * Add RESET status during reset
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

    /**
     * Find the player in the map
     *
     * @param map the map where it might contain the player
     * @return the player if exists
     */
    private Actor findPlayer(GameMap map){
        for (int y : map.getYRange()){
            for (int x : map.getXRange()){
                if (map.at(x, y).containsAnActor() && map.at(x, y).getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                    return map.at(x, y).getActor();
                }
            }
        }
        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }



}
