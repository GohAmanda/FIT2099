package game.surfaces;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.MoveToOtherMapAction;
import game.actors.Player;
import game.enums.Status;

/**
 * GoldenFogDoor that allows the player to travels through different maps
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class GoldenFogDoor extends Ground {
    /**
     * The attributes used in this class
     */
    private Location nextDoorLoc;
    private String doorName;

    /**
     * Constructor with parameter (doorName, nextDoorLoc)
     */
    public GoldenFogDoor(String doorName, Location nextDoorLoc) {
        super('d');
        this.nextDoorLoc = nextDoorLoc;
        this.doorName = doorName;
    }

    /**
     * Constructor with no parameter
     */
    public GoldenFogDoor() {
        super('d');
    }

    /**
     * Check whether an actor can enter or not
     *
     * @param actor the Actor to check
     * @return the actor that has the capability HOSTILE_TO_ENEMY
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Moves the actor from one map to another map
     *
     * @return an action that moves the actor from one door to another door
     */
    public Action move(){
        return new MoveToOtherMapAction(this.nextDoorLoc, this);
    }

    /**
     * Getter method for the door name
     *
     * @return the door name
     */
    public String getDoorName(){
        return this.doorName;
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

        if (location == location.map().locationOf(actor) && this.canActorEnter(actor)){
            actions.add(this.move());
        }

        return actions;
    }

    /**
     * Setter for the door
     * @param startingMap the map that the actor is at right noe
     * @param x x
     * @param y y
     * @param doorName the name of the  door
     * @param destinationMap the position of the door at the destination map
     * @param destinationX the position of the door at the x destination
     * @param destinationY the position of the door at the y destination
     */
    public void setGoldFogDoor(GameMap startingMap, int x, int y, String doorName, GameMap destinationMap, int destinationX, int destinationY){
        startingMap.at(x,y).setGround(new GoldenFogDoor(doorName, destinationMap.at(destinationX, destinationY)));
    }
}