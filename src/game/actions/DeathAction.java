package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.tools.FancyMessage;
import game.actors.Player;
import game.enums.Status;
import game.items.Runes;

/**
 * an action when enemies or player died
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class DeathAction extends Action {
    /**
     * the runes that drop after actor died
     */
    private Runes runes = null;

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map    The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        ActionList dropActions = new ActionList();

        // if player died, reset whole game (player won't die)
        if (target.getClass() == Player.class) {
            target.addCapability(Status.DEAD);
            ResetAction action = new ResetAction();
            result += action.execute(target, map) + "\n";

        }else {
            // if enemy die, drop all items
            for (Item item : target.getItemInventory()) {
                dropActions.add(item.getDropAction(target));
                if (item.getClass() == Runes.class && item != null) {
                    this.runes = (Runes) item;
                }
            }

            for (Action drop : dropActions) {
                drop.execute(target, map);
            }

            if(this.runes != null)
            {
                result += new DropItemAction(this.runes).execute(target, map);
            }

            map.removeActor(target);
        }


        result += System.lineSeparator() + menuDescription(target);
        return result;
    }

    /**
     * Describes which actor is killed
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription (Actor actor)
    {
        if (actor.getClass() == Player.class)
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        else {
            if(this.runes != null){
              return actor + " is killed.\nDropped Runes: " + this.runes.getRunes();
            }
            else {
                return actor + " is killed";
            }
        }
        return "OhNo so sad";
    }


}
