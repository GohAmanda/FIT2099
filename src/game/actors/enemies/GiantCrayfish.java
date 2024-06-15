package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.GiantCrayFishPincer;

/**
 * GiantCrayfish is a class that represents an enemy
 * it has 4803 hitting point, same type with Giant Crab
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GiantCrayfish extends Enemy {

    /**
     * Constructor
     *
     * A new GiantCrayfish object with predefined attributes.
     * The GiantCrayfish is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the GiantCrayfish, and the weapon that the GiantCrayfish uses is added
     *  into the weapon inventory
     */
    public GiantCrayfish() {
        super("Giant Crab Fish", 'R', 4803, 500, 2374);
        this.addCapability(EnemyType.CRABANDFISH);
        this.addCapability(Status.ENEMY);
        this.addWeaponToInventory(new GiantCrayFishPincer());
    }

    /**
     * getter to get weaponItem
     *
     * @return weaponItem
     */
    public WeaponItem getWeaponItem() {
        return this.getWeaponInventory().get(0);
    }


    /**
     * During the attackAction, if the actor didn't have weapon in their weaponInventory, will use this intrinsicWeapon
     *
     * @return intrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        WeaponItem weapon = this.getWeaponItem();
        return new IntrinsicWeapon(weapon.damage(), "slams", weapon.chanceToHit());
    }

}
