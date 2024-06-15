package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.tools.RandomNumberGenerator;
import game.tools.Surroundings;
import game.actors.Ally;
import game.actors.Invader;
import game.roles.Astrologer;
import game.roles.Bandit;
import game.roles.Samurai;
import game.roles.Wretch;

/**
 * An action that will spawn Ally or Invader
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class SummonAction extends Action {

    /**
     * The name of the actor that is being summoned
     */
    private String name;

    /**
     * The verb of the actor that is being summoned
     */
    private char verb;

    /**
     * The hp of the actor that is being summoned
     */
    private int hittingPoint;

    /**
     * The weapon of the actor that is being summoned
     */
    private WeaponItem weaponItem;

    /**
     * The actor that is being summoned
     */
    private Actor summonTarget;

    /**
     * This will be executed when the player decides to summon an actor. Either the actor Ally or Invader will
     * be summoned which is random. The roles of both actors will be set as random as well. Everytime the player
     * decides to summon there will have a change of having either Ally or Invader, and they might have different
     * roles each time they are being summon
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return which actor is being summoned by the player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(RandomNumberGenerator.getRandomInt(0,100) > 50){
            name = "Ally";
            verb = 'A';
        }
        else{
            name = "Invader";
            verb = 'ඞ';
        }
        int randomRolesInt = RandomNumberGenerator.getRandomInt(0,3);

        switch (randomRolesInt){
            case 0:
                hittingPoint = new Bandit().getHittingPoint();
                weaponItem= new Bandit().getWeaponItem();
                break;
            case 1:
                hittingPoint = new Samurai().getHittingPoint();
                weaponItem= new Samurai().getWeaponItem();
                break;
            case 2:
                hittingPoint = new Wretch().getHittingPoint();
                weaponItem= new Wretch().getWeaponItem();
                break;
            case 3:
                hittingPoint = new Astrologer().getHittingPoint();
                weaponItem= new Astrologer().getWeaponItem();
                break;
        }
        if (name == "Ally"){
            summonTarget = new Ally(name, verb, hittingPoint, weaponItem);
        }
        else{
            summonTarget = new Invader(name, verb, hittingPoint, weaponItem);
        }

        Surroundings surroundings = new Surroundings();
        surroundings.summonActor(summonTarget, map.locationOf(actor));

        return "Successfully summon: " + summonTarget;

    }


    /**
     * Describes the actor chooses to summon
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " chooses summon";
    }
}
