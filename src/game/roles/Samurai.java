package game.roles;

import game.weaponItems.Uchigatana;

/**
 * Samurai is a class that represent one of roles that has 455 hp and determine Uchigatana weapon for player.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Samurai extends Roles{
    /**
     * Constructor
     */
    public Samurai() {
        super(455,  new Uchigatana());
    }
}
