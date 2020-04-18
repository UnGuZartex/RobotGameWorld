package GameWorldUtility;

import GameWorld.CellType;
import GameWorld.Grid;
import GameWorld.Level;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelLoaderTest {

    LevelLoader levelLoader;
    Robot defaultRobot, robot;
    Grid defaultGrid, grid;
    Level level;

    @BeforeEach
    void setUp() {
        levelLoader = new LevelLoader();
        defaultRobot = levelLoader.getDefaultRobot();
        defaultGrid = levelLoader.getDefaultGrid();
    }

    @AfterEach
    void tearDown() {
        levelLoader = null;
        defaultRobot = null;
        robot = null;
        defaultGrid = null;
        grid = null;
        level = null;
    }

    @Test
    void loadDefaultLevel() {
        level = levelLoader.loadDefaultLevel();
        assertNotEquals(defaultRobot, level.getRobot());
        assertEquals(defaultRobot.getGridPosition().getX(), level.getRobot().getGridPosition().getX());
        assertEquals(defaultRobot.getGridPosition().getY(), level.getRobot().getGridPosition().getY());
        assertEquals(defaultRobot.getDirection(), level.getRobot().getDirection());
        // Can't reach grid because of encapsulation
    }

    @Test
    void getDefaultRobot() {
        assertEquals(1, defaultRobot.getGridPosition().getX());
        assertEquals(1, defaultRobot.getGridPosition().getY());
        assertEquals("LEFT", defaultRobot.getDirection());
    }

    @Test
    void getDefaultRobot_indistinctCopy() {
        robot = levelLoader.getDefaultRobot();
        assertNotEquals(robot, defaultRobot);
        assertEquals(1, robot.getGridPosition().getX());
        assertEquals(1, robot.getGridPosition().getY());
        assertEquals("LEFT", robot.getDirection());
        assertEquals(1, defaultRobot.getGridPosition().getX());
        assertEquals(1, defaultRobot.getGridPosition().getY());
        assertEquals("LEFT", defaultRobot.getDirection());
        robot.moveForward();
        robot.turnLeft();
        robot.moveForward();
        assertEquals(0, robot.getGridPosition().getX());
        assertEquals(2, robot.getGridPosition().getY());
        assertEquals("DOWN", robot.getDirection());
        assertEquals(1, defaultRobot.getGridPosition().getX());
        assertEquals(1, defaultRobot.getGridPosition().getY());
        assertEquals("LEFT", defaultRobot.getDirection());
    }

    @Test
    void getDefaultGrid() {
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(0,0)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(1,0)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(2,0)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(0,1)).getCellType());
        assertEquals(CellType.BLANK, defaultGrid.getCellAt(new GridPosition(1,1)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(2,1)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(0,2)).getCellType());
        assertEquals(CellType.GOAL, defaultGrid.getCellAt(new GridPosition(1,2)).getCellType());
        assertEquals(CellType.WALL, defaultGrid.getCellAt(new GridPosition(2,2)).getCellType());
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(3,0)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(3,1)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(3,2)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(3,3)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(0,3)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(1,3)); });
        assertThrows(IndexOutOfBoundsException.class, () -> { defaultGrid.getCellAt(new GridPosition(2,3)); });
    }

    @Test
    void getDefaultGrid_indistinctCopy() {
        grid = levelLoader.getDefaultGrid();
        assertNotEquals(grid, defaultGrid);
    }
}