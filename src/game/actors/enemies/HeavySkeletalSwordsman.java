package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.Grossmesser;

/**
 * BEHOLD, DOG!
 * HeavySkeletalSwordsman is a class that represents enemy
 * it has 153 hitting point, same type with Skeletal Bandit
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class HeavySkeletalSwordsman extends Enemy {

    /**
     * Constructor
     *
     * A new HeavySkeletalSwordsman object with predefined attributes.
     * The HeavySkeletalSwordsman is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the HeavySkeletalSwordsman, and the weapon that the HeavySkeletalSwordsman
     *  uses is added into the weapon inventory
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153, 35, 392);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(EnemyType.SKELETAL);
        this.addCapability(Status.ENEMY);
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
        return new IntrinsicWeapon(weapon.damage(), "hit", weapon.chanceToHit());
    }


}

