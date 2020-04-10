package GameWorld;

import GameWorldAPI.GameWorld.Result;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    Grid grid;
    Cell[][] cells, invalidCells;
    Random random;
    int width, height;
    final static int MAX_WIDTH = 20, MAX_HEIGHT = 20, MIN_WIDTH = 1, MIN_HEIGHT = 1;
    GridPosition position;

    @BeforeEach
    void setUp() {
        random = new Random();
        width = random.nextInt(MAX_WIDTH + 1 - MIN_WIDTH) + MIN_WIDTH;
        height = random.nextInt(MAX_HEIGHT + 1 - MIN_HEIGHT) + MIN_HEIGHT;

        cells = new Cell[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cells[w][h] = new Cell(CellType.values()[random.nextInt(CellType.values().length)]);
            }
        }
        invalidCells = new Cell[][] { {} };

        grid = new Grid(cells);
    }

    @AfterEach
    void tearDown() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                cells[w][h] = null;
            }
        }
        cells = null;
        grid = null;
        position = null;
    }

    @Test
    void Grid_invalidCells() {
        assertThrows(IllegalArgumentException.class, () -> { new Grid(invalidCells); });
    }

    @Test
    void copy() {
        Grid copy = grid.copy();
        assertNotEquals(grid, copy);
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                position = new GridPosition(w, h);
                assertNotEquals(grid.getCellAt(position), copy.getCellAt(position));
                assertEquals(grid.getCellAt(position).getCellType(), copy.getCellAt(position).getCellType());
            }
        }
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, height);
            assertThrows(IndexOutOfBoundsException.class, () -> { copy.getCellAt(position); });
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(width, h);
            assertThrows(IndexOutOfBoundsException.class, () -> { copy.getCellAt(position); });
        }
    }

    @Test
    void areValidCells_invalid() {
        assertFalse(Grid.areValidCells(null));
        assertFalse(Grid.areValidCells(invalidCells));
    }

    @Test
    void areValidCells_valid() {
        assertTrue(Grid.areValidCells(cells));
    }

    @Test
    void getCellAt_inGrid() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                position = new GridPosition(w, h);
                assertEquals(cells[w][h], grid.getCellAt(position));
            }
        }
    }

    @Test
    void getCellAt_OutGrid() {
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, height);
            assertThrows(IndexOutOfBoundsException.class, () -> { grid.getCellAt(position); });
        }
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, -1);
            assertThrows(IndexOutOfBoundsException.class, () -> { grid.getCellAt(position); });
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(width, h);
            assertThrows(IndexOutOfBoundsException.class, () -> { grid.getCellAt(position); });
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(-1, h);
            assertThrows(IndexOutOfBoundsException.class, () -> { grid.getCellAt(position); });
        }
    }

    @Test
    void isWalkablePosition_inGrid() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                position = new GridPosition(w, h);
                assertEquals(cells[w][h].getCellType().canWalkOn(), grid.isWalkablePosition(position));
            }
        }
    }

    @Test
    void isWalkablePosition_outGrid() {
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, height);
            assertFalse(grid.isWalkablePosition(position));
        }
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, -1);
            assertFalse(grid.isWalkablePosition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(width, h);
            assertFalse(grid.isWalkablePosition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(-1, h);
            assertFalse(grid.isWalkablePosition(position));
        }
    }

    @Test
    void isWinningPosition_inGrid() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                position = new GridPosition(w, h);
                assertEquals(cells[w][h].getCellType().isWin(), grid.isWinningPosition(position));
            }
        }
    }

    @Test
    void isWinningPosition_outGrid() {
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, height);
            assertFalse(grid.isWinningPosition(position));
        }
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, -1);
            assertFalse(grid.isWinningPosition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(width, h);
            assertFalse(grid.isWinningPosition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(-1, h);
            assertFalse(grid.isWinningPosition(position));
        }
    }

    @Test
    void resultingCondition_inGrid() {
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                position = new GridPosition(w, h);
                if (cells[w][h].getCellType() == CellType.BLANK) {
                    assertEquals(Result.SUCCESS, grid.resultingCondition(position));
                }
                if (cells[w][h].getCellType() == CellType.GOAL) {
                    assertEquals(Result.END, grid.resultingCondition(position));
                }
                if (cells[w][h].getCellType() == CellType.WALL) {
                    assertEquals(Result.FAILURE, grid.resultingCondition(position));
                }
            }
        }
    }

    @Test
    void resultingCondition_outGrid() {
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, height);
            assertEquals(Result.FAILURE, grid.resultingCondition(position));
        }
        for (int w = 0; w < width; w++) {
            position = new GridPosition(w, -1);
            assertEquals(Result.FAILURE, grid.resultingCondition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(width, h);
            assertEquals(Result.FAILURE, grid.resultingCondition(position));
        }
        for (int h = 0; h < width; h++) {
            position = new GridPosition(-1, h);
            assertEquals(Result.FAILURE, grid.resultingCondition(position));
        }
    }
}