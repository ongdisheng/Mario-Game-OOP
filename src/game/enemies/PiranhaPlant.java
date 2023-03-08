package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.items.Key;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PiranhaPlant extends Enemy {
    /**
     * Behaviours Hash map to store priority and  behaviour
     * */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    /**
     * The intrinsice damage is set as a constant 90
     */
    private static final int INTRINSIC_DAMAGE = 90;
    /**
     * The damage verb is set as a constant String "chomps"
     */
    private static final String DAMAGE_VERB = "chomps";
    /**
     * The name of Piranha Plant is set as a constant String "Piranha Plant"
     */
    public static final String NAME = "Piranha Plant";
    /**
     * The display char is set as a constant char 'Y'
     */
    public static final char DISPLAY_CHAR = 'Y';
    /**
     * The hitpoints is set as a constant 150
     */
    public static final int HITPOINTS = 150;
    /**
     * The hitpoints is set as a constant 20
     */
    private final Random rand = new Random();
    /**
     * Number of Power water consumption
     */
    private int numOfPowerWaterConsumption = 0;

    /**
     * Constructor for PiranhaPlant
     */
    public PiranhaPlant() {
        super(NAME, DISPLAY_CHAR, HITPOINTS);
        this.addCapability(Status.NOT_FOLLOWING);
        this.behaviours.put(8, new AttackBehaviour());
        this.registerInstance();
    }

    /**
     * Override getIntrinsicWeapon with its own damage and verb
     *
     * @return Intrinsic Weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(INTRINSIC_DAMAGE, DAMAGE_VERB);
    }

    /**
     * Return actions taken by Bowser
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
            //check if Piranha Plant is conscious
            if (this.isConscious()) {
                actions.add(new AttackAction(this, direction));
            }
        }
        return actions;
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
        super.playTurn(actions, lastAction, map, display);

        //loop through behaviours and get actions
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        //return action that do nothing
        return new DoNothingAction();
    }

    /**
     * Reset Piranha Plant hp
     */
    @Override
    public void resetInstance() {
        this.increaseMaxHp(50);
        this.heal(getMaxHp());
    }
}
