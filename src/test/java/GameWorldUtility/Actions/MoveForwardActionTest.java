package GameWorldUtility.Actions;

import GameWorld.Cell;
import GameWorld.CellType;
import GameWorld.Grid;
import GameWorld.Level;
import GameWorldAPI.GameWorldType.Action;
import RobotCollection.Robot.*;
import RobotCollection.Utility.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MoveForwardActionTest {

    int xUp, yUp, xDown, yDown, xLeft, yLeft, xRight, yRight;
    GridPosition gridPositionUp, gridPositionDown, gridPositionLeft, gridPositionRight;
    Robot robotUp, robotDown, robotLeft, robotRight;
    Random random;
    Cell[][] cellsUp, cellsDown, cellsLeft, cellsRight;
    Grid gridUp, gridDown, gridRight, gridLeft;
    Level levelUp, levelDown, levelLeft, levelRight;
    int width, height;
    static final int MIN_X = 1, MIN_Y = 1, MAX_WIDTH = 20, MAX_HEIGHT = 20, MIN_WIDTH = 3, MIN_HEIGHT = 3;
    Action moveForwardAction;

    @BeforeEach
    void setUp() {
        random = new Random();

        width = random.nextInt(MAX_WIDTH + 1 - MIN_WIDTH) + MIN_WIDTH;
        height = random.nextInt(MAX_HEIGHT + 1 - MIN_HEIGHT) + MIN_HEIGHT;

        xUp = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yUp = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        xDown = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yDown = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        xLeft = random.nextInt(width - 1- MIN_X) + MIN_X;
        yLeft = random.nextInt(height - 1 - MIN_Y) + MIN_Y;
        xRight = random.nextInt(width - 1 - MIN_X) + MIN_X;
        yRight = random.nextInt(height - 1 - MIN_Y) + MIN_Y;

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
        cellsUp[robotUp.getPositionForward().getX()][robotUp.getPositionForward().getY()] = new Cell(CellType.BLANK);

        cellsDown = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsDown[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsDown[xDown][yDown] = new Cell(CellType.BLANK);
        cellsDown[robotDown.getPositionForward().getX()][robotDown.getPositionForward().getY()] = new Cell(CellType.BLANK);

        cellsLeft = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsLeft[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsLeft[xLeft][yLeft] = new Cell(CellType.BLANK);
        cellsLeft[robotLeft.getPositionForward().getX()][robotLeft.getPositionForward().getY()] = new Cell(CellType.BLANK);

        cellsRight = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cellsRight[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        cellsRight[xRight][yRight] = new Cell(CellType.BLANK);
        cellsRight[robotRight.getPositionForward().getX()][robotRight.getPositionForward().getY()] = new Cell(CellType.BLANK);

        gridUp = new Grid(cellsUp);
        gridDown = new Grid(cellsDown);
        gridLeft = new Grid(cellsLeft);
        gridRight = new Grid(cellsRight);

        levelUp = new Level(robotUp, gridUp);
        levelDown = new Level(robotDown, gridDown);
        levelLeft = new Level(robotLeft, gridLeft);
        levelRight = new Level(robotRight, gridRight);

        moveForwardAction = new MoveForwardAction();
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

        moveForwardAction = null;
    }

    @Test
    void getName() {
        assertEquals("Move Forward", moveForwardAction.getName());
    }

    @Test
    void execute_up() {
        assertEquals(xUp, levelUp.getRobot().getGridPosition().getX());
        assertEquals(yUp, levelUp.getRobot().getGridPosition().getY());
        moveForwardAction.execute(levelUp);
        assertEquals(xUp, levelUp.getRobot().getGridPosition().getX());
        assertEquals(yUp - 1, levelUp.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_down() {
        assertEquals(xDown, levelDown.getRobot().getGridPosition().getX());
        assertEquals(yDown, levelDown.getRobot().getGridPosition().getY());
        moveForwardAction.execute(levelDown);
        assertEquals(xDown, levelDown.getRobot().getGridPosition().getX());
        assertEquals(yDown + 1, levelDown.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_left() {
        assertEquals(xLeft, levelLeft.getRobot().getGridPosition().getX());
        assertEquals(yLeft, levelLeft.getRobot().getGridPosition().getY());
        moveForwardAction.execute(levelLeft);
        assertEquals(xLeft - 1, levelLeft.getRobot().getGridPosition().getX());
        assertEquals(yLeft, levelLeft.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_right() {
        assertEquals(xRight, levelRight.getRobot().getGridPosition().getX());
        assertEquals(yRight, levelRight.getRobot().getGridPosition().getY());
        moveForwardAction.execute(levelRight);
        assertEquals(xRight + 1, levelRight.getRobot().getGridPosition().getX());
        assertEquals(yRight, levelRight.getRobot().getGridPosition().getY());
    }
}