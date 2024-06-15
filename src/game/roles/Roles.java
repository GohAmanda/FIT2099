package game.roles;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Roles is an abstract class that represent player's role
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public abstract class Roles {
    /**
     * hitting point for player
     */
    private int hittingPoint;
    /**
     * the weapon item the player will start the game with
     */
    private WeaponItem weaponItem;

    /**
     * Constructor
     */
    public Roles(int hittingPoint, WeaponItem weaponItem) {
        this.hittingPoint = hittingPoint;
        this.weaponItem = weaponItem;
    }

    /**
     * Getter to get weapon
     * @return weaponItem
     */
    public WeaponItem getWeaponItem(){
        return this.weaponItem;
    }

    /**
     * Getter to get hittingPoint
     * @return hittingPoint
     */
    public int getHittingPoint(){
        return this.hittingPoint;
    }
}
