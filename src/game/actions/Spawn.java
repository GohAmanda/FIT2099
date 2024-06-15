package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.tools.RandomNumberGenerator;
import game.environments.Environment;

/**
 * Spawn is an action that can spawn enemy on map
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Spawn extends Action {
    /**
     * the environment that can spawn enemies
     */
    private Environment env;

    /**
     * the map enemies will be spawned
     */
    private GameMap map;

    /**
     * the x location
     */
    private int x;

    /**
     * the y location
     */
    private int y;

    /**
     * the location to spawn
     */
    private  Location location;

    /**
     * Constructor
     * @param env the environment that can spawn enemies
     * @param map the map enemies will be spawned
     * @param x x
     * @param y y
     */
    public Spawn(Environment env, GameMap map, int x, int y){
        this.env = env;
        this.map = map;
        this.x = x;
        this.y = y;
    }

    /**
     * If the enemies spawned successfully, add them to the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (map.at(this.x, this.y).canActorEnter(actor) && RandomNumberGenerator.getRandomInt(100) <= this.env.getChances()){
            map.at(this.x, this.y).addActor(actor);
            return menuDescription(actor);
        }
        return null;
    }

    /**
     * Describes which enemy is spawned
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been spawned.";
    }

}