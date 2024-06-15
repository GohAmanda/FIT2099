package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Despawn is an action that can despawn enemy on map
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Despawn extends Action {
    /**
     * When the enemy is killed, the mao will remove the actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ActionList dropActions = new ActionList();

        if (map.locationOf(actor) != null){
            for (WeaponItem weapon : actor.getWeaponInventory())
                dropActions.add(weapon.getDropAction(actor));

        }
        for (Action drop : dropActions) {
            drop.execute(actor, map);
        }

        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * Describes which actor is despawned
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been despawned.";
    }
}