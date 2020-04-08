package GameWorldUtility;

/**
 * A class for actions which turn the robot to the right.
 *
 * @author Alpha-team
 */
public class TurnRightAction extends RobotAction {

    /**
     * Get the name of this action.
     *
     * @return The name "Turn Right".
     */
    @Override
    public String getName() {
        return "Turn Right";
    }

    /**
     * Execute this action.
     *
     * @effect Turns the robot of this action to the right.
     */
    @Override
    public void execute() {
        robot.turnRight();
    }
}
