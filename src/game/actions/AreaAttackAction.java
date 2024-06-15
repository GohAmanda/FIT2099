package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.tools.Surroundings;
import game.actors.Player;
import game.actors.enemies.Enemy;

import java.util.List;

/**
 * an action that perform area attack (attack surroundings)
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class AreaAttackAction extends Action {
    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Used to display the message
     */
    private Display display = new Display();

    /**
     * Constructor
     * @param direction     the direction where the attack should be performed (only used for display purposes)
     * @param weapon        the actor's weapon
     */
    public AreaAttackAction(String direction, Weapon weapon) {
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructor with intrinsic weapon as default
     * @param direction     the direction where the attack should be performed (only used for display purposes)
     */
    public AreaAttackAction(String direction) {
        this.direction = direction;
    }

    /**
     * When executed, attack all the targets(actors) that in the surroundings of the actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Surroundings surroundings = new Surroundings();
        List<Actor> ActorsNearby = surroundings.isObjectNearby(map.locationOf(actor), actor);
        
        for (Actor target: ActorsNearby){
            if (target.getClass().equals(Enemy.class) || target.getClass().equals(Player.class)){
                display.println(String.valueOf(new AttackAction(target, direction, weapon)));
            }
        }

        return menuDescription(actor);
    }

    /**
     * Describes which weapon the actor performs area attack with
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs area attack with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }

}


