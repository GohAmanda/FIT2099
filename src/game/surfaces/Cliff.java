package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.actors.Player;
import game.enums.Status;

/**
 * Cliff that the player can enter but will die immediately
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Cliff extends Ground {

    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    /**
     * Checks if an actor can actor this ground
     * @param actor the Actor to check
     * @return the actor that has the capability HOSTILE_TO_ENEMY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * The actions that actors can perform on this ground
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of actions that can be performed by the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.getClass() == Player.class){
            actions.add(new DeathAction());
        }

        return actions;
    }
}
