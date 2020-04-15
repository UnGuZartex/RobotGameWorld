package GameWorldUtility.Actions;

import GameWorld.Cell;
import GameWorld.CellType;
import GameWorld.Grid;
import GameWorld.Level;
import GameWorldAPI.GameWorldType.Action;
import RobotCollection.Robot.Robot;
import RobotCollection.Robot.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TurnRightActionTest {

    int xUp, yUp, xDown, yDown, xLeft, yLeft, xRight, yRight;
    GridPosition gridPositionUp, gridPositionDown, gridPositionLeft, gridPositionRight;
    int width, height;
    Robot robotUp, robotDown, robotLeft, robotRight;
    Random random;
    Cell[][] cellsUp, cellsDown, cellsLeft, cellsRight;
    Grid gridUp, gridDown, gridRight, gridLeft;
    Level levelUp, levelDown, levelLeft, levelRight;
    static final int MIN_X = 0, MIN_Y = 0, MAX_WIDTH = 20, MAX_HEIGHT = 20, MIN_WIDTH = 1, MIN_HEIGHT = 1;
    Action turnRightAction;

    @BeforeEach
    void setUp() {
        random = new Random();

        width = random.nextInt(MAX_WIDTH + 1 - MIN_WIDTH) + MIN_WIDTH;
        height = random.nextInt(MAX_HEIGHT + 1 - MIN_HEIGHT) + MIN_HEIGHT;

        xUp = random.nextInt(width - MIN_X) + MIN_X;
        yUp = random.nextInt(height - MIN_Y) + MIN_Y;
        xDown = random.nextInt(width - MIN_X) + MIN_X;
        yDown = random.nextInt(height - MIN_Y) + MIN_Y;
        xLeft = random.nextInt(width - MIN_X) + MIN_X;
        yLeft = random.nextInt(height - MIN_Y) + MIN_Y;
        xRight = random.nextInt(width - MIN_X) + MIN_X;
        yRight = random.nextInt(height - MIN_Y) + MIN_Y;

        gridPositionUp = new GridPosition(xUp, yUp);
        gridPositionDown = new GridPosition(xDown, yDown);
        gridPositionLeft = new GridPosition(xLeft, yLeft);
        gridPositionRight = new GridPosition(xRight, yRight);

        robotUp = new Robot(gridPositionUp, Direction.UP);
        robotDown = new Robot(gridPositionDown, Direction.DOWN);
        robotLeft = new Robot(gridPositionLeft, Direction.LEFT);
        robotRight = new Robot(gridPositionRight, Direction.RIGHT);

        cellsUp = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsUp[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsUp[xUp][yUp] = new Cell(CellType.BLANK);

        cellsDown = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsDown[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsDown[xDown][yDown] = new Cell(CellType.BLANK);

        cellsLeft = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsLeft[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsLeft[xLeft][yLeft] = new Cell(CellType.BLANK);

        cellsRight = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsRight[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsRight[xRight][yRight] = new Cell(CellType.BLANK);

        gridUp = new Grid(cellsUp);
        gridDown = new Grid(cellsDown);
        gridLeft = new Grid(cellsLeft);
        gridRight = new Grid(cellsRight);

        levelUp = new Level(robotUp, gridUp);
        levelDown = new Level(robotDown, gridDown);
        levelLeft = new Level(robotLeft, gridLeft);
        levelRight = new Level(robotRight, gridRight);

        turnRightAction = new TurnRightAction();
    }

    @AfterEach
    void tearDown() {
        random = null;

        gridPositionUp = null;
        gridPositionDown = null;
        gridPositionLeft = null;
        gridPositionRight = null;

        robotUp = null;
        robotDown = null;
        robotLeft = null;
        robotRight = null;

        cellsUp = null;
        cellsDown = null;
        cellsLeft = null;
        cellsRight = null;

        gridUp = null;
        gridDown = null;
        gridLeft = null;
        gridRight = null;

        levelUp = null;
        levelDown = null;
        levelLeft = null;
        levelRight = null;

        turnRightAction = null;
    }

    @Test
    void getName() {
        assertEquals("Turn Right", turnRightAction.getName());
    }

    @Test
    void execute_up() {
        assertEquals("UP", levelUp.getRobot().getDirection());
        turnRightAction.execute(levelUp);
        assertEquals("RIGHT", levelUp.getRobot().getDirection());
        turnRightAction.execute(levelUp);
        assertEquals("DOWN", levelUp.getRobot().getDirection());
        turnRightAction.execute(levelUp);
        assertEquals("LEFT", levelUp.getRobot().getDirection());
        turnRightAction.execute(levelUp);
        assertEquals("UP", levelUp.getRobot().getDirection());
    }

    @Test
    void execute_down() {
        assertEquals("DOWN", levelDown.getRobot().getDirection());
        turnRightAction.execute(levelDown);
        assertEquals("LEFT", levelDown.getRobot().getDirection());
        turnRightAction.execute(levelDown);
        assertEquals("UP", levelDown.getRobot().getDirection());
        turnRightAction.execute(levelDown);
        assertEquals("RIGHT", levelDown.getRobot().getDirection());
        turnRightAction.execute(levelDown);
        assertEquals("DOWN", levelDown.getRobot().getDirection());
    }

    @Test
    void execute_left() {
        assertEquals("LEFT", levelLeft.getRobot().getDirection());
        turnRightAction.execute(levelLeft);
        assertEquals("UP", levelLeft.getRobot().getDirection());
        turnRightAction.execute(levelLeft);
        assertEquals("RIGHT", levelLeft.getRobot().getDirection());
        turnRightAction.execute(levelLeft);
        assertEquals("DOWN", levelLeft.getRobot().getDirection());
        turnRightAction.execute(levelLeft);
        assertEquals("LEFT", levelLeft.getRobot().getDirection());
    }

    @Test
    void execute_right() {
        assertEquals("RIGHT", levelRight.getRobot().getDirection());
        turnRightAction.execute(levelRight);
        assertEquals("DOWN", levelRight.getRobot().getDirection());
        turnRightAction.execute(levelRight);
        assertEquals("LEFT", levelRight.getRobot().getDirection());
        turnRightAction.execute(levelRight);
        assertEquals("UP", levelRight.getRobot().getDirection());
        turnRightAction.execute(levelRight);
        assertEquals("RIGHT", levelRight.getRobot().getDirection());
    }
}