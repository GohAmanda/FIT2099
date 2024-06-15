package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RecoverRunesAction;
import game.actions.ResetManager;
import game.actions.Resettable;
import game.actors.Player;

/**
 * Runes is a class that represents the money held by the player and
 * can be used to purchase weapons from trader.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Runes extends Item implements Resettable{
    /**
     * the number of runes
     */
    private int runes;
    /**
     * the actor that holds the runes
     */
    private Actor actor;

    /**
     * Constructor
     * @param actor
     * @param runes
     */
    public Runes(Actor actor, int runes){
        super("Runes", '$', false);
        this.actor = actor;
        this.runes = runes;

       ResetManager.getInstance().registerResettable(this);
    }

    /**
     * a getter to get runes
     * @return runes
     */
    public int getRunes(){
        return this.runes;
    }


    /**
     * When reset, the runes will be reset to 0
     */
    @Override
    public void reset()
    {
        this.runes = 0;
    }

    /**
     * a function to add runes
     * @param runes
     */

    public void addRunes(int runes){
        this.runes += runes;
    }

    /**
     * a function to reduce runes
     * @param runes
     */
    public void reduceRunes(int runes){
        this.runes -= runes;
    }

    /**
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        for (Action action: this.getAllowableActions()){
            if (action.getClass() == RecoverRunesAction.class){
                this.removeAction(action);
                break;
            }
        }

        if(currentLocation.containsAnActor() && currentLocation.getActor().getClass() == Player.class)
        {
            this.addAction(new RecoverRunesAction(this));
        }
    }

    /**
     * Runes is resettable
     * @return true
     */
    @Override
    public boolean resettable()
    {
        return true;
    }

}


