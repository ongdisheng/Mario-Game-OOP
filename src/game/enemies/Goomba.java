package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.ConsumeBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A little fungus guy. Goomba is one of the enemy in the game.
 *
 * @author Kennedy Tan
 * @version 1.0
 */
public class Goomba extends Enemy {

	/**
	 * Behaviours Hash map to store priority and  behaviour
	 * */
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
	/**
	 * The intrinsice damage is set as a constant 10
	 */
	private static final int INTRINSIC_DAMAGE = 10;
	/**
	 * The damage verb is set as a constant String "kick"
	 */
	private static final String DAMAGE_VERB = "kick";
	/**
	 * The name of Goomba is set as a constant String "Goomba"
	 */
	public static final String NAME = "Goomba";
	/**
	 * The display char is set as a constant char 'g'
	 */
	public static final char DISPLAY_CHAR = 'g';
	/**
	 * The hitpoints is set as a constant 20
	 */
	public static final int HITPOINTS = 20;
	/**
	 * The suicide rate is set as a constant 10
	 */
	private final int SUICIDE_RATE = 10;
	/**
	 * The hitpoints is set as a constant 20
	 */
	private Random rand = new Random();

	private static final int POWER_BASE_DAMAGE = 15;

	private int numOfPowerWaterConsumption = 0;

	/**
	 * Constructor. Add WanderBehaviour and AttackBehaviour to Goomba
	 */
	public Goomba() {
		super(NAME, DISPLAY_CHAR, HITPOINTS);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(8, new AttackBehaviour());
		this.behaviours.put(9, new ConsumeBehaviour());
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * By default, the Actor 'punches' for 5 damage. Override this method to create
	 * an Actor with description and damage using constant variable declared
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 * @see Actor getIntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		IntrinsicWeapon intrinsicWeapon = new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
		if (this.hasCapability(Status.POWER)){
			this.removeCapability(Status.POWER);
		}
		return new IntrinsicWeapon(intrinsicWeapon.damage() + POWER_BASE_DAMAGE * numOfPowerWaterConsumption, intrinsicWeapon.verb());
	}

	/**
	 * Figure out what to do next. Override playTurn method and add in
	 * reset method and suicideAction
	 *
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 * @return Action
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (this.hasCapability(Status.POWER)) {
			numOfPowerWaterConsumption++;
		}

		Location actorLocation = map.locationOf(this);
		super.playTurn(actions, lastAction, map, display);

		//method used to remove Goomba when reset
		if (!(map.contains(this))){
			return new SuicideAction(actorLocation);
		}

		//goomba will suicide with certain rate
		if (rand.nextInt(100) <= SUICIDE_RATE || !this.isConscious()){
			//remove goomba from map
			map.removeActor(this);
			return new SuicideAction(actorLocation);
		}

		//loop through behaviours and get actions
		for(Behaviour Behaviour : behaviours.values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}

		//return action that do nothing
		return new DoNothingAction();
	}
}
