package GameWorldUtility;

import GameWorld.Level;
import GameWorldAPI.GameWorldType.Predicate;

/**
 * An abstract class for predicates which operate on levels.
 *
 * @author Alpha-team
 */
public abstract class LevelPredicate implements Predicate {

    /**
     * Variable referring to the level this predicate operates on.
     */
    protected Level level;

    /**
     * Get the name of this predicate.
     *
     * @return The name of this predicate.
     */
    public abstract String getName();

    /**
     * Evaluates this predicate.
     *
     * @return True if and only if the level of this predicate meets the condition
     *         of this predicate.
     */
    public abstract boolean evaluate();

    /**
     * Set the level this predicate operates on.
     *
     * @param level The new level for this predicate.
     *
     * @post The level of this predicate is set to the given level.
     */
    protected void setLevel(Level level) {
        this.level = level;
    }
}
