package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.FlyingKoopa;
import game.enemies.Koopa;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Mature class is a type of Tree that is meant to represent the last stage that a Tree can be in
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Mature extends Tree {
    /**
     * The chance of death related to a Mature tree
     */
    private static final double DEATH_CHANCE = 0.20;

    /**
     * The spawn chance related to a Mature tree
     */
    private static final double SPAWN_CHANCE = 0.15;

    /**
     * The jump chance related to a Mature tree (used for jumping)
     */
    private static final double JUMP_CHANCE = 0.7;

    /**
     * The fall damage related to a Mature tree (used for jumping)
     */
    private static final int FALL_DAMAGE = 30;

    private static final double SPAWN_KOOPAS_RATE = 0.5;
    /**
     * A constructor for the Mature class
     */
    public Mature() {
        super('T'); // display character
        setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Mature tree's jump chance
        setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Mature tree's fall damage
    }

    /**
     * A method that is called so that this Mature tree can experience the passage of time
     * In this case to increment the age of the Mature tree, handle the spawning of new sprouts in the Mature tree's
     * surroundings, handle the spawning of Koopa enemies at each turn and handle the death of a Mature tree.
     * @param location The location of the Sapling
     */
    @Override
    public void tick(Location location) {
        age++;
        // every five turns, attempt to spawn a new Sprout tree
        if (age % 5 == 0) {
            // retrieve a list of available fertile grounds surrounding the Mature tree
            ArrayList<Location> fertileGrounds = findFertileGrounds(location);
            // randomly spawn a new Sprout tree at a surrounding fertile ground, if available
            spawnSprout(fertileGrounds);
        }
        // if there are no actors on this Mature tree and there is a successful spawn attempt, spawn a new Koopa enemy on the Mature tree's location
        if (Math.random() < SPAWN_CHANCE && !location.containsAnActor()) {
            if (Math.random() < SPAWN_KOOPAS_RATE) {
                location.addActor(new Koopa());
            }
            else
                location.addActor(new FlyingKoopa());
        }
        // if there is a successful death attempt, the Mature tree will wither and die (turn into a new Dirt ground)
        if (Math.random() < DEATH_CHANCE) {
            location.setGround(new Dirt());
        }
        super.tick(location); // call parent tick, used for reset
    }

    /**
     * A method that finds the locations of fertile grounds surrounding a particular location
     * @param location The location at which we are finding surrounding fertile grounds
     * @return A list of locations surrounding the target location that are fertile grounds
     */
    private ArrayList<Location> findFertileGrounds(Location location) {
        ArrayList<Location> fertileGrounds = new ArrayList<>();
        // loop through every possible surrounding location
        for (Exit exit: location.getExits()) {
            Location potentialFertileGround = exit.getDestination();
            // if the ground at the current location being evaluated has the fertile status, add it to the valid list
            if (potentialFertileGround.getGround().hasCapability(Status.FERTILE)) {
                fertileGrounds.add(potentialFertileGround);
            }
        }
        // return the list of valid surrounding locations that have a fertile ground
        return fertileGrounds;
    }

    /**
     * A method that spawns a new Sprout tree in a location chosen randomly from a given list
     * @param fertileGrounds A list of locations that have grounds with the fertile status
     */
    private void spawnSprout(ArrayList<Location> fertileGrounds) {
        // if the list of locations is not empty
        if (fertileGrounds.size() > 0) {
            Random rand = new Random();
            // choose the location to spawn the new Sprout randomly
            Location chosenGround = fertileGrounds.get(rand.nextInt(fertileGrounds.size()));
            // spawn the new Sprout at this randomly chosen location
            chosenGround.setGround(new Sprout());
        }
    }

    /**
     * A method used to return the string representation of the Mature class
     * @return The string representation of the Mature class
     */
    @Override
    public String toString() {
        return "Mature Tree";
    }
}
