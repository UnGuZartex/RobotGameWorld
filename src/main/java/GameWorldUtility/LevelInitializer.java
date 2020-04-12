package GameWorldUtility;

import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import RobotCollection.Robot.Robot;
import GameWorld.*;
import RobotCollection.Utility.Direction;
import RobotCollection.Utility.GridPosition;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelInitializer implements GameWorldType {

    /**
     * Variable referring to the level loader.
     */
    private LevelLoader levelLoader = new LevelLoader();
    /**
     * Variable referring to the actions possible.
     */
    private ArrayList<RobotAction> actionList = new ArrayList<>(
            Arrays.asList(
                    new MoveForwardAction(),
                    new TurnLeftAction(),
                    new TurnRightAction()
            )
    );
    /**
     * Variable referring to the predicates.
     */
    private List<LevelPredicate> predicateList = new ArrayList<>(
            Collections.singletonList(
                    new WallInFrontPredicate()
            )
    );

    /**
     * Get all the actions possible.
     *
     * @return The actions list.
     */
    @Override
    public List<Action> getAllActions() {
        return new ArrayList<>(actionList);
    }

    /**
     * Get all predicates possible.
     *
     * @return The predicates list.
     */
    @Override
    public List<Predicate> getAllPredicates() {
        return new ArrayList<>(predicateList);
    }

    /**
     * TODO
     */
    @Override
    public GameWorld loadFromFile(File file) {
        return createNewGameWorld();
    }

    /**
     * TODO
     */
    @Override
    public GameWorld createNewGameWorld() {
        Robot gameWorldRobot = new Robot(new GridPosition(1,1), Direction.LEFT);
        Level returnGameWorld = levelLoader.loadLevel(gameWorldRobot);
        setCorrectGameWorld(returnGameWorld, gameWorldRobot);
        return returnGameWorld;
    }

    /**
     * Set the game world correct.
     *
     * @param level The level for this game world.
     * @param robot The robot for this game world.
     *
     * @effect The robot of the actions in the action list are set to
     *         the given robot.
     * @effect The level of the predicates in the predicate list are
     *         set to the given level.
     */
    private void setCorrectGameWorld(Level level, Robot robot) {
        for (RobotAction action : actionList) {
            action.setRobot(robot);
        }
        for (LevelPredicate predicate : predicateList) {
            predicate.setLevel(level);
        }
    }
}
