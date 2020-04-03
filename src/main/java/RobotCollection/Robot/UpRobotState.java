package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is pointed upwards.
 *
 * @author Alpha-team
 */
public class UpRobotState implements RobotState {

    /**
     * Get the name of this robot state.
     *
     * @return The string "UP"
     */
    @Override
    public String getName() {
        return "UP";
    }

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return A new grid position equal to the given grid position but
     *         with the y-coordinate decremented. (inverted y-axis)
     */
    @Override
    public GridPosition getPositionForward(GridPosition gridPosition) {
        return new GridPosition(gridPosition.getX(), gridPosition.getY() - 1);
    }

    /**
     * Get the state to the left of this state.
     *
     * @return The left robot state.
     */
    @Override
    public RobotState getLeftState() {
        return new LeftRobotState();
    }

    /**
     * Get the state to the right of this state.
     *
     * @return The right robot state.
     */
    @Override
    public RobotState getRightState() {
        return new RightRobotState();
    }
}
