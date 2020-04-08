package GameWorldUtility;

import GameWorldAPI.GameWorldType.Action;
import RobotCollection.Robot.Robot;

/**
 * An abstract class for actions which operate on a robot.
 *
 * @author Alpha-team
 */
public abstract class RobotAction implements Action {

    /**
     * Variable referring to the robot this action operates on.
     */
    protected Robot robot;

    /**
     * Get the name of this action.
     *
     * @return The name of this action.
     */
    public abstract String getName();

    /**
     * Execute this action.
     *
     * @effect The robot is changed according to this action.
     */
    public abstract void execute();

    /**
     * Set the robot of this action to the given robot.
     *
     * @param robot The new robot for this action.
     *
     * @post The robot of this action is set to the given robot.
     */
    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
