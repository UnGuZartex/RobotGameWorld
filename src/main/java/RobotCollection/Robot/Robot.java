package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;
import GameWorld.History.GenericHistory;
import GameWorld.History.HistoryTracked;
import GameWorldAPI.History.Snapshot;
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
     * History of the robot
     */
    private GenericHistory history;


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
        gridPosition = position;
        this.direction = direction;
    }

    public Robot(Robot robot) {
        gridPosition = new Pair(robot.getGridPosition());
        direction = robot.getDirection();
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
     * Get the gridPosition of the robot
     * @return a copy of the grid position of the robot
     */
    public Direction getDirection() {
        return direction;
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
     * @effect The position, and the direction of the robot is changed
     */
    public void updatePositionAndDirection(Pair newPosition, Direction newDirection) {
        gridPosition = newPosition;
        direction = newDirection;
    }

    /**
     * Gets the position of the block in front of the robot
     * @return position in front of the robot
     */
    public GridPosition getForwardPosition() {
        return direction.getForwardPosition(gridPosition);
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
     *
     * @throws IllegalStateException
     *         If the robot can't move a step forward.
     */
    public void moveForward() throws IllegalStateException {
        GridPosition newGridPosition = getPositionForward();
        if (isValidPosition(newGridPosition)) {
            gridPosition = newGridPosition;
        } else {
            throw new IllegalStateException("This robot can't move forward!");
        }
    }
}
