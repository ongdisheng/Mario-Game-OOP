package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

/**
 * An ObtainBottleAction class to obtain bottle from Toad
 */
public class ObtainBottleAction extends Action {

    /**
     * Add bottle to the player's inventory
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string describing obtaining bottle from toad
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle()); // add bottle to player inventory
        return menuDescription(actor);
    }

    /**
     * Return a string describing obtaining bottle from toad
     *
     * @param actor The actor performing the action.
     * @return a string describing obtaining bottle from toad
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle from Toad";
    }
}
