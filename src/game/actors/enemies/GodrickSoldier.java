package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enums.EnemyType;
import game.enums.Status;
import game.weaponItems.Grossmesser;

public class GodrickSoldier extends Enemy{
    /**
     * Constructor
     *
     * A new GodrickSoldier object with predefined attributes.
     * The GodrickSoldier is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the GodrickSoldier, and the weapon that the GodrickSoldier uses is added
     *  into the weapon inventory
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198, 38, 70);
        this.addCapability(EnemyType.BROTHERS);
        this.addCapability(Status.ENEMY);
        this.addWeaponToInventory(new Grossmesser());
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
