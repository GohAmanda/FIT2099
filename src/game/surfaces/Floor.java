package game.surfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class Floor extends Ground {
	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
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
}
