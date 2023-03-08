package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.Status;
import game.interfaces.Tradable;

/**
 * Wrench is the only weapon to destroy Koopa's shell
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public class Wrench extends WeaponItem implements Tradable {
    /**
     * The price of wrench is set as a constant $200
     */
    private static final int PRICE = 200;

    /**
     * The hit rate of wrench is set as a constant 80%
     */
    private static final int HIT_RATE = 80;

    /**
     * The damage of wrench is set as a constant 50
     */
    private static final int DAMAGE = 50;

    /**
     * The name is set as a constant "Wrench" / whatever you think is appropriate
     */
    private static final String NAME = "Wrench";

    /**
     * The display character of wrench is set as a constant 'w' / whatever you think is appropriate
     */
    private static final char WRENCH_CHAR = 'w';

    /**
     * The verb used by wrench when carrying out an attack
     */
    private static final String WRENCH_VERB = "whacks";

    /**
     * Constructor to create a wrench object
     *
     */
    public Wrench() {
        super(NAME, WRENCH_CHAR, DAMAGE, WRENCH_VERB, HIT_RATE);
        this.addCapability(Status.CAN_SMASH_KOOPA_SHELL);   // every wrench has the capability to smash koopa's shell
    }

    /**
     * Get the price of wrench
     *
     * @return the price of wrench
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Add a capability to wrench which is specific to the trading action only
     */
    @Override
    public void addCapabilityDuringTrading() {
        this.addCapability(Status.CAN_SMASH_KOOPA_SHELL);
    }

}
