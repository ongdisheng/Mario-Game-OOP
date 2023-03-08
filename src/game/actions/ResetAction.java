package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.managers.ResetManager;

/**
 * A ResetAction class that enables the game to be reset only once
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class ResetAction extends Action {
    /**
     * A boolean flag indicating whether the reset has been done to the game
     */
    private static boolean resetFlag = false;

    /**
     * Execute reset action to reset the game
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a brief description on the game has been reset
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();   // run reset instance method for every resettable object
        resetFlag = true;   // set to true so that we cannot reset the game anymore
        return "Game has been reset";
    }

    /**
     * Display the reset action message in the menu
     *
     * @param actor The actor performing the action.
     * @return a string describing the reset game action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset game";
    }

    /**
     * The string hotkey used by reset action
     *
     * @return string hotkey "r" used by reset action
     */
    @Override
    public String hotkey() {
        return "r";
    }

    /**
     * Get the reset flag to see if the game has been reset
     *
     * @return boolean reset flag
     */
    public static boolean getResetFlag(){
        return resetFlag;
    }
}

