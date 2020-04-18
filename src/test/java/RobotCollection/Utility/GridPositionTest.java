package RobotCollection.Utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class GridPositionTest {

    Random random;
    int x, y;
    static final int X_MIN = -20, X_MAX = 20, Y_MIN = -20, Y_MAX = 20;
    GridPosition gridPosition;

    @BeforeEach
    void setUp() {
        random = new Random();
        x = X_MIN + random.nextInt(X_MAX - X_MIN + 1);
        y = Y_MIN + random.nextInt(Y_MAX - Y_MIN + 1);
        gridPosition = new GridPosition(x, y);
    }

    @AfterEach
    void tearDown() {
        random = null;
        gridPosition = null;
    }

    @Test
    void getX() {
        assertEquals(x, gridPosition.getX());
    }

    @Test
    void getY() {
        assertEquals(y, gridPosition.getY());
    }

    @Test
    void copy() {
        GridPosition copy = gridPosition.copy();
        assertNotEquals(gridPosition, copy);
        assertEquals(gridPosition.getX(), copy.getX());
        assertEquals(gridPosition.getY(), copy.getY());
    }

    @Test
    void toStringTest() {
        assertEquals("(" + x + ", " + y + ")", gridPosition.toString());
    }
}