package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.enums.WeaponType;

/**
 * Scimitar is a class that represents a weapon which can perform spinning attack action
 * It deals 118 damage and 88 hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Scimitar extends WeaponItem implements Sellable, Purchasable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;
    /**
     * the price when player purchase this weapon from trader
     */
    private int purchasePrice;
    /**
     * A weapon type which define Scimitar
     */
    private WeaponType weaponType = WeaponType.CURVED_SWORD;

    public Scimitar() {
        super("Scimitar", 's', 118, "Knife", 88);
        this.sellingPrice = 100;
        this.purchasePrice = 600;
    }

    /**
     * getter to get the purchase price
     * @return purchase price
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }


    /**
     * getter to get the selling price
     * @return selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Scimitar can make area attack action
     * @param actor target actor
     * @param direction
     * @return AreaAttackAction
     */
    @Override
    public Action getSkill(Actor actor, String direction) {
        return new AreaAttackAction(direction, this);
    }

    /**
     * Scimitar is sellable
     * @return true
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }

    /**
     * Scimitar is purchasable
     * @return true
     */
    @Override
    public boolean isPurchasable()
    {
        return true;
    }
}
