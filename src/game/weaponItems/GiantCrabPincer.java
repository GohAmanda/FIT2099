package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickStepAction;
import game.enums.EnemyType;

/**
 * GiantCrabPincer is a class that represents a Giant Crab's weapon
 * It deals 208 damage, 90% hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GiantCrabPincer extends WeaponItem implements Sellable, Purchasable {

    /**
     * Constructor.
     *
     */
    public GiantCrabPincer() {
        super("Giant Crab Pincer", 'c', 208, "slams", 90);

    }

}
