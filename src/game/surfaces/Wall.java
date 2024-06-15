package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 *
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Checks whether an actor can enter this ground
	 *
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Blocks the objects that is thrown
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
