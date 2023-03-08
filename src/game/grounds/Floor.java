package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Floor extends Ground {
	/**
	 * A constructor for the Floor class
	 */
	public Floor() {
		super('_'); // display character
	}

	/**
	 * A method that makes this terrain impassible if the actor trying to enter is an enemy
	 * @param actor the Actor to check
	 * @return a boolean representing whether the actor trying to enter the Floor is not an enemy
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
	}

	/**
	 * A method used to return the string representation of the Floor class
	 * @return The string representation of the Floor class
	 */
	@Override
	public String toString() {
		return "Floor";
	}
}
