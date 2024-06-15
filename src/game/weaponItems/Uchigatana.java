package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.UnsheatheAction;
import game.enums.WeaponType;

/**
 * Uchigatana is a class that represents a weapon
 * It deals 115 damage and 80 hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Uchigatana extends WeaponItem implements Sellable, Purchasable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;
    /**
     * the price when player purchase this weapon from trader
     */
    private int purchasePrice;
    /**
     * A weapon type which define Uchigatana
     */
    private WeaponType weaponType = WeaponType.KATANA;


    /**
     * A default constructor for Uchigatana
     */
    public Uchigatana(){
        super("Uchigatana", ')',115,"",80);
        this.sellingPrice = 500;
        this.purchasePrice = 5000;
    }

    /**
     * A non-default constructor for Uchigatana
     */
    public Uchigatana(String name, char displayChar, int damage, String verb, int hitRate)
    {
        super(name, displayChar,damage,verb,hitRate);
        this.sellingPrice = 500;
        this.purchasePrice = 5000;
    }

    /**
     * getter to get the selling price
     * @return selling price
     */
    public int getSellingPrice(){
        return this.sellingPrice;
    }

    /**
     * getter to get the purchase price
     * @return purchase price
     */
    public int getPurchasePrice(){
        return this.purchasePrice;
    }

    /**
     * to set the selling price (the player want to sell weapon to the trader)
     * @param sellingPrice
     */
    public void setSellingPrice(int sellingPrice){
        this.sellingPrice = sellingPrice;
    }

    /**
     * to set the purchase price (the player want to purchase weapon from the trader)
     * @param purchasePrice
     */
    public void setPurchasePrice(int purchasePrice){
        this.purchasePrice = purchasePrice;
    }

    /**
     * A getter for weapon type
     * @return
     */
    public WeaponType getWeaponType(){
        return this.weaponType;
    }

    /**
     * A function to return each weapon's special skill
     * @param otherActor target to attack
     * @param direction the direction
     * @return an attack action
     */
    @Override
    public Action getSkill(Actor otherActor, String direction) {
        WeaponItem weaponItem = new UnsheatheAction().newUchigatana();
        return new AttackAction(otherActor, direction, weaponItem);
    }

    /**
     * mark as sellable
     * @return boolean
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }

    /**
     * mark as purchasable
     * @return boolean
     */
    @Override
    public boolean isPurchasable()
    {
        return true;
    }
}
