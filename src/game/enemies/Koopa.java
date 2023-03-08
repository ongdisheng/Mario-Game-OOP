package game.enemies;

/**
 * Koopa is one of the enemy that will attack the Player
 *
 * @author Kennedy Tan
 * @version 2.0
 * */
public class Koopa extends AnnoyingKoopa {

    /**
     * The name of Koopa is set as a constant String "Koopa"
     */
    public static final String NAME = "Koopa";
    /**
     * The display char is set as a constant char 'K'
     */
    public static final char DISPLAY_CHAR = 'K';
    /**
     * The hitpoints is set as a constant 100
     */
    public static final int HITPOINTS = 100;


    /**
     * Constructor for Koopa
     */
    public Koopa() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
    }
}
