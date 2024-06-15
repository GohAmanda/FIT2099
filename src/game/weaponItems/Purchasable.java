package game.weaponItems;

import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Sellable is an interface that define the weapon is sellable or not
 */
public interface Purchasable extends Weapon {
    /**
     * If the weapon is purchasable, return true, otherwise return false
     * @return false as default
     */
    default boolean isPurchasable()
    {
        return false;
    }
}
