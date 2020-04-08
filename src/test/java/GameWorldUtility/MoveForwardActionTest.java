package GameWorldUtility;

import RobotCollection.Robot.*;
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
    static final int MIN_X = 1, MAX_X = 20, MIN_Y = 1, MAX_Y = 20;
    RobotAction actionUp, actionDown, actionRight, actionLeft;

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

        robotUp = new Robot(gridPositionUp, new UpRobotState());
        robotDown = new Robot(gridPositionDown, new DownRobotState());
        robotLeft = new Robot(gridPositionLeft, new LeftRobotState());
        robotRight = new Robot(gridPositionRight, new RightRobotState());

        actionUp = new MoveForwardAction();
        actionUp.setRobot(robotUp);
        actionDown = new MoveForwardAction();
        actionDown.setRobot(robotDown);
        actionLeft = new MoveForwardAction();
        actionLeft.setRobot(robotLeft);
        actionRight = new MoveForwardAction();
        actionRight.setRobot(robotRight);
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

        actionUp = null;
        actionDown = null;
        actionLeft = null;
        actionRight = null;
    }

    @Test
    void getRobot() {
        assertEquals(robotUp, actionUp.getRobot());
        assertEquals(robotRight, actionRight.getRobot());
        assertEquals(robotLeft, actionLeft.getRobot());
        assertEquals(robotRight, actionRight.getRobot());
    }

    @Test
    void setRobot() {
        assertEquals(robotDown, actionDown.getRobot());
        actionDown.setRobot(robotLeft);
        assertEquals(robotLeft, actionDown.getRobot());
        actionDown.setRobot(robotUp);
        assertEquals(robotUp, actionDown.getRobot());
        actionDown.setRobot(robotRight);
        assertEquals(robotRight, actionDown.getRobot());
    }

    @Test
    void getName() {
        assertEquals("Move Forward", actionUp.getName());
        assertEquals("Move Forward", actionDown.getName());
        assertEquals("Move Forward", actionLeft.getName());
        assertEquals("Move Forward", actionRight.getName());
    }

    @Test
    void execute_up() {
        assertEquals(xUp, actionUp.getRobot().getGridPosition().getX());
        assertEquals(yUp, actionUp.getRobot().getGridPosition().getY());
        actionUp.execute();
        assertEquals(xUp, actionUp.getRobot().getGridPosition().getX());
        assertEquals(yUp - 1, actionUp.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_down() {
        assertEquals(xDown, actionDown.getRobot().getGridPosition().getX());
        assertEquals(yDown, actionDown.getRobot().getGridPosition().getY());
        actionDown.execute();
        assertEquals(xDown, actionDown.getRobot().getGridPosition().getX());
        assertEquals(yDown + 1, actionDown.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_left() {
        assertEquals(xLeft, actionLeft.getRobot().getGridPosition().getX());
        assertEquals(yLeft, actionLeft.getRobot().getGridPosition().getY());
        actionLeft.execute();
        assertEquals(xLeft - 1, actionLeft.getRobot().getGridPosition().getX());
        assertEquals(yLeft, actionLeft.getRobot().getGridPosition().getY());
    }

    @Test
    void execute_right() {
        assertEquals(xRight, actionRight.getRobot().getGridPosition().getX());
        assertEquals(yRight, actionRight.getRobot().getGridPosition().getY());
        actionRight.execute();
        assertEquals(xRight + 1, actionRight.getRobot().getGridPosition().getX());
        assertEquals(yRight, actionRight.getRobot().getGridPosition().getY());
    }
}