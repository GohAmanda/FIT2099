package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.enemyFactory.EastMapEnemyFactory;
import game.enemyFactory.EnemyFactory;
import game.enemyFactory.WestMapEnemyFactory;
import game.actions.Spawn;

/**
 * Environment is an abstract class that represents a place that can spawn different enemies
 *
 * @author Amanda Goh, Samantha Oh, Tan Yi Jin
 */
public abstract class Environment extends Ground{
    /**
     * chance to spawn enemies in each turn
     */
    private double chances;
    /**
     * the enemy that will be spawned
     */
    private Actor spawnedActor;
    /**
     * an factory method to check which enemy should be spawned
     */
    private EnemyFactory enemyFactory;

    /**
     * Constructor
     * @param displayChar
     * @param chances
     */
    public Environment(char displayChar, double chances){
        super(displayChar);
        this.chances = chances;
    }

    /**
     * getter to get chances
     * @return chances
     */
    public double getChances(){
        return chances;
    }


    /**
     * In every turn, to spawn enemy
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        NumberRange xRange = location.map().getXRange();
        if (location.x() < (xRange.max() / 2)){
            this.enemyFactory = new WestMapEnemyFactory();}
        else{
            this.enemyFactory = new EastMapEnemyFactory();}

        this.spawnedActor = enemyFactory.createEnemy(this);
        new Spawn(this, location.map(), location.x(), location.y()).execute(this.spawnedActor, location.map());

    }
}
