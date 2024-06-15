package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.GiantCrabPincer;

/**
 * BEHOLD, DOG!
 * GiantCrayfish is a class that represents an enemy
 * it has 407 hitting point, same type with Giant Crab
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class GiantCrab extends Enemy {

    /**
     * Constructor
     * A new GiantCrab object with predefined attributes.
     * The GiantCrab is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the GiantCrab, and the weapon that the GiantCrab uses is added into the weapon inventory
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407, 318, 4961);
        this.addCapability(EnemyType.CRABANDFISH);
        this.addCapability(Status.ENEMY);
        this.addWeaponToInventory(new GiantCrabPincer());

    }

    /**
     * getter to get weaponItem
     * @return weaponItem
     */
    public WeaponItem getWeaponItem() {
        return this.getWeaponInventory().get(0);
    }


    /**
     * During the attackAction, if the actor didn't have weapon in their weaponInventory, will use this intrinsicWeapon
     * @return intrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        WeaponItem weapon = this.getWeaponItem();
        return new IntrinsicWeapon(weapon.damage(), "slams", weapon.chanceToHit());
    }



}
