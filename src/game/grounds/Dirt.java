package game.grounds;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

/**
 * A class that represents bare dirt.
 * @author Mark Manlangit
 * @editor Kennedy Tan
 * @version 2.0
 * @since 01-05-2022
 */
public class Dirt extends Ground {

/*	*//**
	 * Number of turns to keep the ground on fire
	 *//*
	private int burningTurn = 3;
	*//**
	 * Number of turns to start to let the player on fire
	 *//*
	private int startBurningTurn = 4;
	*//**
	 * Create display object to print message to console
	 *//*
	Display display = new Display();
	*//**
	 * Character to display fire
	 *//*
	private static final char fireChar = 'v';*/

	/**
	 * Character to display dirt
	 */
	private static final char dirtChar = '.';

	/**
	 * A constructor for the Dirt class
	 */
	public Dirt() {
		super(dirtChar); // display character
		this.addCapability(Status.FERTILE); // add fertile status to Dirt grounds
	}

/*	*//**
	 * Override tick method to check the ground every turn
	 *
	 * @param location The location of the Ground
	 *//*
	@Override
	public void tick(Location location){
		//check if the ground is burning
		if (location.getGround().hasCapability(Status.IS_BURNING) && burningTurn > 0){
			//set character to 'v'
			this.setDisplayChar(fireChar);
			//check if the location contains actor
			if (location.containsAnActor()){
				edu.monash.fit2099.engine.actors.Actor target = location.getActor();
				//check if target can be hurt by fire
				if ((target.hasCapability(Status.HOSTILE_TO_ENEMY) || target.hasCapability(Status.HOSTILE_TO_PLAYER)) && startBurningTurn <= 3){
					//display message to console
					display.println(target + " is on fire, receives 20 damage! ");
					target.hurt(20);
				}
			}
			burningTurn--;
			startBurningTurn--;
		}
		//check if ground is burning and burningTurn is zero
		else if (location.getGround().hasCapability(Status.IS_BURNING) && burningTurn == 0){
			//remove burning capability from ground
			location.getGround().removeCapability(Status.IS_BURNING);
			//display stop burning message to console
			display.println(this + " has stopped burning ");
			burningTurn = 3;
			startBurningTurn = 4;
			//set character back to '.'
			this.setDisplayChar(dirtChar);
		}
		super.tick(location);
	}*/

	/**
	 * A method used to return the string representation of the Dirt class
	 * @return The string representation of the Dirt class
	 */
	@Override
	public String toString() {
		return "Dirt";
	}
}
