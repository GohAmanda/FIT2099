package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.Scimitar;

/**
 * SkeletalBandit is a class that represents enemy
 * it has 184 hitting point, same type with Heavy Skeletal Swordsman
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class SkeletalBandit extends Enemy {
    /**
     * Constructor
     *
     * A new SkeletalBandit object with predefined attributes.
     * The SkeletalBandit is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the SkeletalBandit, and the weapon that the SkeletalBandit uses is added
     *  into the weapon inventory
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, 35, 892);
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(EnemyType.SKELETAL);
        this.addCapability(Status.ENEMY);
        this.getIntrinsicWeapon();
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
