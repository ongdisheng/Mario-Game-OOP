package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A class that represents a blazing fire ground that inflicts damage when the player steps on it.
 * @author Mark Manlangit
 * @version 1.0
 * @since 09-05-2022
 */
public class Lava extends Ground {
    /**
     * The damage an actor will take when standing on this ground
     */
    private static final int DAMAGE = 15;

    /**
     * A constructor for the Lava class
     */
    public Lava() {
        super('L'); // display character
    }

    /**
     * A method used to return the string representation of the Lava class
     * @return The string representation of the Lava class
     */
    @Override
    public String toString() {
        return "Lava";
    }


    /**
     * Returns true if an Actor can enter this location.
     * Actors can enter this location if they do not have the status HOSTILE_TO_PLAYER (enemy)
     * @param actor the Actor to check
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
    }

    /**
     * A method that is called so that this lava ground can experience the passage of time
     * In this case to deal damage to an actor standing on this ground
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            location.getActor().hurt(DAMAGE);
        }
    }
}
