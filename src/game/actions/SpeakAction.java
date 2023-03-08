package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * A SpeakAction class where the Toad will speak one sentence at a time randomly
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class SpeakAction extends Action {
    /**
     * Actor who speaks the message
     */
    private Actor target;

    /**
     * If the player has a wrench, then the message at index 0 in TOAD_MSG array is excluded
     */
    private static final int EXCLUDE_WRENCH_MSG = 0;

    /**
     * If the player is in INVINCIBLE status, then the message at index 1 in TOAD_MSG array is excluded
     */
    private static final int EXCLUDE_POWER_STAR_MSG = 1;

    /**
     * A string array storing all the available messages that can be spoken by Toad
     */
    private static final String[] TOAD_MSG = {"You might need a wrench to smash Koopa's hard shells.",
                                              "You better get back to finding the Power Stars.",
                                              "The Princess is depending on you! You are our only hope.",
                                              "Being imprisoned in these walls can drive a fungus crazy :("};

    /**
     * Constructor for creating SpeakAction
     *
     * @param target actor who speaks the message
     */
    public SpeakAction(Actor target){
        this.target = target;
    }

    /**
     * Get the random message spoken by Toad
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the random message spoken by Toad
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Integer> excludeMsgIndexList = new ArrayList<>(); // arraylist storing index of the message to be excluded from showing up in the menu

        // check for wrench in inventory
        if (actor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
            excludeMsgIndexList.add(EXCLUDE_WRENCH_MSG);    // exclude the wrench message
        }

        // check power star effect
        if (actor.hasCapability(Status.INVINCIBLE)){
            excludeMsgIndexList.add(EXCLUDE_POWER_STAR_MSG);    // exclude power star message
        }

        return target + ": " + getRandomMsg(excludeMsgIndexList);
    }

    /**
     * Display the speak action message in the menu
     *
     * @param actor The actor performing the action.
     * @return a string describing the talk action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + target;
    }

    /**
     * Get random message to be shown in the display
     *
     * @param excludeMsgIndexList the arraylist storing the integer index of messages that are to be excluded
     * @return a random message after filtering out those excluded messages
     */
    private String getRandomMsg(ArrayList<Integer> excludeMsgIndexList){
        ArrayList<String> msg = new ArrayList<>();

        // loop through TOAD_MSG
        for (int i = 0; i < TOAD_MSG.length; i++){
            // if index i is not in excludeMsgIndexList, then the message at index i of TOAD_MSG is added
            if (!(excludeMsgIndexList.contains(i))){
                msg.add(TOAD_MSG[i]);
            }
        }

        // get the random remaining message
        return msg.get(new Random().nextInt(msg.size()));
    }

}
