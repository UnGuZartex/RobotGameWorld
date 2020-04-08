package GameWorldUtility;

import RobotCollection.Robot.*;
import RobotCollection.Utility.Direction;
import RobotCollection.Utility.GridPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TurnRightActionTest {

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

        robotUp = new Robot(gridPositionUp, Direction.UP);
        robotDown = new Robot(gridPositionDown, Direction.DOWN);
        robotLeft = new Robot(gridPositionLeft, Direction.LEFT);
        robotRight = new Robot(gridPositionRight, Direction.RIGHT);

        actionUp = new TurnRightAction();
        actionUp.setRobot(robotUp);
        actionDown = new TurnRightAction();
        actionDown.setRobot(robotDown);
        actionLeft = new TurnRightAction();
        actionLeft.setRobot(robotLeft);
        actionRight = new TurnRightAction();
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
    void setRobot() {
        assertEquals(robotLeft, actionLeft.robot);
        actionLeft.setRobot(robotDown);
        assertEquals(robotDown, actionLeft.robot);
        actionLeft.setRobot(robotUp);
        assertEquals(robotUp, actionLeft.robot);
        actionLeft.setRobot(robotRight);
        assertEquals(robotRight, actionLeft.robot);
    }

    @Test
    void getName() {
        assertEquals("Turn Right", actionUp.getName());
        assertEquals("Turn Right", actionDown.getName());
        assertEquals("Turn Right", actionLeft.getName());
        assertEquals("Turn Right", actionRight.getName());
    }

    @Test
    void execute_up() {
        assertEquals("UP", actionUp.robot.getDirection());
        actionUp.execute();
        assertEquals("RIGHT", actionUp.robot.getDirection());
        actionUp.execute();
        assertEquals("DOWN", actionUp.robot.getDirection());
        actionUp.execute();
        assertEquals("LEFT", actionUp.robot.getDirection());
        actionUp.execute();
        assertEquals("UP", actionUp.robot.getDirection());
    }

    @Test
    void execute_down() {
        assertEquals("DOWN", actionDown.robot.getDirection());
        actionDown.execute();
        assertEquals("LEFT", actionDown.robot.getDirection());
        actionDown.execute();
        assertEquals("UP", actionDown.robot.getDirection());
        actionDown.execute();
        assertEquals("RIGHT", actionDown.robot.getDirection());
        actionDown.execute();
        assertEquals("DOWN", actionDown.robot.getDirection());
    }

    @Test
    void execute_left() {
        assertEquals("LEFT", actionLeft.robot.getDirection());
        actionLeft.execute();
        assertEquals("UP", actionLeft.robot.getDirection());
        actionLeft.execute();
        assertEquals("RIGHT", actionLeft.robot.getDirection());
        actionLeft.execute();
        assertEquals("DOWN", actionLeft.robot.getDirection());
        actionLeft.execute();
        assertEquals("LEFT", actionLeft.robot.getDirection());
    }

    @Test
    void execute_right() {
        assertEquals("RIGHT", actionRight.robot.getDirection());
        actionRight.execute();
        assertEquals("DOWN", actionRight.robot.getDirection());
        actionRight.execute();
        assertEquals("LEFT", actionRight.robot.getDirection());
        actionRight.execute();
        assertEquals("UP", actionRight.robot.getDirection());
        actionRight.execute();
        assertEquals("RIGHT", actionRight.robot.getDirection());
    }
}