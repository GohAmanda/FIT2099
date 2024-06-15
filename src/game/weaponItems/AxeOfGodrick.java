package game.weaponItems;


import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * The weapon that extends the WeaponItem
 * It deals 142 damage with 84% hit rate
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */

public class AxeOfGodrick extends WeaponItem implements Sellable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;

    /**
     * Constructor
     */
    public AxeOfGodrick() {
        super("AxeOfGodrick", 'T', 142, "bonks", 84);
        this.sellingPrice = 100;
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
}

