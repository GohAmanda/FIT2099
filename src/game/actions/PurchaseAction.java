package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.items.RuneManager;
import game.weaponItems.Club;
import game.weaponItems.GreatKnife;
import game.weaponItems.Scimitar;
import game.weaponItems.Uchigatana;

import java.util.Scanner;

/**
 * PurchaseAction is an action when player purchase weapon from trader
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class PurchaseAction extends Action {

    /**
     * the player that do this action
     */
    private Player player;
    private Display display = new Display();


    /**
     * Constructor
     *
     * @param actor the actor that purchase weapon from trader
     */
    public PurchaseAction(Actor actor)
    {
        this.player = (Player)actor;
    }

    /**
     * When the player meets the trader, player can choose to purchase weapon(s) from the trader
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        System.out.println(this.player.getRunesInt().getRunes());
        int selection;
        Scanner input = new Scanner(System.in);
        display.println("Choose The Weapon That You Want To Buy From Trader:");
        display.println("1 - " + new Uchigatana());
        display.println("2 - " + new GreatKnife());
        display.println("3 - " + new Club());
        display.println("4 - " + new Scimitar());
        selection = input.nextInt();

        RuneManager runeManager = new RuneManager();
        int getPurchasePrice = 0;
        boolean isPurchasable = false;
        boolean decision = false;
        WeaponItem weaponItem = null;

        try{
            switch (selection){
                case 1:
                    weaponItem = new Uchigatana();
                    getPurchasePrice = new Uchigatana().getPurchasePrice();
                    isPurchasable = new Uchigatana().isPurchasable();

                    break;
                case 2:
                    weaponItem = new GreatKnife();
                    getPurchasePrice = new GreatKnife().getPurchasePrice();
                    isPurchasable = new GreatKnife().isPurchasable();
                    break;
                case 3:
                    weaponItem = new Club();
                    getPurchasePrice = new Club().getPurchasePrice();
                    isPurchasable = new Club().isPurchasable();
                    break;
                case 4:
                    weaponItem = new Scimitar();
                    getPurchasePrice = new Scimitar().getPurchasePrice();
                    isPurchasable = new Scimitar().isPurchasable();
                    break;
            }
        }
        catch(IllegalArgumentException e)
        {
            display.println("You have input wrong number!");
        }

        decision = runeManager.makePurchase(this.player, getPurchasePrice, isPurchasable);

        if(decision)
        {
            this.player.addWeaponToInventory(weaponItem);
        }
        else
        {
            return "You don't have enough money to buy the weapon!";
        }

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
        return actor + " purchase weapon " + this.player.getWeaponInventory();
    }
}
