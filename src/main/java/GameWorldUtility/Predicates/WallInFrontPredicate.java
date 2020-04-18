package GameWorldUtility.Predicates;

import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Predicate;
import GameWorld.Level;

/**
 * A class for wall in front predicates. This predicate checks if the robot of
 * this predicates level has a wall in front or not.
 *
 * @author Alpha-team
 */
public class WallInFrontPredicate implements Predicate {

    /**
     * Get the name of this predicate.
     *
     * @return The name "Wall In Front".
     */
    @Override
    public String getName() {
        return "Wall In Front";
    }

    /**
     * Evaluates this predicate.
     *
     * @return True if and only if the robot of the level of this predicate has
     *         a wall in front.
     */
    @Override
    public boolean evaluate(GameWorld gameWorld) {
        return ((Level) gameWorld).robotHasWallInFront();
    }
}
