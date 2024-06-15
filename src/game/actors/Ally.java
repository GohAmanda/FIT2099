package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropWeaponAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.tools.RandomNumberGenerator;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actions.ResetManager;
import game.actions.Resettable;
import game.enums.Status;

/**
 * Ally that will be summoned by player
 * A friend of player
 *
 *  @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Ally extends Actor implements Resettable {

    /**
     * Attributes that will be used in this class
     */
    private GameMap map;

    /**
     * countes the number of time the ally dies
     */
    private int allyDie = 0;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Ally(String name, char displayChar, int hitPoints, WeaponItem weaponItem) { //char --> A
        super(name, displayChar, hitPoints);
        this.addWeaponToInventory(weaponItem);

        ResetManager.getInstance().registerResettable(this);

    }

    /**
     *  At each turn,  a valid action will be performed.
     *
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action that will be performed by Ally
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        System.out.println(this.toString() + " | HP: " + this.printHp());
        int i = 0;
        while(i<actions.size()){
            if(actions.get(i).getClass() == DropWeaponAction.class)
            {
                actions.remove(actions.get(i));
            }
            i++;
        }
        int j =0;
        while(j< actions.size())
        {
            j++;
        }

        if (actions.size() > 0)
            return actions.get(RandomNumberGenerator.getRandomInt(actions.size()));
        else
            return new DoNothingAction();

    }

    /**
     * Ally can be attacked by any actor except for player
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that the other actors can do to player
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.map = map;
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Status.ENEMY)){
            if (!otherActor.getWeaponInventory().isEmpty()) {
                WeaponItem weaponItem = otherActor.getWeaponInventory().get(0);
                if(RandomNumberGenerator.getRandomInt(100) > 50)
                {
                    actions.add(weaponItem.getSkill(this, direction));
                }
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * When game reset:
     * player's hp will reset to the maximum
     * if player died, drop runes at the location
     * move player to the site of lost grace
     */
    @Override
    public void reset() {
        Player actor = (Player) this.map.getActorAt(map.at(36, 15));
        if(actor.hasCapability(Status.DEAD))
        {
            new DeathAction().execute(this, this.map);
            this.allyDie ++;
        }
    }

    /**
     * Player is resettable
     * @return true
     */
    @Override
    public boolean resettable() {
        return true;
    }


}
