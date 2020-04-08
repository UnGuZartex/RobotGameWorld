package GameWorld;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTypeTest {

    CellType blank, wall, goal;

    @BeforeEach
    void setUp() {
        blank = CellType.BLANK;
        wall = CellType.WALL;
        goal = CellType.GOAL;
    }

    @AfterEach
    void tearDown() {
        blank = null;
        wall = null;
        goal = null;
    }

    @Test
    void canWalkOn() {
        assertTrue(blank.canWalkOn());
        assertFalse(wall.canWalkOn());
        assertTrue(goal.canWalkOn());
    }

    @Test
    void isWin() {
        assertFalse(blank.isWin());
        assertFalse(wall.isWin());
        assertTrue(goal.isWin());
    }
}