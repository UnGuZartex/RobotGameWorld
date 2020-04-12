package GameWorldUtility.Actions;

import GameWorld.Level;
import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Action;

/**
 * A class for actions which turn the robot to the right.
 *
 * @author Alpha-team
 */
public class TurnRightAction implements Action {

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
    public void execute(GameWorld gameWorld) {
        ((Level) gameWorld).getRobot().turnRight();
    }
}
