package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.DogMouth;

/**
 * A Dog class that extends from the Enemy class
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Dog extends Enemy {

    /**
     * Constructor
     *
     * A new Dog object with predefined attributes.
     * The dog is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the dog, and the weapon that the dog uses is added into the weapon inventory
     */
    public Dog() {
        super("Dog", 'a', 104, 52, 1390);
        this.addCapability(EnemyType.BROTHERS);
        this.addCapability(Status.ENEMY);
        this.addWeaponToInventory(new DogMouth());
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
        return new IntrinsicWeapon(weapon.damage(), "bites", weapon.chanceToHit());
    }

}
