package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;
import game.interfaces.Resettable;

/**
 * The Tree class is a type of High Ground that is meant to represent a Tree in the game
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * The age of this Tree
     */
    protected int age = 0;
    /**
     * The age at which this Tree can grow into a next stage
     */
    protected static final int GROWTH_AGE = 10;
    /**
     * The chance that this Tree will be turned into dirt during a reset action
     */
    private static final double RESET_CHANCE = 0.5;

    /**
     * A constructor for the Tree class
     * @param displayChar The character that represents the Tree when displayed
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();    // tree is resettable
    }

    /**
     * A method that is called so that this Tree can experience the passage of time
     * In this case to check whether to turn this Tree into dirt during a reset
     * @param location The location of the Tree
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        // if tree has RESET status and succeeds in a reset
        if (this.hasCapability(Status.RESET) && Math.random() < RESET_CHANCE){
            location.setGround(new Dirt()); // convert to dirt
        }
    }

    /**
     * A method used to add the RESET status to tree during a reset action
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
