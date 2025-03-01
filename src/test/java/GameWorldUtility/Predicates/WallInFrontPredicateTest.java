package GameWorldUtility.Predicates;

import GameWorld.Cell;
import GameWorld.CellType;
import GameWorld.Grid;
import GameWorld.Level;
import GameWorldAPI.GameWorld.GameWorld;
import GameWorldAPI.GameWorldType.Predicate;
import RobotCollection.Robot.*;
import RobotCollection.Robot.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class WallInFrontPredicateTest {

    Predicate wallInFrontPredicate;
    GameWorld levelBeforeWall, levelNotBeforeWall, levelBeforeNothing;
    Robot robotBeforeWall, robotNotBeforeWall, robotBeforeNothing;
    int xBeforeWall, yBeforeWall, xNotBeforeWall, yNotBeforeWall, xBeforeNothing, yBeforeNothing;
    GridPosition gridPositionBeforeWall, gridPositionNotBeforeWall, gridPositionBeforeNothing;
    Random random;
    Cell[][] cellsBeforeWall, cellsNotBeforeWall, cellsBeforeNothing;
    Grid gridBeforeWall, gridNotBeforeWall, gridBeforeNothing;
    static final int MIN_X = 1, MAX_X = 1, MIN_Y = 1, MAX_Y = 1; // Max is one for easier creation of levels (especially grids)

    @BeforeEach
    void setUp() {
        random = new Random();
        xBeforeWall = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yBeforeWall = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        xNotBeforeWall = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yNotBeforeWall = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        xBeforeNothing = 0;
        yBeforeNothing = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;

        gridPositionBeforeWall = new GridPosition(xBeforeWall, yBeforeWall);
        gridPositionNotBeforeWall = new GridPosition(xNotBeforeWall, yNotBeforeWall);
        gridPositionBeforeNothing = new GridPosition(xBeforeNothing, yBeforeNothing);

        robotBeforeWall = new Robot(gridPositionBeforeWall, Direction.UP);
        robotNotBeforeWall = new Robot(gridPositionNotBeforeWall, Direction.DOWN);
        robotBeforeNothing = new Robot(gridPositionBeforeNothing, Direction.LEFT);

        cellsBeforeWall = new Cell[][]{
                {new Cell(CellType.GOAL), new Cell(CellType.WALL), new Cell(CellType.BLANK)},
                {new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.WALL)},
                {new Cell(CellType.BLANK), new Cell(CellType.WALL), new Cell(CellType.BLANK)}
        };
        cellsNotBeforeWall = new Cell[][]{
                {new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.WALL)},
                {new Cell(CellType.GOAL), new Cell(CellType.BLANK), new Cell(CellType.BLANK)},
                {new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.WALL)}
        };
        cellsBeforeNothing = new Cell[][]{
                {new Cell(CellType.BLANK), new Cell(CellType.BLANK), new Cell(CellType.BLANK)},
                {new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.WALL)},
                {new Cell(CellType.WALL), new Cell(CellType.GOAL), new Cell(CellType.WALL)}
        };

        gridBeforeWall = new Grid(cellsBeforeWall);
        gridNotBeforeWall = new Grid(cellsNotBeforeWall);
        gridBeforeNothing = new Grid(cellsBeforeNothing);

        levelBeforeWall = new Level(robotBeforeWall, gridBeforeWall);
        levelNotBeforeWall = new Level(robotNotBeforeWall, gridNotBeforeWall);
        levelBeforeNothing = new Level(robotBeforeNothing, gridBeforeNothing);

        wallInFrontPredicate = new WallInFrontPredicate();
    }

    @AfterEach
    void tearDown() {
        random = null;
        gridPositionBeforeWall = null;
        gridPositionNotBeforeWall = null;
        gridPositionBeforeNothing = null;
        robotBeforeWall = null;
        robotNotBeforeWall = null;
        robotBeforeNothing = null;
        cellsBeforeWall = null;
        cellsNotBeforeWall = null;
        cellsBeforeNothing = null;
        gridBeforeWall = null;
        gridNotBeforeWall = null;
        gridBeforeNothing = null;
        levelBeforeWall = null;
        levelNotBeforeWall = null;
        levelBeforeNothing = null;
        wallInFrontPredicate = null;
    }

    @Test
    void getName() {
        assertEquals("Wall In Front", wallInFrontPredicate.getName());
    }

    @Test
    void evaluate_beforeWall() {
        assertTrue(wallInFrontPredicate.evaluate(levelBeforeWall));
    }

    @Test
    void evaluate_notBeforeWall() {
        assertFalse(wallInFrontPredicate.evaluate(levelNotBeforeWall));
    }

    @Test
    void evaluate_beforeNothing() {
        assertTrue(wallInFrontPredicate.evaluate(levelBeforeNothing));
    }
}