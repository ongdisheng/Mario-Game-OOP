package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Status;

public class Fire extends Item {

    /**
     * The name is set as a constant "Fire" / whatever you think is appropriate
     */
    private static final String NAME = "Fire";

    /**
     * The display character of fire is set as a constant 'v' / whatever you think is appropriate
     */
    private static final char FIRE_CHAR = 'v';
    /**
     * The number of turns for fire to fade away
     */
    private int burningTurn = 4;
    /**
     * Create display object to print message to console
     */
    Display display = new Display();
    /**
     * Number of turns to start to let the player on fire
     */
    private int startBurningTurn = 4;

    /***
     * Constructor.
     */
    public Fire() {
        super(NAME, FIRE_CHAR, false);
    }

    /**
     * Remove fire from the current location within 10 turns
     *
     * @param currentLocation The location of the ground on which fire lie.
     */
    @Override
    public void tick(Location currentLocation){
        Actor target = currentLocation.getActor();
        GameMap map = currentLocation.map();
        //check if the fire location contains actor
        if (currentLocation.containsAnActor() && target.isConscious() && startBurningTurn <= 3) {
            if (target.hasCapability(Status.CAN_ENTER_SHELL)){
                target.addCapability(Status.ON_FIRE);
            }
            //fire will hurt actor for 20 damage
            currentLocation.getActor().hurt(20);
            //display message to console
            display.println(currentLocation.getActor() + " is on fire, receive 20 damage");
            //check if target is conscious
            if (!target.isConscious()) {
                //check if target is Koopa
                if (target.hasCapability(Status.CAN_ENTER_SHELL)){
                    display.println(System.lineSeparator() + target + " retreated into its shell!");
                }
                else {
                    //normal output String for enemies other than Koopa
                    ActionList dropActions = new ActionList();
                    //drop all items
                    for (Item item : target.getInventory())
                        dropActions.add(item.getDropAction(target));
                    for (Action drop : dropActions)
                        drop.execute(target, map);
                    if (target.hasCapability(Status.CAN_DROP_KEY)){
                        map.locationOf(target).addItem(new Key());
                    }
                    //remove actor from map
                    map.removeActor(target);
                    //check if target is in dormant state
                    display.println(System.lineSeparator() + target + " was burnt to death!");
                }
            }
        }

        startBurningTurn -= 1;
        burningTurn -= 1;

        //if use up 3 turns
        if (burningTurn == 0){
            //remove fire current location
            currentLocation.removeItem(this);
        }
    }

}