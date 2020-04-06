package GameWorldUtility;

import RobotCollection.Utility.Direction;
import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import RobotCollection.Robot.Robot;
import GameWorld.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelInitializer implements GameWorldType {

    private LevelLoader levelLoader = new LevelLoader();

    private ArrayList<RobotAction> actionList = new ArrayList<>(
            Arrays.asList(
                    new MoveForwardAction(),
                    new TurnLeftAction(),
                    new TurnRightAction()
            )
    );

    private List<LevelPredicate> predicateList = new ArrayList<>(
            Collections.singletonList(
                    new WallInFrontPredicate()
            )
    );

    @Override
    public List<Action> getAllActions() {
        return new ArrayList<>(actionList);
    }

    @Override
    public List<Predicate> getAllPredicates() {
        return new ArrayList<>(predicateList);
    }

    @Override
    public GameWorld loadFromFile(File file) {
        return levelLoader.loadLevel();
    }

    @Override
    public GameWorld createNewGameworld() {
        Robot gameWorldRobot = new Robot(1,1, Direction.LEFT);
        Level returnGameWorld = getLevel(gameWorldRobot);
        setCorrectGameWorld(returnGameWorld, gameWorldRobot);
        return returnGameWorld;
    }

    private Level getLevel(Robot gameWorldRobot) {
        Cell[][] cells = new Cell[][]{new Cell[]{new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)},
                new Cell[]{new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.GOAL)},
                new Cell[]{new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)}};
        return new Level(gameWorldRobot, cells);
    }

    private void setCorrectGameWorld(Level level, Robot robot) {
        for (RobotAction action : actionList) {
            action.setRobot(robot);
        }
        for (LevelPredicate predicate : predicateList) {
            predicate.setLevel(level);
        }
    }
}
