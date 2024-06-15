package game.roles;

import game.weaponItems.GreatKnife;

/**
 * Bandit is a class that represent one of roles that has 414 hp and determine GreatKnife weapon for player.
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class Bandit extends Roles{

    /**
     * Constructor
     */
    public Bandit()
    {
        super(414, new GreatKnife());
    }
}
