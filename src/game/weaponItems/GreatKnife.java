package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.enums.WeaponType;

/**
 * GreatKnife is a class that represents a weapon that can perform Quick Step action
 * It deals with 75 damage and 70 hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GreatKnife extends WeaponItem implements Sellable, Purchasable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;
    /**
     * the price when player purchase this weapon from trader
     */
    private int purchasePrice;
    /**
     * A weapon type which define GreatKnife
     */
    private WeaponType weaponType = WeaponType.DAGGER;

    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "Knife", 70);
        this.sellingPrice = 350;
        this.purchasePrice = 3500;
    }


    /**
     * getter to get the purchase price
     *
     * @return purchase price
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }


    /**
     * getter to get the selling price
     *
     * @return selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * GreatKnife can make quick step action
     * @param otherActor target actor
     * @param direction
     * @return QuickStepAction
     */
    @Override
    public Action getSkill(Actor otherActor, String direction) {
        return new QuickStepAction(otherActor, direction);
    }

    /**
     * GreatKnife is sellable
     * @return true
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }

    /**
     * GreatKnife is purchasable
     * @return true
     */
    @Override
    public boolean isPurchasable()
    {
        return true;
    }

}