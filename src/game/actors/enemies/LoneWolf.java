package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enums.EnemyType;
import game.enums.Status;


/**
 * BEHOLD, DOG!
 * LoneWolf is a class that represents an enemy
 * it has 102 hitting point, same type with Giant Dog
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class LoneWolf extends Enemy {

    /**
     * Constructor
     *
     * A new LoneWolf object with predefined attributes.
     * The LoneWolf  is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     * Adds the capability of the LoneWolf, and the weapon that the LoneWolf
     * uses is added into the weapon inventory
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102, 55, 1470);
        this.addCapability(EnemyType.DOGANDWOLF);
        this.addCapability(Status.ENEMY);
    }

    /**
     * During the attackAction, if the actor didn't have weapon in their weaponInventory, will use this intrinsicWeapon
     *
     * @return intrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(97, "bites", 95);
    }
}
