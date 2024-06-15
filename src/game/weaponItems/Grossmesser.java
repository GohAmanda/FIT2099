package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.enums.WeaponType;

/**
 * Grossmesser is a class that represents a weapon that can perform spinning attack action
 * It deals with 115 damage and 85 hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Grossmesser extends WeaponItem implements Sellable, Purchasable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;

    /**
     * A weapon type which define Grossmesser
     */
    private WeaponType weaponType = WeaponType.CURVED_SWORD;

    /**
     * Constructor
     */
    public Grossmesser(){
        super("Grossmesser", '?', 115, "hits", 85);
        this.sellingPrice = 100;
    }

    /**
     * getter to get the selling price
     * @return selling price
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Grossmesser can make area attack action
     * @param actor target actor
     * @param direction
     * @return AreaAttackAction
     */
    @Override
    public Action getSkill(Actor actor, String direction) {
        return new AreaAttackAction(direction, this);
    }

    /**
     * Grossmesser is sellable
     * @return true
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }
}
