package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that enables a player to teleport to a Warp Pipe in the Lava Zone
 * @author Mark Manlangit
 * @version 1.0
 * @since 22-05-2022
 */
public class TeleportAction extends Action{
    /**
     * A boolean representing whether the actor is in the original map
     */
    private static boolean ORIGINAL_MAP = true;

    /**
     * The source location of the current WarpPipe to teleport form
     */
    private Location warpSource;

    /**
     * The destination location of the current WarpPipe to teleport to
     */
    private static Location warpDestination;

    /**
     * A constructor for the TeleportAction class
     * @param location The location of the Warp Pipe that an actor is trying to Teleport from
     */
    public TeleportAction(Location location) {
        warpSource = location; // Set source location
    }

    /**
     * A method that performs a teleport to another map
     * @param actor The actor that will be teleported
     * @param map The map the actor is on.
     * @return A string representing where the actor was teleported to
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        // Remove any existing actors on the Warp Pipe at destination location
        if (warpDestination.map().isAnActorAt(warpDestination)) {
            warpDestination.map().removeActor(warpDestination.getActor());
        }
        warpDestination.map().moveActor(actor, warpDestination); // Move player to destination location
        warpDestination = warpSource; // Set warp source as new destination
        message = (ORIGINAL_MAP) ? "Teleported to Lava Zone" : "Teleported back to original world";
        ORIGINAL_MAP = !ORIGINAL_MAP;
        return message;
    }

    /**
     * A method that sets the destination location a TeleportAction will teleport to
     * @param location The location of the WarpPipe to teleport to
     */
    public static void setDestination(Location location) {
        warpDestination = location;
    }

    /**
     * Display the option to teleport to new map/original world
     * @param actor The player on top of a WarpPipe
     * @return the option to teleport to lava zone/original world
     */
    @Override
    public String menuDescription(Actor actor) {
        return (ORIGINAL_MAP) ? "Teleport to Lava Zone" : "Teleport back to original world";
    }
}
