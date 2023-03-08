package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.behaviours.*;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.enums.Status;
import java.util.HashMap;
import java.util.Map;

/**
 * An Enemy class represents the enemy actor with their actions and behaviours.
 *
 * @author Kennedy Tan
 * @version 2.0
 * */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * Behaviours Hash map to store priority and  behaviour
     * */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(8, new AttackBehaviour());
        this.behaviours.put(9, new ConsumeBehaviour());
        this.registerInstance();
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //for resetting
        if (this.hasCapability(Status.RESET)){
            //remove actor from map
            map.removeActor(this);
        }
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // check if actor has HOSTILE_TO_ENEMY status
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //if yes, then add AttackAction to it
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Add RESET capability to enemy
     * */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
