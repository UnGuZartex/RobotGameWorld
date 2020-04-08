package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;
import RobotCollection.Utility.Direction;

/**
 * A class representing a robot. This has coordinates and a direction.
 *
 * @invar The robot's coordinates must be valid at all time.
 *        | isValidPosition(getGridPosition())
 *
 * @author Alpha-team
 */
public class Robot {

    /**
     * Variable referring to the grid position of this robot.
     */
    private GridPosition gridPosition;
    /**
     * Variable referring to the state of this robot.
     */
    private Direction direction;


    /**
     * Initialise a new robot with given x and y coordinates, as
     * well as a direction.
     *
     * @param gridPosition The grid position for this robot.
     * @param direction The direction for this robot.
     *
     * @post The grid position of this robot is set to the given grid position,
     *       if and only if this given position is valid.
     * @throws IllegalArgumentException
     *         If the given grid position is not a valid position.
     */
    public Robot(GridPosition gridPosition, Direction direction) {
        this.gridPosition = gridPosition;
        this.direction = direction;
    }

    public Robot copy() {
        return new Robot(gridPosition, direction);
    }

    /**
     * Return the grid position of this robot.
     *
     * @return The grid position of this robot.
     */
    public GridPosition getGridPosition() {
        return gridPosition;
    }

    /**
     * Get the direction of this robot
     *
     * @return A string representing the direction of the robot
     */
    public String getDirection() {
        return direction.name();
    }

    /**
     * Checks whether the given coordinates are valid for a robot.
     *
     * @param gridPosition The grid position to check.
     *
     * @return True if and only if both x and y coordinate of the given grid position are
     *         greater than 0, false otherwise.
     */
    public static boolean isValidPosition(GridPosition gridPosition) {
        return gridPosition.getX() >= 0 &&
               gridPosition.getY() >= 0;
    }


    /**
     * Gets the position of the block in front of the robot
     * @return position in front of the robot
     */
    public GridPosition getForwardPosition() {
        return direction.getPositionForward(gridPosition);
    }

    /**
     * Turn this robot to the left.
     *
     * @post The robot state is set to the state to the left of this robot's state.
     */
    public void turnLeft() {
        direction = direction.turnLeft();
    }

    /**
     * Turn this robot to the right.
     *
     * @post The robot state is set to the state to the right of this robot's state.
     */
    public void turnRight() {
        direction = direction.turnRight();
    }

    /**
     * Move this robot one position forward.
     *
     * @post The robot position is set one step forward forward if
     *       this position is a valid position.
     */
    public void moveForward() {
        this.gridPosition = getForwardPosition();
    }
}
