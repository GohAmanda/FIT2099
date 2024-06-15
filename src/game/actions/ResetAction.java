package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.enums.Status;
import game.items.Runes;

/**
 * An action when reset whole game
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class ResetAction extends Action {

    /**
     * Constructor.
     */
    public ResetAction(){
    }

    /**
     * When executed, reset the game
     * If the player died again before grabbing the runes, the runes will disappear
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description used for the menu UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return menuDescription(actor);
    }

    /**
     * Describes the reset is done
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset whole game";
    }
}
