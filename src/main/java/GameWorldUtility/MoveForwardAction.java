package GameWorldUtility;

import GameWorldAPI.GameWorld.GameWorld;
import GameWorld.Level;
import GameWorldAPI.GameWorldType.Action;

public class MoveForwardAction implements Action {

    @Override
    public String getName() {
        return "Move Forward";
    }

    @Override
    public void execute(GameWorld gameWorld) {
        ((Level) gameWorld).getRobot().moveForward();
    }
}
