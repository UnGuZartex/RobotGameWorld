package GameWorldUtility;


public class TurnRightAction extends RobotAction {

    @Override
    public String getName() {
        return "Turn Right";
    }

    @Override
    public void execute() {
        getRobot().turnRight();
    }
}
