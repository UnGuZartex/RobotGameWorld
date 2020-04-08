package RobotCollection.Utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    Direction up, down, left, right;
    Random random;
    int x, y;
    static final int X_MIN = -20, X_MAX = 20, Y_MIN = -20, Y_MAX = 20;
    GridPosition gridPosition;

    @BeforeEach
    void setUp() {
        up = Direction.UP;
        down = Direction.DOWN;
        left = Direction.LEFT;
        right = Direction.RIGHT;

        random = new Random();
        x = X_MIN + random.nextInt(X_MAX - X_MIN + 1);
        y = Y_MIN + random.nextInt(Y_MAX - Y_MIN + 1);
        gridPosition = new GridPosition(x, y);
    }

    @AfterEach
    void tearDown() {
        up = null;
        down = null;
        left = null;
        right = null;
        random = null;
        gridPosition = null;
    }

    @Test
    void turnLeft() {
        assertEquals(Direction.LEFT, up.turnLeft());
        assertEquals(Direction.RIGHT, down.turnLeft());
        assertEquals(Direction.DOWN, left.turnLeft());
        assertEquals(Direction.UP, right.turnLeft());
    }

    @Test
    void turnRight() {
        assertEquals(Direction.RIGHT, up.turnRight());
        assertEquals(Direction.LEFT, down.turnRight());
        assertEquals(Direction.UP, left.turnRight());
        assertEquals(Direction.DOWN, right.turnRight());
    }

    @Test
    void moveForward() {
        assertEquals(x, up.getPositionForward(gridPosition).getX());
        assertEquals(y - 1, up.getPositionForward(gridPosition).getY());
        assertEquals(x, down.getPositionForward(gridPosition).getX());
        assertEquals(y + 1, down.getPositionForward(gridPosition).getY());
        assertEquals(x - 1, left.getPositionForward(gridPosition).getX());
        assertEquals(y, left.getPositionForward(gridPosition).getY());
        assertEquals(x + 1, right.getPositionForward(gridPosition).getX());
        assertEquals(y, right.getPositionForward(gridPosition).getY());
    }
}