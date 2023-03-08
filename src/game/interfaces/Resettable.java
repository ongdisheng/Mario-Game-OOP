package game.interfaces;

import game.managers.ResetManager;

/**
 * A Resettable interface which should be implemented by those affected when the game is reset
 *
 * @author Ong Di Sheng
 * @version 1.0
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void resetInstance();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
