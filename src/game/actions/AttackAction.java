package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.actors.enemies.PileOfBones;
import game.enums.EnemyType;
import game.items.Runes;
import game.weaponItems.Club;

import java.util.Random;
import java.util.Scanner;

/**
 * an action to attack other actor
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private Actor target;

	/**
	 * The direction of incoming attack.
	 */
	private String direction;

	/**
	 * Random number generator
	 */
	private Random rand = new Random();

	/**
	 * Weapon used for the attack
	 */
	private Weapon weapon;

	/**
	 * Used to display the message
	 */
	private Display display = new Display();


	/**
	 * Constructor.
	 *
	 * @param target    the Actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction, Weapon weapon) {
		this.target = target;
		this.direction = direction;
		this.weapon = weapon;
	}

	/**
	 * Constructor with intrinsic weapon as default
	 *
	 * @param target    the actor to attack
	 * @param direction the direction where the attack should be performed (only used for display purposes)
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
	 * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
	 *
	 * @param actor The actor performing the attack action.
	 * @param map   The map the actor is on.
	 * @return the result of the attack, e.g. whether the target is killed, etc.
	 * @see DeathAction
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		if (actor.getClass() == Player.class) {
			this.weapon = ((Player) actor).chooseWeapon();
			if (this.weapon == null) {
				this.weapon = actor.getIntrinsicWeapon();
			} else if (this.weapon.getClass() != Club.class) {
				int selection;
				Scanner input = new Scanner(System.in);
				display.println("Do you want to use unique skill?");
				display.println("-------------------------\n");
				display.println("1 - Yes");
				display.println("2 - No");
				selection = input.nextInt();

				if (selection == 1) {
					return this.weapon.getSkill(target, direction).execute(target, map);
				}
			}
		} else if (this.weapon == null) {
			this.weapon = actor.getIntrinsicWeapon();
		}

		if (!(rand.nextInt(100) <= this.weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = this.weapon.damage();
		String result = actor + " " + this.weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		// if player kill enemy
		if (!target.isConscious() && actor.getClass() == Player.class) {
			if (target.hasCapability(EnemyType.SKELETAL)) {    // if player kill skeletal type enemy
				return turnIntoPileOfBones(map, (Enemy) target);
			}
			result += new DeathAction().execute(target, map);
		}

		// if enemy kill other enemies
		else if (!target.isConscious() && target.getClass() != Player.class && target.getClass() != Player.class) {
			if (target.hasCapability(EnemyType.SKELETAL)) {    // if enemy kill skeletal type enemy
				return turnIntoPileOfBones(map, (Enemy) target);
			} else {
				map.removeActor(target);
			}
		}

		// if player die
		else if (!target.isConscious() && target.getClass() == Player.class) {
			result += new DeathAction().execute(target, map);
		}

		return result;
	}


	/**
	 * Describes which target the actor is attacking with which weapon
	 *
	 * @param actor The actor performing the action.
	 * @return a description used for the menu UI
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

	/**
	 * When the enemy turns into pile of bones this method will be called. It changes the enemy into pile
	 * of bones.
	 * Saves the current location of the skeletal type enemy, created a pile of bones and in the pile of bones
	 * the setEnemy and setRunes method is used to save the information of the enemy. Then in the map the location
	 * of the enemy will be replaced to bones
	 *
	 * @param map saves the current location of the enemy
	 * @param enemy The actor that turns into pile of bones
	 * @return do nothing action
	 */
	public String turnIntoPileOfBones(GameMap map, Enemy enemy){
		Location bonesLocation = map.locationOf(enemy);
		PileOfBones bones = new PileOfBones();
		bones.setEnemy(enemy);
		bones.addItemToInventory(new Runes(bones, enemy.getRunes().getRunes()));
		map.removeActor(enemy);
		bonesLocation.addActor(bones);
		return new DoNothingAction().execute(enemy, map);
	}
}

