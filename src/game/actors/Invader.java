package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.tools.RandomNumberGenerator;
import game.tools.Surroundings;
import game.actions.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.items.Runes;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Invader extends Actor implements Resettable {
    /**
     * Attributes that will be used in this class
     */
    private GameMap map;

    /**
     * The number of times the invader has died
     */
    private int invaderDied = 0;
    /**
     * the minimum runes that enemy will drop after died.
     */
    private int minDropRunes = 1358;

    /**
     * the maximum runes that enemy will drop after died.
     */
    private int maxDropRunes = 5578;
    /**
     * The runes that enemy will drop after died.
     */
    private Runes runes;
    /**
     * A tree map that contains keys and behaviours.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Invader(String name, char displayChar, int hitPoints, WeaponItem weaponItem) {
        super(name, displayChar, hitPoints);
        this.runes = new Runes(this, RandomNumberGenerator.getRandomInt(getMinDropRunes(), getMaxDropRunes()));
        this.addItemToInventory(this.runes);
        this.addCapability(Status.ENEMY);
        ResetManager.getInstance().registerResettable(this);

    }

    /**
     * At each turn,  a valid action will be performed.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Surroundings surroundings = new Surroundings();
        List<Actor> actorList = surroundings.isObjectNearby(map.locationOf(this), this);
        Actor actorToFollow = surroundings.canFollow(map.locationOf(this));

        Action action;
        boolean isActorAround = false;
        boolean isPlayerAround = false;


        if (actorList.size() > 0 ){
            for(int i = 0; i < actorList.size(); i ++)
            {
                if(!actorList.get(i).hasCapability(this.capabilitiesList().get(0))){
                    this.behaviours.put(10, new AttackBehaviour());
                    isActorAround = true;
                }
            }

        }
        if (!isActorAround) {
            this.behaviours.remove(10);
        }

        // follow behaviour
        if(actorToFollow != null)
        {
            this.behaviours.put(222, new FollowBehaviour(actorToFollow));
            isPlayerAround = true;
        }
        if (!isPlayerAround){
            this.behaviours.remove(222);
        }

        this.behaviours.put(777, new WanderBehaviour());

        for (Behaviour behaviour : behaviours.values()) {
            action = behaviour.getAction(this, map);

            if(action == null) // if attack behaviour
            {
                int i = 0;
                while(i < actions.size()){
                    if (actions.get(i).getClass() == AreaAttackAction.class ||
                            actions.get(i).getClass() == AttackAction.class){
                        i++;
                    }
                    else
                        actions.remove(actions.get(i));
                }

                if (actions.size() > 0)
                    return actions.get(RandomNumberGenerator.getRandomInt(actions.size()));
                else
                    return new DoNothingAction();
            }
            if(action != null) {
                return action;
            }
        }
        return null;
    }

    /**
     * The invader can be attacked by any actor
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
        WeaponItem weaponItem;

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        else if(this.capabilitiesList().size() > 0 && !otherActor.hasCapability(this.capabilitiesList().get(0)))
        {
            if (!otherActor.getWeaponInventory().isEmpty()) {
                weaponItem = otherActor.getWeaponInventory().get(0);
                if(RandomNumberGenerator.getRandomInt(0,100) > 50)
                {
                    actions.add(weaponItem.getSkill(this, direction));
                }
                actions.add(new AttackAction(this, direction, weaponItem));
            }
            else{
                if (otherActor.getIntrinsicWeapon().verb().equals("slams")){
                    actions.add(new AreaAttackAction(direction));
                }
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * getter to get minDropRunes
     * @return minDropRunes
     */
    public int getMinDropRunes(){return minDropRunes;}

    /**
     * getter to get maxDropRunes
     * @return maxDropRunes
     */
    public int getMaxDropRunes(){return maxDropRunes;}

    public Runes getRunes()
    {
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
            this.invaderDied ++;
        }
    }

}
