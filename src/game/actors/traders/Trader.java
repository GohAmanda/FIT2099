package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ExchangeAction;
import game.items.RemembranceOfTheGrafted;
import game.tools.Surroundings;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.actors.Player;

import java.util.List;


/**
 * Trader is an actor that player can purchase or sell with
 * Trader cannot be attacked
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Trader extends Actor {

    /**
     * Constructor
     */
    public Trader()
    {
        super("Merchant Kale", 'K', 0);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * The trader cannot be attacked
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that the other actors can do to trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList meetTrader = new ActionList();

        if (otherActor.getClass() == Player.class)
        {
            meetTrader.add(new SellAction(otherActor));
            meetTrader.add(new PurchaseAction(otherActor));
        }
        return meetTrader;
    }
}
