package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is turned to the left.
 *
 * @author Alpha-team
 */
public class LeftRobotState implements RobotState {

    /**
     * Get the name of this robot state.
     *
     * @return The string "LEFT"
     */
    @Override
    public String getName() {
        return "LEFT";
    }

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return A new grid position equal to the given grid position but
     *         with the x-coordinate decremented.
     */
    @Override
    public GridPosition getPositionForward(GridPosition gridPosition) {
        return new GridPosition(gridPosition.getX() - 1, gridPosition.getY());
    }

    /**
     * Get the state to the left of this state.
     *
     * @return The down robot state.
     */
    @Override
    public RobotState getLeftState() {
        return new DownRobotState();
    }

    /**
     * Get the state to the right of this state.
     *
     * @return The up robot state.
     */
    @Override
    public RobotState getRightState() {
        return new UpRobotState();
    }
}
