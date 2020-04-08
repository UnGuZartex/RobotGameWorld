package GameWorld;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    Cell blank, wall, goal;

    @BeforeEach
    void setUp() {
        blank = new Cell(CellType.BLANK);
        wall = new Cell(CellType.WALL);
        goal = new Cell(CellType.GOAL);
    }

    @AfterEach
    void tearDown() {
        blank = null;
        wall = null;
        goal = null;
    }

    @Test
    void getCellType() {
        assertEquals(CellType.BLANK, blank.getCellType());
        assertEquals(CellType.WALL, wall.getCellType());
        assertEquals(CellType.GOAL, goal.getCellType());
    }
}