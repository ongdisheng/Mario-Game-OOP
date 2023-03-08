package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Wallet;
import game.actions.ConsumeAction;
import game.actions.ResetAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Consumable;
import game.items.Bottle;
import game.items.PowerStar;
import game.managers.ConsumableItemManager;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	private final Menu menu = new Menu();

	private static int noOfTurn = 10;

	private static final int POWER_BASE_DAMAGE = 15;

	private int numOfPowerWaterConsumption = 0;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		//add HOSTILE_TO_ENEMY capability  to player
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.registerInstance();
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		//print Player's hitpoints to console
		display.println(this +  printHp() + " at " + map.locationOf(this).getGround());
		//print Player's wallet balance to console
		display.println(this + "'s current balance: $" + Wallet.getBalance());

		if (this.hasCapability(Status.POWER)){
			this.removeCapability(Status.POWER);
			numOfPowerWaterConsumption++;
		}

		//reset action
		if (!(ResetAction.getResetFlag())){
			actions.add(new ResetAction());
		}
		//check if Player is invincible
		if (hasCapability(Status.INVINCIBLE)){
			//display message in console
			display.println("Mario is INVINCIBLE!");
			display.println("Mario consumes Power Star - " + PowerStar.getInvincibleTurn() +" turns remaining");
			//decreament of number of turns every round
			//noOfTurn -=1;
			PowerStar.subtractInvincibleTurn();
		}
		//if number of turns is less than one
		if (PowerStar.getInvincibleTurn() < 1){
			//remove the invincible capability from Player
			removeCapability(Status.INVINCIBLE);
		}

		//loop through item in Player's inventory
		for (Item item: this.getInventory()){
			if (item.hasCapability(Status.NON_REMOVABLE_FROM_INVENTORY) && Bottle.getNumOfWaters() == 0){
				continue;
			}

			//get consumable item from consumableList
			Consumable consumable = ConsumableItemManager.getInstance().getConsumableItem(item);
			//check if consumable item is null
			if (consumable != null){
				//add ConsumeAction to Player
				actions.add(new ConsumeAction(consumable));
			}
		}

		// Handle multi-noOfTurns Actions (given)
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu (given)
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Get display character
	 *
	 * @return display character
	 */
	@Override
	public char getDisplayChar(){
		//check if Player is Invincible
		if (this.hasCapability(Status.TALL)){
			//change character to uppercase
			return Character.toUpperCase(super.getDisplayChar());
		} else {
			//return normal character
			return super.getDisplayChar();
		}
	}

	/**
	 * Reset Player's character back to normal
	 */
	@Override
	public void resetInstance() {
		//check if Player is Tall
		if (this.hasCapability(Status.TALL)) {
			this.setDisplayChar(Character.toLowerCase(super.getDisplayChar()));
			this.removeCapability(Status.TALL);
		}
		//remove Invincible capability from Player
		if (this.hasCapability(Status.INVINCIBLE)) {
			this.removeCapability(Status.INVINCIBLE);
		}
		//reset Player's hitpoints
		this.resetMaxHp(this.getMaxHp());
	}

	/**
	 * Return Player's character back to normal if attacked by enemy,
	 * also decrease the health of Player
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points){
		if (this.hasCapability(Status.TALL)){
			this.removeCapability(Status.TALL);
		}
		else {
			super.hurt(points);
		}
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon intrinsicWeapon = super.getIntrinsicWeapon();
		return new IntrinsicWeapon(intrinsicWeapon.damage() + POWER_BASE_DAMAGE * numOfPowerWaterConsumption, intrinsicWeapon.verb());
	}

}
