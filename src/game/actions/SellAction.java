package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Player;
import game.items.RemembranceOfTheGrafted;
import game.items.RuneManager;
import game.weaponItems.*;

import java.util.Scanner;

/**
 * SellAction is an action when player sells weapon to trader
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class SellAction extends Action{

    /**
     * The player that will do the sell action
     */
    private Player player;

    /**
     * Used to display the message
     */
    private Display display = new Display();

    /**
     * Constructor
     *
     * @param player that will do the sell action
     */
    public SellAction(Actor player)
    {
        this.player = (Player) player;
    }

    /**
     * When the player meets the trader, player can choose to sell weapon(s) to the trader
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Scanner input = new Scanner(System.in);
        int selection;

        RuneManager runeManager = new RuneManager();
        int getSellPrice = 0;
        boolean isSellable = false;
        boolean isSellWeapon = true;
        WeaponItem weapon = null;
        RemembranceOfTheGrafted remembrance = null;

        int i = 0;
        while (i < this.player.getWeaponInventory().size()){
            display.println(i + " - " + this.player.getWeaponInventory().get(i));
            i ++;
        }

        for (Item item: this.player.getItemInventory()){
            if (item.getClass() == RemembranceOfTheGrafted.class){
                remembrance = (RemembranceOfTheGrafted) item;
                display.println(i + " - " + item);
            }
        }

        selection = input.nextInt();
        if (selection < i){
            weapon = this.player.getWeaponInventory().get(selection);

            if(weapon.getClass() == new GreatKnife().getClass()) {
                getSellPrice = new GreatKnife().getSellingPrice();
                isSellable = new GreatKnife().isSellable();
            }

            else if(weapon.getClass() == new Club().getClass()) {
                getSellPrice = new Club().getSellingPrice();
                isSellable = new Club().isSellable();
            }

            else if(weapon.getClass() == new Grossmesser().getClass()) {
                getSellPrice = new Grossmesser().getSellingPrice();
                isSellable = new Grossmesser().isSellable();
            }

            else if(weapon.getClass() == new Uchigatana().getClass()) {
                getSellPrice = new Uchigatana().getSellingPrice();
                isSellable = new Uchigatana().isSellable();
            }

            else if(weapon.getClass() == new Scimitar().getClass()) {
                getSellPrice = new Scimitar().getSellingPrice();
                isSellable = new Scimitar().isSellable();
            }
        }
        else{
            isSellWeapon = false;
            getSellPrice = new RemembranceOfTheGrafted().getSellingPrice();
            isSellable = new RemembranceOfTheGrafted().isSellable();
        }



        boolean decision = runeManager.makeSell(this.player, getSellPrice, isSellable);

        if(decision)
        {
            if (isSellWeapon)
                this.player.removeWeaponFromInventory(weapon);
            else
                this.player.removeItemFromInventory(remembrance);
            display.print("runes:" + this.player.getRunesInt().getRunes());
        }
        else
        {
            return "Failed to sell item/weapon";
        }

        return menuDescription(actor);
    }


    /**
     * Describes the player purchased which weapon(s
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription (Actor actor){
        return this.player + " sell item/weapon";
    }
}