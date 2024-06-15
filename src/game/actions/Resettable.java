package game.actions;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Amanda Goh, Samantha Oh, Tan Yi Jin
 *
 */
public interface Resettable {
    /**
     * a method that the resettable actors and items
     */
    void reset();

    /**
     * Get a boolean from the actors and items. Use this method to check whether the items and actors is resettable.
     * @return false if the items and actors are not resettable, otherwise, return true.
     */
    default boolean resettable()
    {
        return false;
    }
}
