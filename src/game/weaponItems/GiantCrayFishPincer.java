package game.weaponItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AreaAttackAction;

/**
 * GiantCrayFishPincer is a class that represents a Giant Cray Fish's weapon
 * It deals 527 damage, 100% hit rate
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GiantCrayFishPincer extends WeaponItem {
    /**
     * Constructor.
     */
    public GiantCrayFishPincer() {
        super("Giant Cray Fish Pincer", 'P', 527, "slams", 100);
    }

    /**
     * GiantCrayFishPincer can make area attack
     * @param actor target actor
     * @param direction
     * @return AreaAttackAction
     */
    @Override
    public Action getSkill(Actor actor, String direction) {
        return new AreaAttackAction(direction, this);
    }

}
