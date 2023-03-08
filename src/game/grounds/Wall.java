package game.grounds;

/**
 * A class that represents a wall
 * @author Mark Manlangit
 * @version 1.0
 * @since 01-05-2022
 */
public class Wall extends HighGround {
	/**
	 * The jump chance related to a Wall (used for jumping)
	 */
	private static final double JUMP_CHANCE = 0.8;

	/**
	 * The fall damage related to a Wall (used for jumping)
	 */
	private static final int FALL_DAMAGE = 20;

	/**
	 * A constructor for the Sprout class
	 */
	public Wall() {
		super('#'); // display character
		setJumpChance(JUMP_CHANCE); // set jump chance of High Ground parent class based on a Wall's jump chance
		setFallDamage(FALL_DAMAGE); // set fall damage of High Ground parent class based on a Wall's fall damage
	}

	/**
	 * A method used to return the string representation of the Wall class
	 * @return The string representation of the Wall class
	 */
	@Override
	public String toString() {
		return "Wall";
	}
}
