package game.tools;

import edu.monash.fit2099.demo.mars.actors.Player;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.Exit;
import game.surfaces.SummonSign;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Surroundings class is a concrete class that include some functions to help us to check actor's surroundings and
 * move actor to a new place.
 *
 * @author Amanda Goh, Samantha Oh Jia-Jia, Tan Yi Jin
 */
public class Surroundings {

    /**
     * To check whether has any other actor in the actor's surroundings
     * @param location
     * @param actor
     * @return list of other actors in actor's surroundings
     */
    public List<Actor> isObjectNearby(Location location, Actor actor) {
        List<Actor> listActor = new ArrayList<>();
        GameMap map = location.map();

        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (!destination.canActorEnter(actor) && destination.containsAnActor()) {
                listActor.add(destination.getActor());
            }

        }
        return listActor;
    }

    /**
     * To check whether has any other actor in the actor's one-step away surroundings
     * @param location
     * @return list of other actors in actor's one-step away surroundings
     */
    public Actor canFollow(Location location)
    {
        int x = location.x();
        int y = location.y();
        GameMap map = location.map();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (map.at(x, y).getActor().getClass() == Player.class) {
                    return map.at(x, y).getActor();
                }
            }
        }
        return null;
    }

    /**
     * For quickStepAction, check which location can actor enter in actor's surroundings
     * Then, randomly choose one location
     * @param location
     * @param direction
     * @param actor
     * @return DoNothingAction if there is no location to move. Otherwise, return MoveActorAction
     */
    public Action moveToNewPlace (Location location, String direction, Actor actor) {
        GameMap map = location.map();
        List<Location> surroundings = new ArrayList<>();
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if(destination.canActorEnter(actor))
            {
                surroundings.add(destination);
            }
        }

        if (surroundings.isEmpty()) {
            return new DoNothingAction();
        }

        Location moveTo = surroundings.get(new Random().nextInt(surroundings.size()));
        return new MoveActorAction(moveTo, direction);

    }

    /**
     * Spawn the actor at the summonsign surroundings
     * @param actor
     * @param location
     */
    public void summonActor(Actor actor, Location location)
    {
        List<Location> surroundings = new ArrayList<>();

        for(Exit exit: location.getExits()) {
            Location locationToSummon = exit.getDestination();
            if (locationToSummon.canActorEnter(actor)) {
                surroundings.add(locationToSummon);
            }
        }

        Location newLocation = surroundings.get(new Random().nextInt(surroundings.size()));
        newLocation.addActor(actor);
    }
}

