package game.weaponItems;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A simple weapon that can be used by the enemy
 *  It deals 101 damage with 93% hit rate
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */
public class DogMouth extends WeaponItem {

    /**
     * Constructor.
     */
    public DogMouth() {
        super("Dog Mouth", 'M', 101, "bits", 93);
    }
}
