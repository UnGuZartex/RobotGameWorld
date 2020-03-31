package GameWorldUtility;

import GameWorldAPI.GameWorldType.Action;
import RobotCollection.Robot.Robot;

public abstract class RobotAction implements Action {

    private Robot robot;

    public abstract String getName();

    public abstract void execute();

    protected Robot getRobot() {
        return robot;
    }

    protected void setRobot(Robot robot) {
        this.robot = robot;
    }
}
