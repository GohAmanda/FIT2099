package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Runes;

import java.awt.image.renderable.RenderableImage;

/**
 * An action that recovers the runes
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class RecoverRunesAction extends Action {

    /**
     * The runes that will be recovered
     */
    private Item runes;

    /**
     *  Constructor
     *
     * @param runes the runes that will be recovered
     */
    public RecoverRunesAction(Item runes)
    {
        this.runes = runes;
    }

    /**
     * This will be executed when the actor has done the RecoverRunesAction. The actor's current amount
     * of runes will add with the runes that will be recovered. Once the runes are being recovered the
     * map will not show this item anymore
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of how many runes the actor has gotten
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        Runes runesToGet = (Runes) runes;

        player.getRunesInt().addRunes(runesToGet.getRunes());
        map.locationOf(actor).removeItem(this.runes);

        return actor + " gets " + runesToGet.getRunes();
    }

    /**
     * Describes the actor has chose to recover runes
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " choose to recover the runes";
    }
}
