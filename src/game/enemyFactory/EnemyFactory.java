package game.enemyFactory;

import game.actors.enemies.Enemy;
import game.environments.Environment;

/**
 * EnemyFactory is an interface that allows for the creation of multiple instances of enemies
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public interface EnemyFactory{
    /**
     * A method to create enemy in each environment
     * @param env
     * @return
     */
    Enemy createEnemy(Environment env);
}
