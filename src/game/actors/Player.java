package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.enums.Status;
import game.items.CrimsonTearsFlask;
import game.items.GoldenRunes;
import game.items.RemembranceOfTheGrafted;
import game.items.Runes;
import game.surfaces.GoldenFogDoor;
import game.surfaces.SiteOfLostGrace;
import game.tools.RandomNumberGenerator;
import game.tools.Surroundings;

import java.util.List;
import java.util.Scanner;


/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class Player extends Actor implements Resettable {

    /**
     * Attributes that will be used in this class
     */
    private GameMap map;

    /**
     * The number of times the player has dies
     */
    private int playerDied = 0;

    /**
     * The location of the runes
     */
    private Location runesLocation;

    /**
     * a new menu object
     */
    private Menu menu = new Menu();

    /**
     * runes of player
     */
    private Runes runes;

    /**
     * flask of crimson tears of the player
     */
    private CrimsonTearsFlask flaskOfCrimsonTears;

    /**
     * The previous location that the player is on
     */
    private Location previousLocation;

    /**
     * site of lost grace of this game
     */
    private SiteOfLostGrace siteOfLostGrace;

    /**
     * display to print out selection
     */
    private Display display = new Display();


    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.flaskOfCrimsonTears = new CrimsonTearsFlask();
        this.addItemToInventory(this.flaskOfCrimsonTears);
        this.runes = new Runes(this, 0);

        addCapability(Status.RESTING);
        addCapability(Status.HOSTILE_TO_ENEMY);

        this.addItemToInventory(new RemembranceOfTheGrafted());
        ResetManager.getInstance().registerResettable(this);
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
        // save the location of siteOfLostGrace
        if(this.map == null) {
            this.siteOfLostGrace = (SiteOfLostGrace) map.at(36, 15).getGround();
        }
        this.map = map;

        if(this.hasCapability(Status.DEAD))
            this.removeCapability(Status.DEAD);

        // will show player's hp and runes every turn
        display.println(this.toString() + " | HP: " + this.printHp() + " Runes: " + this.runes.getRunes());

        // if the player's location change, update the previous location
        if(map.locationOf(this) != this.previousLocation)
            this.previousLocation = map.locationOf(this);

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null) {
            return lastAction.getNextAction();
        }

        Action actionRet = menu.showMenu(this, actions, display);

        return actionRet;
    }

    /**
     * During the attackAction, player can choose weapon from player's weaponInventory to fight others
     *
     * @return a weapon
     */
    public WeaponItem chooseWeapon() {
        int selection;
        WeaponItem weapon;
        Scanner input = new Scanner(System.in);

        display.println("Choose your weapon:");
        display.println("-------------------------\n");
        int i = 0;
        for (WeaponItem weaponItem : getWeaponInventory()) {
            display.println(i + " - " + weaponItem.toString());
            i++;
        }
        display.println(i + " - IntrinsicWeapon");
        selection = input.nextInt();

        if (selection == getWeaponInventory().size()) {
            weapon = null;
        } else {
            weapon = getWeaponInventory().get(selection);
        }
        return weapon;
    }


    /**
     * When game reset:
     * player's hp will reset to the maximum
     * if player died, drop runes at the previous location
     * move player to the site of lost grace
     */
    @Override
    public void reset() {
        if(this.playerDied > 0 && this.runesLocation.getItems().get(0).getClass() == Runes.class)
        {
            this.runesLocation.removeItem(this.runesLocation.getItems().get(0));
            this.playerDied = 0;

        }
        this.resetMaxHp(this.getMaxHp());

        if(hasCapability(Status.DEAD)){
            this.playerDied++;
            new DropItemAction(this.runes).execute(this, this.previousLocation.map());

            this.runesLocation = this.previousLocation;

            if (this.map.locationOf(this) != this.siteOfLostGrace.getLoc()){
                display.println(new MoveActorAction(this.siteOfLostGrace.getLoc(), "to Site of Lost Grace").execute(this, this.map));
            }
        }
    }

    /**
     * During the attackAction, if the actor didn't have weapon in their weaponInventory, will use this intrinsicWeapon
     *
     * @return intrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(11, "punch", 100);
    }

    /**
     * Gets the flaskOfCrimsonTears
     *
     * @return flaskOfCrimsonTears
     */
    public CrimsonTearsFlask getFlaskOfCrimsonTears() {
        return this.flaskOfCrimsonTears;
    }

    /**
     * Getter to get player runes
     * @return
     */
    public Runes getRunesInt() {
        return this.runes;
    }

    /**
     * Player is resettable
     * @return true
     */
    @Override
    public boolean resettable() {
        return true;
    }

    /**
     * Increase the hitting point
     * @param points number of hitpoints to add.
     */
    @Override
    public void heal(int points) {
        display.println("The number of crimson left is:" + this.getFlaskOfCrimsonTears().getNumOfFlask());
        hitPoints += points;
        hitPoints = Math.min(hitPoints, maxHitPoints);
    }

    /**
     * The player can be attacked by any actor except for Ally
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
     * Getter method to get the map
     * @return the map
     */
    public GameMap getMap(){
        return this.map;
    }

    /**
     * Getter method to get the max hp
     * @return the hp as the max hp
     */
    public boolean isMaxHp(){
        return this.hitPoints == this.maxHitPoints;
    }

}



