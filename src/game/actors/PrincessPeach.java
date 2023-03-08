package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.actions.RescueAction;
import game.enums.Status;
import game.items.Key;

public class PrincessPeach extends Actor {

    /**
     * The name is set as a constant "PrincessPeach" / whatever you think is appropriate
     */
    private static final String NAME = "Princess Peach";

    /**
     * The display character of toad is set as a constant 'P'
     */
    private static final char PRINCESS_PEACH_CHAR = 'P';

    /**
     * Constructor.
     */
    public PrincessPeach() {
        super(NAME, PRINCESS_PEACH_CHAR, Integer.MAX_VALUE);
    }

    /**
     * Return actions taken by PrincessPeach
     *
     * @param otherActor the Actor that performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     * */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.VICTORY)) {
            actions.add(new RescueAction(this));
        }
        return actions;
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
        return new DoNothingAction();
    }

}
