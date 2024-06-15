package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.items.RuneManager;
import game.weaponItems.*;

import java.util.Scanner;

/**
 * Exchange Action is an action that can allow the player to exchange with the trader
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class ExchangeAction extends Action {
    /**
     * the player that do this action
     */
    private Player player;

    /**
     * The item that will be exchanged in this action
     */
    private Item item ;

    /**
     * Used to display the message
     */
    private Display display = new Display();

    /**
     * Constructor
     * @param actor the actor that exchange weapon from trader
     */
    public ExchangeAction(Actor actor, Item item)
    {
        this.player = (Player) actor;
        this.item = item;
    }

    /**
     * When the player meets the trader, player can choose to exchange weapon(s) from the trader
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int selection;
        Scanner input = new Scanner(System.in);
        display.println("Choose The Weapon That You Want To Exchange From Trader:");
        display.println("1 - " + new AxeOfGodrick());
        display.println("2 - " + new GraftedDragon());
        selection = input.nextInt();
        WeaponItem weaponItem;

        switch (selection){
            case 1:
                weaponItem = new AxeOfGodrick();
                break;
            case 2:
                weaponItem = new GraftedDragon();
                break;
            default:
                return "You have input wrong number!";
        }

        this.player.addWeaponToInventory(weaponItem);
        this.player.removeItemFromInventory(this.item);
        return menuDescription(actor);

    }

    /**
     * Describes the player purchased which weapon(s)
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " choose to exchanges weapon using Remembrance of the Grafted";
    }
}

