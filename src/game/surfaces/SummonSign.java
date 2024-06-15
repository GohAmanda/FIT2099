package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.actions.SummonAction;
import game.actors.Ally;
import game.actors.Invader;
import game.actors.Player;
import game.enums.Status;
import game.roles.Astrologer;
import game.roles.Bandit;
import game.roles.Samurai;
import game.roles.Wretch;
import game.tools.Surroundings;

/**
 * SummonSign the ground that allows player to summon an actor
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */
public class SummonSign extends Ground {

    /**
     * Constructor
     */
    public SummonSign() {
        super('=');
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
        Display display = new Display();
        display.println("Summon sign Allowable actions");
        ActionList actions = new ActionList();

        if (actor.getClass() == Player.class){
            actions.add(new SummonAction());
        }

        return actions;
    }

}
