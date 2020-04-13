package GameWorld;

import RobotCollection.Robot.Robot;
import RobotCollection.Utility.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    Grid grid1by1Blank, grid1by1Wall;
    Robot robotOriginUp;

    @BeforeEach
    void setUp() {
        grid1by1Blank = new Grid(new Cell[][] {{new Cell(CellType.BLANK)}});
        grid1by1Wall = new Grid(new Cell[][] {{new Cell(CellType.WALL)}});
        robotOriginUp = new Robot(new GridPosition(0,0), Direction.UP);
    }

    @AfterEach
    void tearDown() {
        grid1by1Blank = null;
        grid1by1Wall = null;
        robotOriginUp = null;
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
    }

    @Test
    void isInvalidGrid_invalid() {
        assertTrue(Level.isInvalidGrid(null));
    }

    @Test
    void isInvalidGrid_valid() {
        assertFalse(Level.isInvalidGrid(grid1by1Wall));
        assertFalse(Level.isInvalidGrid(grid1by1Blank));
    }



    @Test
    void getRobot() {
    }

    @Test
    void robotHasWallInFront() {
    }

    @Test
    void executeAction() {
    }

    @Test
    void mayNotDoAction() {
    }

    @Test
    void evaluatePredicate() {
    }

    @Test
    void createSnapshot() {
    }

    @Test
    void loadSnapshot() {
    }

    @Test
    void paint() {
    }

    @Test
    void testToString() {
    }
}