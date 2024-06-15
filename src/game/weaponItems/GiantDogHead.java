package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;
import game.actions.QuickStepAction;

/**
 * GiantDogHead is a class that represents a Giant Dog's weapon
 * It deals 527 damage, 90% hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GiantDogHead extends WeaponItem implements Sellable, Purchasable {

    /**
     * Constructor.
     *
     */
    public GiantDogHead() {
        super("Giant Dog Head", 'H', 527, "slams", 90);
    }

    /**
     * GiantDogHead can make area attack
     * @param actor target actor
     * @param direction
     * @return AreaAttackAction
     */
    @Override
    public Action getSkill(Actor actor, String direction) {
        return new AreaAttackAction(direction, this);
    }
}
