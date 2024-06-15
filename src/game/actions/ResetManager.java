package game.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public class ResetManager{

    /**
     * a list of resettables
     */
    private List<Resettable> resettables;

    /**
     * instance of resetManager, only one object can be made in ResetManager
     */
    private static ResetManager instance;

    /**
     *
     * @return the latest instance
     */
    public static ResetManager getInstance(){
        if (instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * HINT 1: where have we seen a private constructor before? Bootcamp
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Reset all resettable actors and items
     */
    public static void run() {
        for (Resettable resettable: instance.resettables){
            resettable.reset();
        }
    }

    /**
     * If the input is resettable, add them into the list
     * @param resettable an object that implement resettable interface
     */
    public void registerResettable(Resettable resettable) {
            if (resettable.resettable()){
                this.resettables.add(resettable);
        }

    }

    /**
     * Remove the object from resettables
     * @param resettable an object that implement resettable interface
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }


}
