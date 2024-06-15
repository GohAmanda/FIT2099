package game.actors.enemies;

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
import game.actors.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Status;
import game.items.Runes;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Enemy is an abstract class that represent Enemy
 * enemy can be reset, will drop runes after they died
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public abstract class Enemy extends Actor implements Resettable {
    /**
     * the minimum runes that enemy will drop after died.
     */
    private int minDropRunes;

    /**
     * the maximum runes that enemy will drop after died.
     */
    private int maxDropRunes;

    /**
     * A tree map that contains keys and behaviours.
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The runes that enemy will drop after died.
     */
    private Runes runes;

    /**
     * the map that locations and environments for player to play.
     */
    private static GameMap map;

    /**
     * Constructor
     * @param name          name of the enemy
     * @param enemyChar     char of the enemy
     * @param hittingPoint  hitting point of the enemy
     * @param minDropRunes  min runes of the enemy
     * @param maxDropRunes  max runes of the enemy
     */
    public Enemy(String name, char enemyChar, int hittingPoint, int minDropRunes, int maxDropRunes) {
        super(name, enemyChar, hittingPoint);
        this.minDropRunes = minDropRunes;
        this.maxDropRunes = maxDropRunes;
        this.runes = new Runes(this, RandomNumberGenerator.getRandomInt(getMinDropRunes(), getMaxDropRunes()));
        this.addItemToInventory(this.runes);
        ResetManager.getInstance().registerResettable(this);
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
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    // checking behaviour - attack -> follow -> wander
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.map = map;
        Surroundings surroundings = new Surroundings();
        List<Actor> actorList = surroundings.isObjectNearby(map.locationOf(this), this);
        Actor actorToFollow = surroundings.canFollow(map.locationOf(this));

        Action action;

        boolean isActorAround = false;
        boolean isPlayerAround = false;

        //AttackBehaviour
        if(actorList.size()>0)
        {
            for(int i = 0; i < actorList.size(); i ++)
            {
                if(!actorList.get(i).hasCapability(this.capabilitiesList().get(0)))
                {
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

        //wanderBehaviour
        this.behaviours.put(777, new WanderBehaviour());

        //action list
        for (Behaviour behaviour : behaviours.values()) {
            action = behaviour.getAction(this, map);
            if (behaviour.getClass() == FollowBehaviour.class
                    && action == null
                    && RandomNumberGenerator.getRandomInt(100) <= 10){
                return new Despawn();
            }
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
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     * The enemy can be attacked by different enemyType's enemy
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of action that the other actor can do to the enemy
     */

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        WeaponItem weaponItem;

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }

        else if(this.capabilitiesList().size() > 0 && (!otherActor.hasCapability(this.capabilitiesList().get(0)))) {
            if (!otherActor.getWeaponInventory().isEmpty()) {
                weaponItem = otherActor.getWeaponInventory().get(0);
                if (RandomNumberGenerator.getRandomInt(0, 100) > 50) {
                    actions.add(weaponItem.getSkill(this, direction));
                }
                actions.add(new AttackAction(this, direction, weaponItem));
            } else {
                if (otherActor.getIntrinsicWeapon().verb().equals("slams")) {
                    actions.add(new AreaAttackAction(direction));
                }
            }
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    /**
     * Enemy is resettable
     * @return true
     */
    @Override
    public boolean resettable()
    {
        return true;
    }

    /**
     * Remove enemy from the map if reset the game
     */
    @Override
    public void reset(){
        new Despawn().execute(this, this.map);

    }


}
