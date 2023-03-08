package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * An abstract Fountain class that extends Ground class
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public abstract class Fountain extends Ground {
    /**
     * max capacity of each fountain is set as a constant 10
     */
    private static final int MAX_CAPACITY = 10;

    /**
     * current available water slot
     */
    private int slot;

    /**
     * recharge timer of each fountain is 5
     */
    private int rechargeTimer = 5;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        this.slot = MAX_CAPACITY;   // initial available water slot is set as MAX_CAPACITY
    }

    /**
     * Return the current available water slot
     *
     * @return the current available water slot
     */
    public int getSlot(){
        return slot;
    }

    /**
     * Subtract the current available water slot
     */
    public void subtractSlot(){
        slot--;
    }

    /**
     * Recharge fountain to have max capacity of water slots after 5 turns
     */
    private void rechargeFountain(){
        slot = MAX_CAPACITY;    // set current water slot as MAX_CAPACITY
        rechargeTimer = 5;  // reset rechargeTimer to 5 again
    }

    /**
     * Retrieve maximum capacity that can be hold in each fountain
     *
     * @return maximum capacity that can be hold in each fountain
     */
    public int getMaxCapacity(){
        return MAX_CAPACITY;
    }

    /**
     * Used for checking whether fountain should be recharged in current turn
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // if no more water
        if (slot == 0){
            // decrement rechargeTimer
            rechargeTimer--;
            // if rechargeTimer has used up 5 turns. then rechargeFountain
            if (rechargeTimer < 0){
                rechargeFountain();
            }
        }
    }

}
