package game.enemyFactory;

import game.actors.enemies.*;
import game.environments.Environment;

/**
 * WestMapEnemyFactory is a class which implements EnemyFactory interface
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public class EastMapEnemyFactory implements EnemyFactory {
    /**
     * Create enemy in each east side environment
     * @param env environment that can spawn enemies
     * @return enemy object if the environment has the char, otherwise, return null
     */
    @Override
    public Enemy createEnemy(Environment env) {
        switch (env.getDisplayChar()) {
            case 'n':   // graveyard
                return new SkeletalBandit();
            case '&':   // Gust of wind
                return new GiantDog();
            case '~':   // Puddle of water
                return new GiantCrayfish();
            case '<':   // Cage
                return new Dog();
            case 'B':   // Barrack
                return new GodrickSoldier();
        }
        return null;
    }

}
