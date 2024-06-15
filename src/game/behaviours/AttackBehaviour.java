package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * AttackBehaviour is a class that implements Behaviour interface which do nothing but
 * we marked to implement attack action
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class AttackBehaviour implements Behaviour {
    /**
     * The action that the actor can do
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return null. It is because if this method return null, it will randomly choose any attack action from the action list
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }
}
