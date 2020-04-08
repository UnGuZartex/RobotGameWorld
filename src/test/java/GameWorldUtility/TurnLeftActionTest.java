package GameWorldUtility;

import RobotCollection.Robot.*;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TurnLeftActionTest {

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

        actionUp = new TurnLeftAction();
        actionUp.setRobot(robotUp);
        actionDown = new TurnLeftAction();
        actionDown.setRobot(robotDown);
        actionLeft = new TurnLeftAction();
        actionLeft.setRobot(robotLeft);
        actionRight = new TurnLeftAction();
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
        assertEquals(robotUp, actionUp.getRobot());
        actionUp.setRobot(robotDown);
        assertEquals(robotDown, actionUp.getRobot());
        actionUp.setRobot(robotLeft);
        assertEquals(robotLeft, actionUp.getRobot());
        actionUp.setRobot(robotRight);
        assertEquals(robotRight, actionUp.getRobot());
    }

    @Test
    void getName() {
        assertEquals("Turn Left", actionUp.getName());
        assertEquals("Turn Left", actionDown.getName());
        assertEquals("Turn Left", actionLeft.getName());
        assertEquals("Turn Left", actionRight.getName());
    }

    @Test
    void execute_up() {
        assertEquals("UP", actionUp.getRobot().getDirection());
        actionUp.execute();
        assertEquals("LEFT", actionUp.getRobot().getDirection());
        actionUp.execute();
        assertEquals("DOWN", actionUp.getRobot().getDirection());
        actionUp.execute();
        assertEquals("RIGHT", actionUp.getRobot().getDirection());
        actionUp.execute();
        assertEquals("UP", actionUp.getRobot().getDirection());
    }

    @Test
    void execute_down() {
        assertEquals("DOWN", actionDown.getRobot().getDirection());
        actionDown.execute();
        assertEquals("RIGHT", actionDown.getRobot().getDirection());
        actionDown.execute();
        assertEquals("UP", actionDown.getRobot().getDirection());
        actionDown.execute();
        assertEquals("LEFT", actionDown.getRobot().getDirection());
        actionDown.execute();
        assertEquals("DOWN", actionDown.getRobot().getDirection());
    }

    @Test
    void execute_left() {
        assertEquals("LEFT", actionLeft.getRobot().getDirection());
        actionLeft.execute();
        assertEquals("DOWN", actionLeft.getRobot().getDirection());
        actionLeft.execute();
        assertEquals("RIGHT", actionLeft.getRobot().getDirection());
        actionLeft.execute();
        assertEquals("UP", actionLeft.getRobot().getDirection());
        actionLeft.execute();
        assertEquals("LEFT", actionLeft.getRobot().getDirection());
    }

    @Test
    void execute_right() {
        assertEquals("RIGHT", actionRight.getRobot().getDirection());
        actionRight.execute();
        assertEquals("UP", actionRight.getRobot().getDirection());
        actionRight.execute();
        assertEquals("LEFT", actionRight.getRobot().getDirection());
        actionRight.execute();
        assertEquals("DOWN", actionRight.getRobot().getDirection());
        actionRight.execute();
        assertEquals("RIGHT", actionRight.getRobot().getDirection());
    }
}