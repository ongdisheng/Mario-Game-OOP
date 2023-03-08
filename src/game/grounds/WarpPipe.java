package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.enemies.PiranhaPlant;
import game.enums.Status;
import game.interfaces.Resettable;

/**
 * The WarpPipe class is a type of High Ground that allows the player to teleport back and forth between maps
 * @author Mark Manlangit
 * @version 1.0
 * @since 22-05-2022
 */
public class WarpPipe extends HighGround implements Resettable {
    /**
     * The jump chance related to a WarpPipe (used for jumping)
     */
    private static final double JUMP_CHANCE = 1.0;
    /**
     * The fall damage related to a WarpPipe (used for jumping)
     */
    private static final int FALL_DAMAGE = 0;
    /**
     * The age of the WarpPipe (used for spawning Piranha Plant)
     */
    private int age;

    /**
     * A constructor for the WarpPipe class
     */
    public WarpPipe() {
        super('C'); // display character
        setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Warp pipe's jump chance
        setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Warp pipe's fall damage
        age = 0;
        this.addCapability(Status.INDESTRUCTIBLE); // WarpPipe cannot be destroyed
        this.registerInstance();
    }

    /**
     * A method that is called so that this WarpPipe can experience the passage of time
     * @param location The location of the WarpPipe
     */
    @Override
    public void tick(Location location) {
        age++;
        // spawn PiranhaPlant after 1 turn
        if (age == 1) {
            if (!location.containsAnActor()) {
                location.addActor(new PiranhaPlant());
            }
        }
        super.tick(location); // call parent tick
    }

    /**
     * A method that returns a list of actions that can be performed on this WarpPipe
     * @param actor the Actor performing an action
     * @param location the current Location of the WarpPipe
     * @param direction the direction of the WarpPipe from the Actor
     * @return a list of actions that can be performed on this WarpPipe
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);
        // if player is on this WarpPipe, add new TeleportAction
        if (location.containsAnActor() && location.getActor() == actor && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new TeleportAction(location));
        }
        return actions;
    }

    /**
     * A method used to return the string representation of the WarpPipe class
     * @return The string representation of the WarpPipe class
     */
    @Override
    public String toString() {
        return "Warp Pipe";
    }

    /**
     * Reset WarpPipe age (for spawning PiranhaPlant after reset)
     */
    @Override
    public void resetInstance() {
        this.age = 0;
    }



}
