package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Runes;

/**
 * A status that Heavy Skeletal Swordsman or Skeletal Bandit will turn into when they died
 * It keeps do nothing for 3 turns, if it keeps alive, it will be respawned
 */
public class PileOfBones extends Enemy {
    /**
     * the number of turn that pile of bones alive for
     */
    private int numberOfTurn;

    /**
     * the enemy before it turns into pile of bones
     */
    private Actor bonesEnemy;

    /**
     * the runes it will drop
     */
    private Runes runes;

    /**
     * Constructor
     *
     * A new PileOfBones object with predefined attributes.
     * The PileOfBones is initialized with a name, char, hp, minimum drop runes and maximum drop runes
     */
    public PileOfBones() {
        super("Pile of Bones", 'X', 0, 0, 0);
        this.numberOfTurn = 0;
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
        this.numberOfTurn ++;
        if(this.numberOfTurn > 3)
        {
            Actor respawnActor = this.bonesEnemy;
            Location bonesLocation = map.locationOf(this);
            map.removeActor(this);
            respawnActor.heal(200);
            map.at(bonesLocation.x(), bonesLocation.y()).addActor(respawnActor);
        }

        return new DoNothingAction();
    }


    /**
     * Setter to set the original skeletal type enemy
     * @param enemy
     */
    public void setEnemy(Actor enemy)
    {
        this.bonesEnemy = enemy;
    }
}
