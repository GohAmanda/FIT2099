package game.weaponItems;

import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import javax.swing.*;

/**
 * Sellable is an interface that define the weapon is sellable or not
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public interface Sellable extends Weapon {
    /**
     * If the weapon is sellable, return true, otherwise return false
     * @return false as default
     */
    default boolean isSellable()
    {
        return false;
    }
}
