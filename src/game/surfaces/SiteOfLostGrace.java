package game.surfaces;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.actions.RestAction;
import game.actors.Player;
import game.enums.Status;


/**
 * Site of Lost Grace is a class that represents a rest stop and rebirth location for player.
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */
public class SiteOfLostGrace extends Ground {
    /**
     * name of Site of Lost Grace
     */
    private String name;

    private Location loc;

    /**
     * Constructor.
     *
     */
    public SiteOfLostGrace(Location loc) {
        super('U');
        this.name = "The First Step";
        this.loc = loc;
        addCapability(Status.RESTING);
    }

    /**
     * Getter to get site of lost grace name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter to get the location
     * @return the location
     */

    public Location getLoc(){
        return this.loc;
    }

    /**
     * @param actor the Actor to check
     * @return true if the actor is player
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return (actor.getClass() == Player.class);
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
            actions.add(new RestAction());
        }

        return actions;
    }




}
