package GameWorld;

import GameWorldAPI.GameWorld.Result;
import GameWorldAPI.GameWorldType.Action;
import GameWorldAPI.GameWorldType.Predicate;
import GameWorldAPI.Utility.Snapshot;
import GameWorldUtility.Actions.MoveForwardAction;
import GameWorldUtility.Actions.TurnLeftAction;
import GameWorldUtility.Actions.TurnRightAction;
import GameWorldUtility.Predicates.WallInFrontPredicate;
import RobotCollection.Robot.Robot;
import RobotCollection.Robot.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    Grid grid1by1Blank, grid1by1Wall, gridBeforeBlank, gridBeforeWall, gridBeforeGoal;
    Robot robotOriginUp, robotBeforeBlank, robotBeforeWall, robotBeforeGoal, robot;
    Level level1by1UpBlank, levelBeforeBlank, levelBeforeWall, levelBeforeGoal;
    Cell[][] cellsBeforeBlank, cellsBeforeWall, cellsBeforeGoal;
    Random random;
    int width, height, xBeforeBlank, yBeforeBlank, xBeforeWall, yBeforeWall, xBeforeGoal, yBeforeGoal;
    static final int MIN_X = 1, MIN_Y = 1, MAX_WIDTH = 20, MAX_HEIGHT = 20, MIN_WIDTH = 3, MIN_HEIGHT = 3;
    Action moveForward, turnLeft,turnRight;
    Predicate wallInFront;
    Snapshot snapshot;

    @BeforeEach
    void setUp() {
        grid1by1Blank = new Grid(new Cell[][] {{new Cell(CellType.BLANK)}});
        grid1by1Wall = new Grid(new Cell[][] {{new Cell(CellType.WALL)}});
        robotOriginUp = new Robot(new GridPosition(0,0), Direction.UP);
        level1by1UpBlank = new Level(robotOriginUp, grid1by1Blank);

        random = new Random();
        width = random.nextInt(MAX_WIDTH + 1 - MIN_WIDTH) + MIN_WIDTH;
        height = random.nextInt(MAX_HEIGHT + 1 - MIN_HEIGHT) + MIN_HEIGHT;

        xBeforeBlank = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yBeforeBlank = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        robotBeforeBlank = new Robot(new GridPosition(xBeforeBlank, yBeforeBlank), Direction.UP);
        cellsBeforeBlank = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsBeforeBlank[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsBeforeBlank[xBeforeBlank][yBeforeBlank] = new Cell(CellType.BLANK);
        cellsBeforeBlank[robotBeforeBlank.getPositionForward().getX()][robotBeforeBlank.getPositionForward().getY()] = new Cell(CellType.BLANK);
        gridBeforeBlank = new Grid(cellsBeforeBlank);
        levelBeforeBlank = new Level(robotBeforeBlank, gridBeforeBlank);

        xBeforeWall = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yBeforeWall = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        robotBeforeWall = new Robot(new GridPosition(xBeforeWall, yBeforeWall), Direction.LEFT);
        cellsBeforeWall = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsBeforeWall[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsBeforeWall[xBeforeWall][yBeforeWall] = new Cell(CellType.BLANK);
        cellsBeforeWall[robotBeforeWall.getPositionForward().getX()][robotBeforeWall.getPositionForward().getY()] = new Cell(CellType.WALL);
        gridBeforeWall = new Grid(cellsBeforeWall);
        levelBeforeWall = new Level(robotBeforeWall, gridBeforeWall);

        xBeforeGoal = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yBeforeGoal = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        robotBeforeGoal = new Robot(new GridPosition(xBeforeGoal, yBeforeGoal), Direction.RIGHT);
        cellsBeforeGoal = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsBeforeGoal[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsBeforeGoal[xBeforeGoal][yBeforeGoal] = new Cell(CellType.BLANK);
        cellsBeforeGoal[robotBeforeGoal.getPositionForward().getX()][robotBeforeGoal.getPositionForward().getY()] = new Cell(CellType.GOAL);
        gridBeforeGoal = new Grid(cellsBeforeGoal);
        levelBeforeGoal = new Level(robotBeforeGoal, gridBeforeGoal);

        moveForward = new MoveForwardAction();
        turnLeft = new TurnLeftAction();
        turnRight = new TurnRightAction();
        wallInFront = new WallInFrontPredicate();
    }

    @AfterEach
    void tearDown() {
        random = null;
        grid1by1Blank = null;
        grid1by1Wall = null;
        robotOriginUp = null;
        level1by1UpBlank = null;

        cellsBeforeBlank = null;
        gridBeforeBlank = null;
        robotBeforeBlank = null;
        levelBeforeBlank = null;

        cellsBeforeWall = null;
        gridBeforeWall = null;
        robotBeforeWall = null;
        levelBeforeWall = null;
        
        cellsBeforeGoal = null;
        gridBeforeGoal = null;
        robotBeforeGoal = null;
        levelBeforeGoal = null;

        moveForward = null;
        turnLeft = null;
        turnRight = null;
        wallInFront = null;

        snapshot = null;
        robot = null;
    }

    @Test
    void Level_InvalidRobot() {
        assertFalse(Level.isInvalidGrid(grid1by1Blank));
        assertThrows(IllegalArgumentException.class, () -> new Level(null, grid1by1Blank));
    }

    @Test
    void Level_InvalidGrid() {
        assertFalse(Level.isInvalidRobot(robotOriginUp));
        assertThrows(IllegalArgumentException.class, () -> new Level(robotOriginUp, null));
    }

    @Test
    void Level_RobotCantWalkInGrid() {
        assertFalse(Level.isInvalidRobot(robotOriginUp));
        assertFalse(Level.isInvalidGrid(grid1by1Wall));
        assertFalse(grid1by1Wall.isWalkablePosition(robotOriginUp.getGridPosition()));
        assertThrows(IllegalStateException.class, () -> new Level(robotOriginUp, grid1by1Wall));
    }

    @Test
    void isInvalidRobot_invalid() {
        assertTrue(Level.isInvalidRobot(null));
    }

    @Test
    void isInvalidRobot_valid() {
        assertFalse(Level.isInvalidRobot(robotOriginUp));
        assertFalse(Level.isInvalidRobot(robotBeforeBlank));
        assertFalse(Level.isInvalidRobot(robotBeforeWall));
        assertFalse(Level.isInvalidRobot(robotBeforeGoal));
    }

    @Test
    void isInvalidGrid_invalid() {
        assertTrue(Level.isInvalidGrid(null));
    }

    @Test
    void isInvalidGrid_valid() {
        assertFalse(Level.isInvalidGrid(grid1by1Wall));
        assertFalse(Level.isInvalidGrid(grid1by1Blank));
        assertFalse(Level.isInvalidGrid(gridBeforeBlank));
        assertFalse(Level.isInvalidGrid(gridBeforeWall));
        assertFalse(Level.isInvalidGrid(gridBeforeGoal));
    }

    @Test
    void getRobot_mayDoAction() {
        assertFalse(level1by1UpBlank.mayNotDoAction());
        assertEquals(robotOriginUp, level1by1UpBlank.getRobot());
        assertFalse(levelBeforeBlank.mayNotDoAction());
        assertEquals(robotBeforeBlank, levelBeforeBlank.getRobot());
        assertFalse(levelBeforeWall.mayNotDoAction());
        assertEquals(robotBeforeWall, levelBeforeWall.getRobot());
        assertFalse(levelBeforeGoal.mayNotDoAction());
        assertEquals(robotBeforeGoal, levelBeforeGoal.getRobot());
    }

    @Test
    void getRobot_mayNotDoAction() {
        levelBeforeWall.executeAction(moveForward);
        levelBeforeGoal.executeAction(moveForward);
        assertTrue(levelBeforeWall.mayNotDoAction());
        assertTrue(levelBeforeGoal.mayNotDoAction());
        assertThrows(IllegalStateException.class, () -> levelBeforeWall.getRobot());
        assertThrows(IllegalStateException.class, () -> levelBeforeGoal.getRobot());
    }

    @Test
    void robotHasWallInFront_outGrid() {
        assertTrue(level1by1UpBlank.robotHasWallInFront());
    }

    @Test
    void robotHasWallInFront_inGrid() {
        assertFalse(levelBeforeBlank.robotHasWallInFront());
        assertTrue(levelBeforeWall.robotHasWallInFront());
        assertFalse(levelBeforeGoal.robotHasWallInFront());
    }

    @Test
    void mayNotDoAction() {
        assertFalse(level1by1UpBlank.mayNotDoAction());
        assertFalse(levelBeforeBlank.mayNotDoAction());
        assertFalse(levelBeforeWall.mayNotDoAction());
        assertFalse(levelBeforeGoal.mayNotDoAction());
        levelBeforeWall.executeAction(moveForward);
        levelBeforeGoal.executeAction(moveForward);
        assertTrue(levelBeforeWall.mayNotDoAction());
        assertTrue(levelBeforeGoal.mayNotDoAction());
    }

    @Test
    void executeAction_mayDoAction() {
        assertFalse(levelBeforeBlank.mayNotDoAction());
        assertFalse(levelBeforeWall.mayNotDoAction());
        assertFalse(levelBeforeGoal.mayNotDoAction());
        assertEquals(Result.SUCCESS, levelBeforeBlank.executeAction(turnLeft));
        assertEquals(Result.SUCCESS, levelBeforeBlank.executeAction(turnRight));
        assertEquals(Result.SUCCESS, levelBeforeBlank.executeAction(moveForward));
        assertEquals(Result.FAILURE, levelBeforeWall.executeAction(moveForward));
        assertEquals(Result.END, levelBeforeGoal.executeAction(moveForward));
    }

    @Test
    void executeAction_mayNotDoAction() {
        levelBeforeWall.executeAction(moveForward);
        levelBeforeGoal.executeAction(moveForward);
        assertTrue(levelBeforeWall.mayNotDoAction());
        assertTrue(levelBeforeGoal.mayNotDoAction());
        assertThrows(IllegalStateException.class, () -> levelBeforeWall.executeAction(turnLeft));
        assertThrows(IllegalStateException.class, () -> levelBeforeGoal.executeAction(turnRight));
    }

    @Test
    void evaluatePredicate() {
        assertFalse(levelBeforeBlank.evaluatePredicate(wallInFront));
        assertTrue(levelBeforeWall.evaluatePredicate(wallInFront));
        assertFalse(levelBeforeGoal.evaluatePredicate(wallInFront));
    }

    @Test
    void createSnapshot() {
        robot = robotBeforeGoal.copy();
        snapshot = levelBeforeGoal.createSnapshot();
        levelBeforeGoal.executeAction(turnLeft);
        levelBeforeGoal.executeAction(turnRight);
        levelBeforeGoal.executeAction(moveForward);
        assertThrows(IllegalStateException.class, () -> levelBeforeGoal.executeAction(turnRight));

        levelBeforeGoal.loadSnapshot(snapshot);
        assertNotEquals(levelBeforeGoal.getRobot(), robotBeforeGoal);
        assertEquals(robot.getGridPosition().getX(), levelBeforeGoal.getRobot().getGridPosition().getX());
        assertEquals(robot.getGridPosition().getY(), levelBeforeGoal.getRobot().getGridPosition().getY());
        assertEquals(robot.getDirection(), levelBeforeGoal.getRobot().getDirection());
    }

    @Test
    void loadSnapshot_differentLevel() {
        snapshot = levelBeforeGoal.createSnapshot();
        levelBeforeWall.loadSnapshot(snapshot);
        assertNotEquals(levelBeforeGoal.getRobot(), robotBeforeWall);
        assertEquals(xBeforeGoal, levelBeforeWall.getRobot().getGridPosition().getX());
        assertEquals(yBeforeGoal, levelBeforeWall.getRobot().getGridPosition().getY());
        assertEquals(robotBeforeGoal.getDirection(), levelBeforeWall.getRobot().getDirection());
    }

    @Test
    void loadSnapshot_oneBack() {
        robot = robotBeforeWall.copy();
        snapshot = levelBeforeWall.createSnapshot();
        levelBeforeWall.executeAction(turnLeft);
        levelBeforeWall.loadSnapshot(snapshot);
        assertNotEquals(levelBeforeBlank.getRobot(), levelBeforeWall);
        assertEquals(robot.getGridPosition().getX(), levelBeforeWall.getRobot().getGridPosition().getX());
        assertEquals(robot.getGridPosition().getY(), levelBeforeWall.getRobot().getGridPosition().getY());
        assertEquals(robot.getDirection(), levelBeforeWall.getRobot().getDirection());
    }

    @Test
    void loadSnapshot_multipleSteps() {
        robot = robotBeforeBlank.copy();
        snapshot = levelBeforeBlank.createSnapshot();
        levelBeforeBlank.executeAction(moveForward);
        levelBeforeBlank.executeAction(turnLeft);
        levelBeforeBlank.executeAction(turnLeft);
        levelBeforeBlank.loadSnapshot(snapshot);
        assertNotEquals(levelBeforeBlank.getRobot(), robotBeforeBlank);
        assertEquals(robot.getGridPosition().getX(), levelBeforeBlank.getRobot().getGridPosition().getX());
        assertEquals(robot.getGridPosition().getY(), levelBeforeBlank.getRobot().getGridPosition().getY());
        assertEquals(robot.getDirection(), levelBeforeBlank.getRobot().getDirection());
    }
}