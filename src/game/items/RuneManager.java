package game.items;

import game.actors.Player;

/**
 * RuneManager is a class that helps to manage runes
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class RuneManager {
    /**
     * an instance of the purchase manager
     */
    private static RuneManager instance;

    /**
     *
     * @return the latest instance
     */
    public static RuneManager getInstance()
    {
        if (instance == null) {
            instance = new RuneManager();
        }

        return instance;
    }

    /**
     * To make purchase when the player meet the trader
     * @param actor         player
     * @param weaponRune    weapon's runes
     * @param weapon        weapon
     * @return true if the player has enough runes to purchase the weapon, otherwise, return false
     */
    public boolean makePurchase(Player actor, int weaponRune, boolean weapon)
    {
        if(weapon == true)
        {
            if(actor.getRunesInt().getRunes() >= weaponRune) {
                actor.getRunesInt().reduceRunes(weaponRune);
                return true;
            }
        }
        return false;
    }

    /**
     * To make sell when the player meet the trader
     * @param actor         player
     * @param weaponRune    weapon's runes
     * @param weapon        weapon
     * @return true if the player has the weapon in player's weaponInventory, otherwise, return false
     */
    public boolean makeSell(Player actor, int weaponRune, boolean weapon)
    {
        if(weapon == true)
        {
            actor.getRunesInt().addRunes(weaponRune);
            return true;
        }
        return false;
    }
}
