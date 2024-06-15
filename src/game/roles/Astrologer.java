package game.roles;

import game.weaponItems.Grossmesser;

/**
 * Bandit is a class that represent one of roles that has 414 hp and determine GreatKnife weapon for player.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Astrologer extends Roles{

    /**
     * Constructor
     */
    public Astrologer()
    {
        super(396, new Grossmesser());
    }
}
