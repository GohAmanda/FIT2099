package game.surfaces;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;

/**
 * The ground that will decrease the player's hp by 10
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */
public class ToxicLandfill extends Ground {

    /**
     * Constructor
     */
    public ToxicLandfill() {
        super('☢');
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && location.getActor().getClass() == Player.class){
            Player player = (Player) location.getActor();
            player.hurt(10);
        }
    }
}
