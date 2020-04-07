package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is turned to the right.
 *
 * @author Alpha-team
 */
public class RightRobotState implements RobotState {

    /**
     * Get the name of this robot state.
     *
     * @return The string "RIGHT"
     */
    @Override
    public String getName() {
        return "RIGHT";
    }

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return A new grid position equal to the given grid position but
     *         with the x-coordinate incremented.
     */
    @Override
    public GridPosition getPositionForward(GridPosition gridPosition) {
        return new GridPosition(gridPosition.getX() + 1, gridPosition.getY());
    }

    /**
     * Get the state to the left of this state.
     *
     * @return The up robot state.
     */
    @Override
    public RobotState getLeftState() {
        return new UpRobotState();
    }

    /**
     * Get the state to the right of this state.
     *
     * @return The down robot state.
     */
    @Override
    public RobotState getRightState() {
        return new DownRobotState();
    }
}
