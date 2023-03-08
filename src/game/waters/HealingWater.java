package game.waters;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A HealingWater class that increases the drinker's hit points/healing by 50 hit points.
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class HealingWater extends Water {
    /**
     * The name is set as a constant called Healing water
     */
    private static final String NAME = "Healing water";

    /**
     * The display character is set as a constant ~
     */
    private static final char DISPLAY_CHAR = '~';

    /**
     * The healing hitpoint is set as a constant 50
     */
    private static final int HEAL_HITPOINT = 50;

    /***
     * Constructor.
     */
    public HealingWater() {
        super(NAME, DISPLAY_CHAR, false);
    }

    /**
     * When HealingWater is consumed, the actor will be healed by 50 hitpoint
     *
     * @param actor actor that consumes Magical items
     */
    @Override
    public void consumeMagicalItems(Actor actor) {
        actor.heal(HEAL_HITPOINT);
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
