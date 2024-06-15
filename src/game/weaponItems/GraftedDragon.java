package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.enums.WeaponType;

/**
 *
 * A simple weapon that can be used to attack the enemy.
 * It deals 89 damage with 90% hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GraftedDragon extends WeaponItem implements Sellable {
    /**
     * the price when player sell to trader
     */
    private int sellingPrice;

    /**
     * Constructor
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "??????", 90);
        this.sellingPrice = 200;
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

