package game.waters;

import edu.monash.fit2099.engine.actors.Actor;
import game.enums.Status;

/**
 * A PowerWater class that increases the drinker's base/intrinsic attack damage by 15.
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class PowerWater extends Water {
    /**
     * The name is set as a constant called Power water
     */
    private static final String NAME = "Power water";

    /**
     * The display character is set as a constant `
     */
    private static final char DISPLAY_CHAR = '`';

    /***
     * Constructor.
     */
    public PowerWater() {
        super(NAME, DISPLAY_CHAR, false);
    }

    /**
     * When PowerWater is consumed, the actor will gain POWER status
     *
     * @param actor actor that consumes Magical items
     */
    @Override
    public void consumeMagicalItems(Actor actor) {
        actor.addCapability(Status.POWER);
    }

    /**
     * Return the name of this water
     *
     * @return the name of this water
     */
    @Override
    public String toString() {
        return NAME;
    }

}
