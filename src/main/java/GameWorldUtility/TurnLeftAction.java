package GameWorldUtility;

/**
 * A class for actions which turn the robot to the left.
 *
 * @author Alpha-team
 */
public class TurnLeftAction extends RobotAction {

    /**
     * Get the name of this action.
     *
     * @return The name "Turn Left".
     */
    @Override
    public String getName() {
        return "Turn Left";
    }

    /**
     * Execute this action.
     *
     * @effect The robot of this action is turned to the left.
     */
    @Override
    public void execute() {
        robot.turnLeft();
    }
}
