package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.enums.Status;
import game.items.Coin;

/**
 * The High Ground class represents a type of ground that can be jumped to
 * @author Mark Manlangit
 * @version 1.0
 * @since 30-04-2022
 */
public abstract class HighGround extends Ground {
    //private/protected attributes
    /**
     * The jump chance of this high ground
     */
    private double jumpChance;
    /**
     * The fall damage from this high ground
     */
    private int fallDamage;
    /**
     * The maximum jump chance of a high ground
     */
    private static final double MAX_JUMP_CHANCE = 1;

    /**
     * A constructor for the HighGround class
     * @param displayChar The character that represents the High Ground when displayed
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Returns true if an Actor can enter this location.
     * Actors can enter this location if they have the Invincible buff.
     * @param actor the Actor to check
     * @return true if the Actor can enter this location
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE) || actor.hasCapability(Status.CAN_ENTER_HIGH_GROUND);
    }

    /**
     * Returns true since High Grounds do block thrown objects
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * A method that performs a jump to this High Ground based on the odds of a successful jump
     * @param actor The actor performing the jump
     * @param to The location of the High Ground the actor wants to jump to
     * @param gameMap The current game map the jump is being performed in
     * @return A jump message indicating whether the actor performed a successful jump
     */
    public String jumped(Actor actor, Location to, GameMap gameMap) {
        String jumpMessage;
        double currentJumpChance = jumpChance;

        // if actor has super mushroom buff, use max jump chance to perform the jump
        if (actor.hasCapability(Status.TALL)) {
            currentJumpChance = MAX_JUMP_CHANCE;
        }
        // perform jump based on jump chance
        if (Math.random() < currentJumpChance) {
            // if successful, move actor to high ground and set output message to success
            gameMap.moveActor(actor, to);
            jumpMessage = " successfully jumped onto the ";
        } else {
            // if not successful, reduce the actor's hp by fall damage amount and set output message to failure
            actor.hurt(fallDamage);
            jumpMessage = " failed the jump! Received " + fallDamage + " damage falling from the ";
        }
        return jumpMessage;
    }

    /**
     * A method that returns a list of actions that can be performed on this High Ground
     * @param actor the Actor performing an action
     * @param location the current Location of the High Ground
     * @param direction the direction of the High Ground from the Actor
     * @return list of actions that can be performed on this High Ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        // if high ground does not contain an existing actor and actor is not Invincible
        if (!location.containsAnActor() && !actor.hasCapability(Status.INVINCIBLE)) {
            // add a new jump action to this high ground
            actions.add(new JumpAction(this, direction, location));
        }
        return actions;
    }

    /**
     * Setter for this High Ground's jump chance
     * @param jumpChance the chance of a successful jump
     */
    protected void setJumpChance(double jumpChance) {
        this.jumpChance = jumpChance;
    }

    /**
     * Setter for this High Ground's fall damage
     * @param fallDamage the damage from failing to jump onto this high ground
     */
    protected void setFallDamage(int fallDamage) {
        this.fallDamage = fallDamage;
    }

    /**
     * A method that is called so that this high ground can experience the passage of time
     * In this case to check if an actor destroys the high ground on this turn
     * @param location The location of the High Ground
     */
    @Override
    public void tick(Location location) {
        // if this high ground contains an actor and that actor has the Invincible buff
        if (!location.getGround().hasCapability(Status.INDESTRUCTIBLE) && location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)) {
            // set the ground at this location to dirt (destroy) and spawn a $5 coin
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }
    }
}
