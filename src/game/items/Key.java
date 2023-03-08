package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.enums.Status;

public class Key extends Item {

    /**
     * The name is set as a constant "Key" / whatever you think is appropriate
     */
    private static final String NAME = "Key";

    /**
     * The display character of the key is set as a constant 'k' / whatever you think is appropriate
     */
    private static final char KEY_CHAR = 'k';

    /***
     * Constructor.
     */
    public Key() {
        super(NAME, KEY_CHAR, true);
        this.addCapability(Status.VICTORY);
    }

}
