package game.roles;

import game.weaponItems.Club;

/**
 * Wretch is a class that represent one of roles that has 414 hp and determine Club weapon for player.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Wretch extends Roles{

    /**
     * Constructor
     */
    public Wretch() {
        super(414,  new Club());
    }

}
