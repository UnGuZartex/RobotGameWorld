package GameWorldUtility.Actions;

import GameWorldAPI.GameWorld.GameWorld;
import GameWorld.Level;
import GameWorldAPI.GameWorldType.Action;

/**
 * A class for actions which move the robot a step forward.
 *
 * @author Alpha-team
 */
public class MoveForwardAction implements Action {

    /**
     * Get the name of this action.
     *
     * @return The name "Move Forward".
     */
    @Override
    public String getName() {
        return "Move Forward";
    }

    /**
     * Execute this action.
     *
     * @effect The robot of this action makes a move forward.
     */
    @Override
    public void execute(GameWorld gameWorld) {
        ((Level) gameWorld).getRobot().moveForward();
    }
}
