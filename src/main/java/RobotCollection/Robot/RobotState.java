package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * An interface for the state of a robot.
 *
 * @author Alpha-team
 */
public interface RobotState {

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return The grid position one step forward according to this robot state.
     */
    GridPosition getPositionForward(GridPosition gridPosition);

    /**
     * Turn the given robot to the left.
     *
     * @param robot The robot to turn to the left.
     *
     * @effect Changes the state of the robot to the correct state.
     */
    void turnLeft(Robot robot);

    /**
     * Turn the given robot to the right.
     *
     * @param robot The robot to turn to the right.
     *
     * @effect Changes the state of the robot to the correct state.
     */
    void turnRight(Robot robot);
}