package GameWorldUtility;

import GameWorld.Level;
import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Action;

public class TurnLeftAction implements Action {

    @Override
    public String getName() {
        return "Turn Left";
    }

    @Override
    public void execute(GameWorld gameWorld) {
        ((Level) gameWorld).getRobot().turnLeft();
    }
}
