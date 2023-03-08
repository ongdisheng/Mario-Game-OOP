package game.managers;

import edu.monash.fit2099.engine.items.Item;
import game.interfaces.Consumable;

import java.util.ArrayList;

/**
 * ConsumableItemManager to store the consumable items in consumableList.
 */
public class ConsumableItemManager {
    /**
     * ArrayList consumableList of type Consumable interface
     */
    private ArrayList<Consumable> consumableList;
    /**
     * Constant instance of type ConsumableItemManager
     */
    private static ConsumableItemManager instance;

    /**
     * Constructor.
     */
    private ConsumableItemManager() { // 1-  private to prevent anyone else from instantiating
        consumableList = new ArrayList<>();
    }

    /**
     * Get consumable items' instance
     *
     * @return instance
     */
    public static ConsumableItemManager getInstance() {
        if (instance == null) {
            instance = new ConsumableItemManager();
        }
        return instance;
    }

    /**
     * Add Consumable Item into consumableList once picked up by Player
     *
     * @param item consumable items picked up by Player
     */
    public void appendConsumableItem(Consumable item) {
        this.consumableList.add(item);
    }

/*    public ArrayList<Consumable> getConsumableItem() {
        return new ArrayList<Consumable>(this.consumableList);
    }*/

    /**
     * Get instance of the item that wanted to be consumed by Player
     *
     * @param newItem item that wanted to be consumed by Player
     * @return item
     */
    public Consumable getConsumableItem(Item newItem){
        Consumable retItem = null;

        //loop through consumableList to get the instance of item
        for (Consumable consumable : consumableList) {
            if (consumable == newItem) {
                retItem = consumable;
            }
        }
        return retItem;
    }

    /**
     * Remove item that are consumed by Player
     *
     * @param item magical items that consumed by Player
     */
    public void removeConsumableItem(Item item){
        consumableList.remove(item);
    }
}
