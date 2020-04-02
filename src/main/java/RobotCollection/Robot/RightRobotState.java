package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;

/**
 * A class for a robot state which is turned to the right.
 *
 * @author Alpha-team
 */
public class RightRobotState implements RobotState {

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
     * Turn the given robot to the left.
     *
     * @param robot The robot to turn to the left.
     *
     * @effect Changes the robot state of the given robot to the up state.
     */
    @Override
    public void turnLeft(Robot robot) {
        robot.changeRobotState(new UpRobotState());
    }

    /**
     * Turn the given robot to the right.
     *
     * @param robot The robot to turn to the right.
     *
     * @effect Changes the robot state of the given robot to the down state.
     */
    @Override
    public void turnRight(Robot robot) {
        robot.changeRobotState(new DownRobotState());
    }
}
