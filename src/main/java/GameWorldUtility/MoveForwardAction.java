package GameWorldUtility;


public class MoveForwardAction extends RobotAction {

    @Override
    public String getName() {
        return "Move Forward";
    }

    @Override
    public void execute() {
        robot.moveForward();
    }
}
