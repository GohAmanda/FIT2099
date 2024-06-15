package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.GiantDogHead;

/**
 * GiantDog is a class that represents an enemy
 * it has 693 hitting point, same type with Lone Wolf
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GiantDog extends Enemy {
    /**
     * Constructor
     *
     * A new GiantDog object with predefined attributes.
     * The GiantDog is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     *Adds the capability of the GiantDog, and the weapon that the GiantDog uses is added into the weapon inventory
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693, 313, 1808);
        this.addCapability(EnemyType.DOGANDWOLF);
        this.addCapability(Status.ENEMY);
        this.addWeaponToInventory(new GiantDogHead());
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
