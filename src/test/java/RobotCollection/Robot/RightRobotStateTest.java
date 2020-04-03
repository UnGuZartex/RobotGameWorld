package RobotCollection.Robot;

import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RightRobotStateTest {

    RobotState state;
    Random random;
    int x, y;
    static final int MIN_X = -20, MAX_X = 20, MIN_Y = -20, MAX_Y = 20;
    GridPosition position;

    @BeforeEach
    void setUp() {
        state = new RightRobotState();
        random = new Random();
        x = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        y = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        position = new GridPosition(x, y);
    }

    @AfterEach
    void tearDown() {
        state = null;
        random = null;
        position = null;
    }

    @Test
    void getName() {
        assertEquals("RIGHT", state.getName());
    }

    @Test
    void getPositionForward() {
        assertEquals(x + 1, state.getPositionForward(position).getX());
        assertEquals(y, state.getPositionForward(position).getY());
    }

    @Test
    void getLeftState() {
        assertTrue(state.getLeftState() instanceof UpRobotState);
    }

    @Test
    void getRightState() {
        assertTrue(state.getRightState() instanceof DownRobotState);
    }
}