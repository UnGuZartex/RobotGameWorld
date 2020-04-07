package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

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
    private RobotState robotState;

    /**
     * Initialise this robot with given grid position and robot state.
     *
     * @param gridPosition The grid position for this robot.
     * @param robotState The robot state for this robot.
     *
     * @post The grid position of this robot is set to the given grid position,
     *       if and only if this given position is valid.
     * @post The robot state of this robot is set to the given robot state.
     *
     * @throws IllegalArgumentException
     *         If the given grid position is not a valid position.
     */
    public Robot(GridPosition gridPosition, RobotState robotState) throws IllegalArgumentException {
        if (isValidPosition(gridPosition)) {
            this.gridPosition = gridPosition;
        } else {
            throw new IllegalArgumentException("Invalid position for a robot!");
        }
        this.robotState = robotState;
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
     * Return the direction this robot is directed to.
     *
     * @return The name of the robot state of this robot.
     */
    public String getDirection() {
        return robotState.getName();
    }

    /**
     * Checks whether or not the given grid position is a valid position for this robot.
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
     * Get the position forward of this robot.
     *
     * @return The position forward according to the robot state.
     */
    public GridPosition getPositionForward() {
        return robotState.getPositionForward(gridPosition);
    }

    /**
     * Turn this robot to the left.
     *
     * @post The robot state is set to the state to the left of this robot's state.
     */
    public void turnLeft() {
        robotState = robotState.getLeftState();
    }

    /**
     * Turn this robot to the right.
     *
     * @post The robot state is set to the state to the right of this robot's state.
     */
    public void turnRight() {
        robotState = robotState.getRightState();
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
