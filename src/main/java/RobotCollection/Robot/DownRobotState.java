package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is pointed downwards.
 *
 * @author Alpha-team
 */
public class DownRobotState implements RobotState {

    /**
     * Get the grid position a step forward from the given grid position.
     *
     * @param gridPosition The grid position one step forward.
     *
     * @return A new grid position equal to the given grid position but
     *         with the y-coordinate incremented. (inverted y-axis)
     */
    @Override
    public GridPosition getPositionForward(GridPosition gridPosition) {
        return new GridPosition(gridPosition.getX(), gridPosition.getY() + 1);
    }

    /**
     * Turn the given robot to the left.
     *
     * @param robot The robot to turn to the left.
     *
     * @effect Changes the robot state of the given robot to the right state.
     */
    @Override
    public void turnLeft(Robot robot) {
        robot.changeRobotState(new RightRobotState());
    }

    /**
     * Turn the given robot to the right.
     *
     * @param robot The robot to turn to the right.
     *
     * @effect Changes the robot state of the given robot to the left state.
     */
    @Override
    public void turnRight(Robot robot) {
        robot.changeRobotState(new LeftRobotState());
    }
}
