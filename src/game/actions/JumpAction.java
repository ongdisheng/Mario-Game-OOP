package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.HighGround;

/**
 * A class that enables a player to attempt to jump to a High Ground
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class JumpAction extends Action {
    /**
     * The direction of the High Ground the actor is trying to jump to
     */
    private final String direction;

    /**
     * The location of the High Ground the actor is trying to jump to
     */
    private final Location highGroundLocation;

    /**
     * The High Ground the actor is trying to jump to
     */
    private final HighGround highGround;

    /**
     * A string representing the name of the High Ground and its coordinates
     */
    private final String coordinateString;

    /**
     * A constructor for the JumpAction class
     * @param highGround The High Ground the actor is trying to jump to
     * @param direction The direction of the High Ground that the actor is trying to jump to
     * @param highGroundLocation The location of the High Ground that the actor is trying to jump to
     */
    public JumpAction(HighGround highGround, String direction, Location highGroundLocation) {
        this.highGround = highGround;
        this.highGroundLocation = highGroundLocation;
        this.direction = direction;
        coordinateString = String.format("%s(%d, %d)", highGround, highGroundLocation.x(), highGroundLocation.y());
    }

    /**
     * A method that attempts to perform a jump
     * @param actor The actor attempting the jump
     * @param map The map the actor is in.
     * @return A string representing if the jump was successful or not
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + highGround.jumped(actor, highGroundLocation, map) + coordinateString + "!";
    }

    /**
     * Display the option to jump onto a high ground in the menu
     *
     * @param actor The actor next to a high ground
     * @return the description of a possible attempt to jump to a high ground
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s jumps on the %s to the %s", actor, coordinateString, direction);
    }
}
