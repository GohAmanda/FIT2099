package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action when player choose to rest
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class RestAction extends Action {
    /**
     * When executed, doing resetAction
     *
     * @param actor The actor performing the attack action.
     * @param map   The map the actor is on.
     * @return the result of the attack, e.g. whether the target is killed, etc.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetAction resetAction = new ResetAction();
        return resetAction.execute(actor, map);
    }

    /**
     * Describes the actor execute restAction
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " chooses to rest";
    }
}
