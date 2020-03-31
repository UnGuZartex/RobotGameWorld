import Utility.Direction;
import Utility.Pair;

/**
 * A class representing a robot. This has coordinates and a direction.
 *
 * @invar The robot's coordinates must be at all time valid.
 *        | isValidCoordinates(getX(), getY())
 *
 * @author Alpha-team
 */
public class Robot {

    /**
     * Variable referring to the gridposition of this robot.
     */
    private Pair gridPosition;


    /**
     * Variable referring to the direction of this robot.
     */
    private Direction direction;


    /**
     * Initialise a new robot with given x and y coordinates, as
     * well as a direction.
     *
     * @param x The initial x coordinate for this robot.
     * @param y The initial x coordinate for this robot.
     * @param direction The initial direction for this robot.
     *
     * @pre The x and y coordinates must be greater than or
     *      equal to 0.
     *
     * @post The x coordinate of this robot is set to the given x coordinate.
     * @post The y coordinate of this robot is set to the given y coordinate.
     * @post The direction of this robot is set to the given direction.
     * @post The original x coordinate of this robot is set to the given x coordinate.
     * @post The original y coordinate of this robot is set to the given y coordinate.
     * @post The original direction of this robot is set to the given direction.
     */
    public Robot(int x, int y, Direction direction) {
        gridPosition = new Pair(x,y);
        this.direction = direction;
    }

    /**
     * Get the direction of this robot.
     *
     * @return The direction of this robot.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get the x coordinate of this robot.
     *
     * @return The x coordinate of this robot.
     */
    public int getX() {
        return gridPosition.getX();
    }


    /**
     * Get the y coordinate of this robot.
     *
     * @return The y coordinate of this robot.
     */
    public int getY() {
        return gridPosition.getY();
    }

    /**
     * Checks whether or not the given coordinates are valid for a robot.
     *
     * @param x The x coordinate to check.
     * @param y The Y coordinate to check.
     *
     * @return True if and only if the given coordinates are both greater than 0.
     */
    public static boolean isValidCoordinates(int x, int y) {
        return x >= 0 && y >= 0;
    }
}
