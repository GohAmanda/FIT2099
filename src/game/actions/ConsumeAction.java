package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * an action to consume item if item is consumable
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class ConsumeAction extends Action {
    /**
     * item to consume
     */
    private Item item;
    /**
     * boolean to make sure if the item is consumable
     */
    private boolean consumable;

    /**
     * Constructor.
     *
     * @param item          the item to consume
     * @param consumable    return whether the item can consumable
     */
    public ConsumeAction(Item item, boolean consumable){
        this.item = item;
        this.consumable = consumable;
    }

    /**
     * When executed, check whether the item can consumable, if true, remove the item from the inventory
     *
     * @param actor The actor performing the attack action.
     * @param map   The map the actor is on.
     * @return the result of consume, e.g. whether the item is consumed, etc.
     * @see DeathAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(this.consumable)
        {
            actor.removeItemFromInventory(this.item);
        }
        return menuDescription(actor);
    }

    /**
     * Describes which actor consume which item
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + this.item;
    }
}
