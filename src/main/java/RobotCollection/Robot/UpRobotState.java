package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is pointed upwards.
 *
 * @author Alpha-team
 */
public class UpRobotState implements RobotState {

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
        return new GridPosition(gridPosition.getX() - 1, gridPosition.getY());
    }

    /**
     * Turn the given robot to the left.
     *
     * @param robot The robot to turn to the left.
     *
     * @effect Changes the robot state of the given robot to the left state.
     */
    @Override
    public void turnLeft(Robot robot) {
        robot.changeRobotState(new LeftRobotState());
    }

    /**
     * Turn the given robot to the right.
     *
     * @param robot The robot to turn to the right.
     *
     * @effect Changes the robot state of the given robot to the right state.
     */
    @Override
    public void turnRight(Robot robot) {
        robot.changeRobotState(new RightRobotState());
    }
}
