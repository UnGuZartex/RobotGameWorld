package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * An interface for the state of a robot.
 *
 * @author Alpha-team
 */
public interface RobotState {

    /**
     * Get the name of this robot state.
     *
     * @return The name of this robot state.
     */
    String getName();

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return The grid position one step forward according to this robot state.
     */
    GridPosition getPositionForward(GridPosition gridPosition);

    /**
     * Get the state to the left of this state.
     *
     * @return The state to the left of this robot state.
     */
    RobotState getLeftState();

    /**
     * Get the state to the right of this state.
     *
     * @return The state to the right of this robot state.
     */
    RobotState getRightState();
}