package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ObtainBottleAction;
import game.actions.SpeakAction;
import game.actions.TradeAction;
import game.enums.Status;
import game.interfaces.Tradable;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

import java.util.ArrayList;

/**
 * A Toad class is a friendly actor standing in the middle of the map where the player can buy some items from it
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Toad extends Actor {
    /**
     * The name is set as a constant "Toad" / whatever you think is appropriate
     */
    private static final String NAME = "Toad";

    /**
     * The display character of toad is set as a constant 'O'
     */
    private static final char TOAD_CHAR = 'O';

    /**
     * The tradable items sold by Toad
     */
    private ArrayList<Tradable> tradableList = new ArrayList<>();

    /**
     * Constructor.
     */
    public Toad() {
        super(NAME, TOAD_CHAR, Integer.MAX_VALUE);
    }

    /**
     * Return the action used by Toad in the current turn (do nothing)
     *
     * @param actions    collection of possible Actions for Toad
     * @param lastAction The Action took by Toad last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing Toad
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Return the list of actions that can be performed by Toad
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions that can be performed by Toad
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if (!otherActor.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY)) {
            actions.add(new ObtainBottleAction());
        }

        // add items that are tradable to the list
        addToTradableList();

        // loop through different types of tradable item
        for (Tradable item : tradableList) {
            actions.add(new TradeAction(item)); // add a trade action
        }

        // reset the tradable list
        tradableList.clear();

        // add a speak action
        actions.add(new SpeakAction(this));

        return actions;
    }

    /**
     * Add items that are sold by Toad to the tradable list
     */
    private void addToTradableList(){
        tradableList.add(new PowerStar());  // add power star
        tradableList.add(new SuperMushroom());  // add super mushroom
        tradableList.add(new Wrench()); // add wrench
    }

}