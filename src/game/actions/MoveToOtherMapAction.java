package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.surfaces.GoldenFogDoor;

/**
 * An action that allows player to move from one map to another map
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class MoveToOtherMapAction extends Action {

    /**
     * The player that will perform this action
     */
    private Player player;

    /**
     * The location that the player will move to
     */
    private Location moveToLoc;

    /**
     * The door that the player enter
     */
    private GoldenFogDoor door;

    /**
     * Constructor
     *
     * @param moveToLoc the location that the player will go to
     * @param door the door that player enter
     */
    public MoveToOtherMapAction(Location moveToLoc, GoldenFogDoor door){
        this.moveToLoc = moveToLoc;
        this.door = door;
    }

    /**
     * This will be executed when the player travels from one map to another map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        player = (Player) actor;
        player.getMap().moveActor(player, moveToLoc);
        return "Travel successfully";
    }

    /**
     * Describes which map the player went
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " goto " + this.door.getDoorName() + " map";
    }
}
