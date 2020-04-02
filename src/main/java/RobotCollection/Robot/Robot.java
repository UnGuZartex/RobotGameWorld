package RobotCollection.Robot;

import RobotCollection.Utility.Direction;
import RobotCollection.Utility.Pair;

/**
 * A class representing a robot. This has coordinates and a direction.
 *
 * @invar The robot's coordinates must be at all time valid.
 *        | isValidCoordinates(getX(), getY())
 *
 * @author Alpha-team
 */
public class Robot {

    /** 
     * Variable referring to the gridposition of this robot.
     */
    private Pair gridPosition;


    /**
     * Variable referring to the direction of this robot.
     */
    private Direction direction;


    /**
     * Initialise a new robot with given x and y coordinates, as
     * well as a direction.
     *
     * @param x The initial x coordinate for this robot.
     * @param y The initial x coordinate for this robot.
     * @param direction The initial direction for this robot.
     *
     * @pre The x and y coordinates must be greater than or
     *      equal to 0.
     *
     * @post The x coordinate of this robot is set to the given x coordinate.
     * @post The y coordinate of this robot is set to the given y coordinate.
     * @post The direction of this robot is set to the given direction.
     * @post The original x coordinate of this robot is set to the given x coordinate.
     * @post The original y coordinate of this robot is set to the given y coordinate.
     * @post The original direction of this robot is set to the given direction.
     */
    public Robot(int x, int y, Direction direction) {
        gridPosition = new Pair(x,y);
        this.direction = direction;
    }

    /**
     * Get the direction of this robot.
     *
     * @return The direction of this robot.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get the gridPosition of the robot
     * @return a copy of the gridposition of the robot
     */
    public Pair getGridPosition() {
        return new Pair(gridPosition);
    }

    /**
     * Checks whether or not the given coordinates are valid for a robot.
     *
     * @param x The x coordinate to check.
     * @param y The Y coordinate to check.
     *
     * @return True if and only if the given coordinates are both greater than 0.
     */
    public static boolean isValidCoordinates(int x, int y) {
        return x >= 0 && y >= 0;
    }

    /**
     * Update the position and the direction of this robot to the new values
     * @param newPosition new Position of the robot
     * @param newDirection new Direction of the robot
     *
     * @effect The position and the direction of the robot is changed
     */
    public void updatePositionAndDirection(Pair newPosition, Direction newDirection) {
        gridPosition = newPosition;
        direction = newDirection;
    }

    /**
     * Gets the position of the block in front of the robot
     * @return position in front of the robot
     */
    public Pair getForwardPosition() {
        return direction.moveForward(gridPosition);
    }

    /**
     * Move the robot forwards
     *
     * @effect the robots position is changed corresponding its direction
     */
    public void moveForward() {
        updatePositionAndDirection(getForwardPosition(), direction);
    }

    /**
     * Turn the robot to the left
     *
     * @effect the robots direction is changed to the left of the current direction
     */
    public void turnLeft() {
        Direction newDirection = direction.turnLeft();
        updatePositionAndDirection(gridPosition, newDirection);
    }

    /**
     * Turn the robot to the right
     *
     * @effect the robots direction is changed to the right of the current direction
     */
    public void turnRight() {
        Direction newDirection = direction.turnRight();
        updatePositionAndDirection(gridPosition, newDirection);
    }
}
