package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.PatrolBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * A Luigi class which acts as a patrol unit
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Luigi extends Actor implements Resettable {
    /**
     * The name is set as a constant called Luigi
     */
    private static final String NAME = "Luigi";

    /**
     * The display character is set as a constant @
     */
    private static final char DISPLAY_CHAR = '@';

    /**
     * The hash map storing the behaviours with priorities
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     */
    public Luigi() {
        super(NAME, DISPLAY_CHAR, Integer.MAX_VALUE);
        this.behaviours.put(2, new PatrolBehaviour());
        this.registerInstance();
    }

    /**
     * Luigi will just patrol in each turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action either from PatrolBehaviour or DoNothingAction (reset)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.RESET)){
            this.removeCapability(Status.RESET);

            int xCoord = map.locationOf(this).x();

            // if the reset location contains an actor, then remove it
            if (map.at(map.locationOf(this).x(), PatrolBehaviour.getMaxYCoord()).containsAnActor()){
                map.removeActor(map.at(map.locationOf(this).x(), PatrolBehaviour.getMaxYCoord()).getActor());
            }

            // move Luigi to reset location
            map.moveActor(this, map.at(xCoord, PatrolBehaviour.getMaxYCoord()));
            display.println(this + " moves back to original position (" + xCoord + ", " + PatrolBehaviour.getMaxYCoord() + ")");
            return new DoNothingAction();
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
     * Add RESET status to Luigi during reset
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}

