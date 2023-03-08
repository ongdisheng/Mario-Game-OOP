package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.DestroyAction;
import game.actions.SuicideAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.ConsumeBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class AnnoyingKoopa extends Enemy{

    /**
     * Behaviours Hash map to store priority and  behaviour
     * */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * The intrinsic damage is set as a constant 30
     */
    private static final int INTRINSIC_DAMAGE = 30;
    /**
     * The damage verb is set as a constant String "punch"
     */
    private static final String DAMAGE_VERB = "punch";
    /**
     * The display char is set as a constant 'D'
     */
    private static final char DORMANT_CHAR = 'D';
    /**
     * Power base damage
     */
    private static final int POWER_BASE_DAMAGE = 15;
    /**
     * Number of Power Water Consumption
     */
    private int numOfPowerWaterConsumption = 0;

    /**
     * Constructor for AnnoyingKoopa
     * */
    public AnnoyingKoopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(8, new AttackBehaviour());
        this.behaviours.put(9, new ConsumeBehaviour());
        this.addCapability(Status.CAN_ENTER_SHELL);
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Intrinsic Weapon with constant variable initialised before.
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
     * Return actions taken by Koopa in different state
     *
     * @param otherActor the Actor that performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     * */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        //check if PLayer is in HOSTILE_TO_ENEMY state
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            //check if Koopa is conscious
            if (this.isConscious()) {
                //add AttackAction to Player to attack enemy
                actions.add(new AttackAction(this, direction));
            }
            //check if Player has a wrench
            else if (otherActor.hasCapability(Status.CAN_SMASH_KOOPA_SHELL)){
                //add DestroyAction to Player to destroy Koopa shell
                actions.add(new DestroyAction(this, direction));
            }
        }

        return actions;
    }

    /**
     * Override playTurn method from Actor class to add in SuicideAction method
     * and return super class's action
     *
     * @return action
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.POWER)) {
            numOfPowerWaterConsumption++;
        }
        Location actorLocation = map.locationOf(this);
        super.playTurn(actions, lastAction, map, display);

        //method used to remove Koopa when reset
        if (!(map.contains(this))){
            return new SuicideAction(actorLocation);
        }
        //check if Koopa is conscious
        if ((!this.isConscious()) || (this.hasCapability(Status.ON_FIRE) && this.getHp() <= 20)){
            //set display char to 'D'
            this.setDisplayChar(DORMANT_CHAR);
            this.removeCapability(Status.ON_FIRE);
            return new DoNothingAction();
        }

        //loop through behaviours and get action
        for(game.interfaces.Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        //return action in super class
        return new DoNothingAction();
    }

    /**
     * Method to get the current hp for Koopas
     * @return current hp
     */
    public int getHp() {
        int hp = 0;
        String currentHp = this.printHp();
        hp = Integer.parseInt(currentHp.substring(1,3));
        return hp;
    }
}
