package GameWorldUtility;

import GameWorld.Level;
import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Action;


public class TurnRightAction implements Action {

    @Override
    public String getName() {
        return "Turn Right";
    }

    @Override
    public void execute(GameWorld gameWorld) {
        ((Level) gameWorld).getRobot().turnRight();
    }
}
