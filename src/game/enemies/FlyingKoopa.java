package game.enemies;

import game.enums.Status;


/**
 * Koopa is one of the enemy that will attack the Player
 *
 * @author Kennedy Tan
 * @version 2.0
 * */
public class FlyingKoopa extends AnnoyingKoopa {

    /**
     * The name of FlyingKoopa is set as a constant String "FlyingKoopa"
     */
    public static final String NAME = "Flying Koopa";
    /**
     * The display char is set as a constant char 'K'
     */
    public static final char DISPLAY_CHAR = 'F';
    /**
     * The hitpoints is set as a constant 150
     */
    public static final int HITPOINTS = 150;


    /**
     * Constructor for FLyingKoopa
     */
    public FlyingKoopa() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
        this.addCapability(Status.CAN_ENTER_HIGH_GROUND);
    }

}
