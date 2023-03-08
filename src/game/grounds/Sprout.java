package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Goomba;

/**
 * The Sprout class is a type of Tree that is meant to represent the first stage that a Tree can be in
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Sprout extends Tree {
    /**
     * The spawn chance related to a Sprout tree
     */
    private static final double SPAWN_CHANCE = 0.1;

    /**
     * The jump chance related to a Sprout tree (used for jumping)
     */
    private static final double JUMP_CHANCE = 0.9;

    /**
     * The fall damage related to a Sprout tree (used for jumping)
     */
    private static final int FALL_DAMAGE = 10;

    /**
     * A constructor for the Sprout class
     */
    public Sprout() {
        super('+'); // display character
        setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Sprout tree's jump chance
        setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Sprout tree's fall damage
    }

    /**
     * A method that is called so that this Sprout tree can experience the passage of time
     * In this case to increment the age of the Sprout, handle the growth of the Sprout into the next stage
     * and handle the spawning of Goomba enemies at each turn
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        age++;
        // if current age is equal to the growth age, replace Sprout at this location with a new Sapling
        if (age == GROWTH_AGE) {
            location.setGround(new Sapling());
        }
        // if there are no actors on this Sprout and there is a successful spawn attempt, spawn a new Goomba enemy on the Sprout's location
        if (Math.random() < SPAWN_CHANCE && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        super.tick(location); // call parent tick, used for reset
    }

    /**
     * A method used to return the string representation of the Sprout class
     * @return The string representation of the Sprout class
     */
    @Override
    public String toString() {
        return "Sprout Tree";
    }
}
