package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weaponItems.Uchigatana;

/**
 * A unique skill that can be performed using Uchigatana
 * it will double the damage and change the hit rate to 60
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class UnsheatheAction extends Action {

    /**
     * return a new Uchigatana
     *
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    public Uchigatana newUchigatana() {
        return new Uchigatana("Uchigatana", ')',115*2,"",60);
    }

    /**
     * return null
     *
     * @param actor The actor performing the attack action.
     * @param map   The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Describes this action is executed
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Use Uchigatana to perform unsheathe action - double your damage and 60% of attack accuracy";
    }
}
