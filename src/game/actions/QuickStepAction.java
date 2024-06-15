package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.tools.Surroundings;

/**
 * An action that can be performed using great knife
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class QuickStepAction extends Action {

    /**
     * the direction where attack should be performed
     */
    private String direction;

    /**
     * the target to attack
     */
    private Actor target;

    /**
     * the taken action
     */
    private Action action;

    /**
     * a visual guide to the locations and environments in a game
     */
    private GameMap map;

    /**
     * Used to display the message
     */
    private Display display = new Display();

    /**
     * Constructor.
     *
     * @param otherActor the actor using this action
     * @param direction the location we move to
     */
    public QuickStepAction(Actor otherActor, String direction) {
        this.target = otherActor;
        this.direction = direction;
        this.action = new DoNothingAction();
    }

    /**
     * When the weapon has a unique skill called QuickStepAction, execute AttackAction and MoveActorAction
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.map = map;
        String attackResult = String.valueOf(new AttackAction(actor, this.direction).execute(actor, map));
        display.println(attackResult);

        Surroundings surroundings = new Surroundings();
        this.action = surroundings.moveToNewPlace(map.locationOf(actor), this.direction, actor);
        this.action.execute(actor,map);

        if(this.action.getClass() == DoNothingAction.class){
            return "Quickstep failed";
        }
        else{
            return menuDescription(actor);
        }
    }

    /**
     * Describes which actor is killed
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "use GreatKnife to perform Quick Step Action";
    }
}
