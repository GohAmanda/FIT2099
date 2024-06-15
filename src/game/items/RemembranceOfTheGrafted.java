package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.weaponItems.Sellable;

/**
 * The item that can be sold
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */

public class RemembranceOfTheGrafted extends Item implements Sellable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;

    public RemembranceOfTheGrafted(){
        super("Remembrance of the Grafted", 'O', false);
        this.sellingPrice = 20000;
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
     * RemembranceOfTheGrafted is sellable
     * @return true
     */
    @Override
    public boolean isSellable()
    {
        return true;
    }


    /**
     * The damage of this item
     * @return 0
     */
    @Override
    public int damage() {
        return 0;
    }

    /**
     * The verb of this item
     * @return null
     */
    @Override
    public String verb() {
        return null;
    }

    /**
     * The chance to hit of this item
     * @return 0
     */
    @Override
    public int chanceToHit() {
        return 0;
    }
}
