package RobotCollection.Robot;

import RobotCollection.Utility.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    int xUp, yUp, xDown, yDown, xLeft, yLeft, xRight, yRight;
    GridPosition gridPositionUp, gridPositionDown, gridPositionLeft, gridPositionRight;
    Robot robotUp, robotDown, robotLeft, robotRight;
    Random random;
    static final int MIN_X = 1, MAX_X = 20, MIN_Y = 1, MAX_Y = 20;

    @BeforeEach
    void setUp() {
        random = new Random();
        xUp = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yUp = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        xDown = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yDown = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        xLeft = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yLeft = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;
        xRight = random.nextInt(MAX_X + 1 - MIN_X) + MIN_X;
        yRight = random.nextInt(MAX_Y + 1 - MIN_Y) + MIN_Y;

        gridPositionUp = new GridPosition(xUp, yUp);
        gridPositionDown = new GridPosition(xDown, yDown);
        gridPositionLeft = new GridPosition(xLeft, yLeft);
        gridPositionRight = new GridPosition(xRight, yRight);

        robotUp = new Robot(gridPositionUp, Direction.UP);
        robotDown = new Robot(gridPositionDown, Direction.DOWN);
        robotLeft = new Robot(gridPositionLeft,Direction.LEFT);
        robotRight = new Robot(gridPositionRight, Direction.RIGHT);
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
    }

    @Test
    void RobotGridPositionRobotState_InvalidGridPosition() {
        assertFalse(Robot.isValidPosition(new GridPosition(-1,-1)));
        assertThrows(IllegalArgumentException.class, () -> { new Robot(new GridPosition(-1,-1), Direction.RIGHT); });
    }

    @Test
    void getGridPosition() {
        assertEquals(gridPositionUp, robotUp.getGridPosition());
        assertEquals(gridPositionUp, robotUp.getGridPosition());
        assertEquals(gridPositionUp, robotUp.getGridPosition());
        assertEquals(gridPositionUp, robotUp.getGridPosition());
    }

    @Test
    void getDirection() {
        assertEquals("UP", robotUp.getDirection());
        assertEquals("DOWN", robotDown.getDirection());
        assertEquals("LEFT", robotLeft.getDirection());
        assertEquals("RIGHT", robotRight.getDirection());
    }

    @Test
    void isValidPosition_invalid() {
        assertFalse(Robot.isValidPosition(new GridPosition(-1,1)));
        assertFalse(Robot.isValidPosition(new GridPosition(1,-1)));
        assertFalse(Robot.isValidPosition(new GridPosition(-1,-1)));
    }

    @Test
    void isValidPosition_valid() {
        assertTrue(Robot.isValidPosition(new GridPosition(0,1)));
        assertTrue(Robot.isValidPosition(new GridPosition(1,1)));
        assertTrue(Robot.isValidPosition(new GridPosition(1,0)));
        assertTrue(Robot.isValidPosition(new GridPosition(0,0)));
    }

    @Test
    void getPositionForward_up() {
        assertEquals(xUp, robotUp.getForwardPosition().getX());
        assertEquals(yUp - 1, robotUp.getForwardPosition().getY());
    }

    @Test
    void getPositionForward_down() {
        assertEquals(xDown, robotDown.getForwardPosition().getX());
        assertEquals(yDown + 1, robotDown.getForwardPosition().getY());
    }

    @Test
    void getPositionForward_left() {
        assertEquals(xLeft - 1, robotLeft.getForwardPosition().getX());
        assertEquals(yLeft, robotLeft.getForwardPosition().getY());
    }

    @Test
    void getPositionForward_right() {
        assertEquals(xRight + 1, robotRight.getForwardPosition().getX());
        assertEquals(yRight, robotRight.getForwardPosition().getY());
    }

    @Test
    void turnLeft_up() {
        assertEquals("UP", robotUp.getDirection());
        robotUp.turnLeft();
        assertEquals("LEFT", robotUp.getDirection());
    }

    @Test
    void turnLeft_down() {
        assertEquals("DOWN", robotDown.getDirection());
        robotDown.turnLeft();
        assertEquals("RIGHT", robotDown.getDirection());
    }

    @Test
    void turnLeft_left() {
        assertEquals("LEFT", robotLeft.getDirection());
        robotLeft.turnLeft();
        assertEquals("DOWN", robotLeft.getDirection());
    }

    @Test
    void turnLeft_right() {
        assertEquals("RIGHT", robotRight.getDirection());
        robotRight.turnLeft();
        assertEquals("UP", robotRight.getDirection());
    }

    @Test
    void turnRight_up() {
        assertEquals("UP", robotUp.getDirection());
        robotUp.turnRight();
        assertEquals("RIGHT", robotUp.getDirection());
    }

    @Test
    void turnRight_down() {
        assertEquals("DOWN", robotDown.getDirection());
        robotDown.turnRight();
        assertEquals("LEFT", robotDown.getDirection());
    }

    @Test
    void turnRight_left() {
        assertEquals("LEFT", robotLeft.getDirection());
        robotLeft.turnRight();
        assertEquals("UP", robotLeft.getDirection());
    }

    @Test
    void turnRight_right() {
        assertEquals("RIGHT", robotRight.getDirection());
        robotRight.turnRight();
        assertEquals("DOWN", robotRight.getDirection());
    }

    @Test
    void moveForward_canNotMoveForward() {
        Robot robot = new Robot(new GridPosition(0,5), Direction.LEFT);
        assertFalse(Robot.isValidPosition(robot.getForwardPosition()));
        assertThrows(IllegalStateException.class, robot::moveForward);
    }

    @Test
    void moveForward_up() {
        assertEquals(xUp, robotUp.getGridPosition().getX());
        assertEquals(yUp, robotUp.getGridPosition().getY());
        robotUp.moveForward();
        assertEquals(xUp, robotUp.getGridPosition().getX());
        assertEquals(yUp - 1, robotUp.getGridPosition().getY());
    }

    @Test
    void moveForward_down() {
        assertEquals(xDown, robotDown.getGridPosition().getX());
        assertEquals(yDown, robotDown.getGridPosition().getY());
        robotDown.moveForward();
        assertEquals(xDown, robotDown.getGridPosition().getX());
        assertEquals(yDown + 1, robotDown.getGridPosition().getY());
    }

    @Test
    void moveForward_left() {
        assertEquals(xLeft, robotLeft.getGridPosition().getX());
        assertEquals(yLeft, robotLeft.getGridPosition().getY());
        robotLeft.moveForward();
        assertEquals(xLeft - 1, robotLeft.getGridPosition().getX());
        assertEquals(yLeft, robotLeft.getGridPosition().getY());
    }

    @Test
    void moveForward_right() {
        assertEquals(xRight, robotRight.getGridPosition().getX());
        assertEquals(yRight, robotRight.getGridPosition().getY());
        robotRight.moveForward();
        assertEquals(xRight + 1, robotRight.getGridPosition().getX());
        assertEquals(yRight, robotRight.getGridPosition().getY());
    }
}