package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

/**
 * The Sapling class is a type of Tree that is meant to represent the second stage that a Tree can be in
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Sapling extends Tree {
    /**
     * The spawn chance related to a Sapling tree
     */
    private static final double SPAWN_CHANCE = 0.1;

    /**
     * The jump chance related to a Sapling tree (used for jumping)
     */
    private static final double JUMP_CHANCE = 0.8;

    /**
     * The fall damage related to a Sapling tree (used for jumping)
     */
    private static final int FALL_DAMAGE = 20;

    /**
     * A constructor for the Sapling class
     */
    public Sapling() {
        super('t'); // display character
        setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Sapling tree's jump chance
        setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Sapling tree's fall damage
    }

    /**
     * A method that is called so that this Sapling tree can experience the passage of time
     * In this case to increment the age of the Sapling, handle the growth of the Sapling into the next stage
     * and handle the spawning of $20 Coin items at each turn
     * @param location The location of the Sapling
     */
    @Override
    public void tick(Location location) {
        age++;
        // if current age is equal to the growth age, replace Sapling at this location with a new Mature
        if (age == GROWTH_AGE) {
            location.setGround(new Mature());
        }
        // if there is a successful spawn attempt, add a new $20 Coin on the Sapling's location
        if (Math.random() < SPAWN_CHANCE) {
            location.addItem(new Coin(20));
        }
        super.tick(location); // call parent tick, used for reset
    }

    /**
     * A method used to return the string representation of the Sapling class
     * @return The string representation of the Sapling class
     */
    @Override
    public String toString() {
        return "Sapling Tree";
    }
}
