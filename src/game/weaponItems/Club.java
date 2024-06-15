package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.enums.WeaponType;
import game.items.Runes;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Club extends WeaponItem implements Sellable, Purchasable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;
    /**
     * the price when player purchase this weapon from trader
     */
    private int purchasePrice;
    /**
     * A weapon type which define Club
     */
    private WeaponType weaponType = WeaponType.HAMMER;

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.sellingPrice = 100;
        this.purchasePrice = 600;
    }

    /**
     * to set the purchase price (the player want to purchase weapon from trader)
     * @param purchasePrice
     */
    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * getter to get the purchase price
     * @return purchase price
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * to set the selling price (the player want to sell weapon to the trader)
     * @param sellingPrice
     */
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * getter to get the selling price
     * @return selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }


    /**
     * Club has not any special skill, hence we just return normal attack action
     * @param actor target actor
     * @param direction
     * @return AttackAction
     */
    @Override
    public Action getSkill(Actor actor, String direction)
    {
        return new AttackAction(actor, direction, this);
    }

    /**
     * Club is sellable
     * @return true
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }

    /**
     * Club is purchasable
     * @return true
     */
    @Override
    public boolean isPurchasable()
    {
        return true;
    }
}
