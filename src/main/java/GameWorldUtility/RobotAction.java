package GameWorldUtility;

import GameWorldAPI.GameWorldType.Action;
import RobotCollection.Robot.Robot;

public abstract class RobotAction implements Action {

    protected Robot robot;

    public abstract String getName();

    public abstract void execute();

    protected void setRobot(Robot robot) {
        this.robot = robot;
    }
}
