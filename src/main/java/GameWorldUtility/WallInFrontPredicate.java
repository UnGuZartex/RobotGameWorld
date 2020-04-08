package GameWorldUtility;

/**
 * A class for wall in front predicates. This predicate checks if the robot of
 * this predicates level has a wall in front or not.
 *
 * @author Alpha-team
 */
public class WallInFrontPredicate extends LevelPredicate {

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
    public boolean evaluate() {
        return level.robotHasWallInFront();
    }
}
