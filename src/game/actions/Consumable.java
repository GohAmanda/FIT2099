package game.actions;

/**
 * Interface for Consumable items.
 *
 * As well as providing methods needed by items, this interface is used in Item to
 * determine whether an item can be consumable.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public interface Consumable {
    /**
     * Get a boolean from the items. Use this method to check whether the item is consumable.
     * @return false if the item is not consumable, otherwise, return true.
     */
    default boolean consumable(){
        return false;
    }
}
