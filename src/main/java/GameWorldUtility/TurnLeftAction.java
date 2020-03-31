package GameWorldUtility;


public class TurnLeftAction extends RobotAction {

    @Override
    public String getName() {
        return "Turn Left";
    }

    @Override
    public void execute() {
        getRobot().turnLeft();
    }
}
