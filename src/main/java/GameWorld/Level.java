package GameWorld;

import GameWorld.Painter.LevelPainter;
import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldAPI.History.*;
import Images.ImageLibrary;
import RobotCollection.Robot.Robot;

import java.awt.Graphics;
import java.time.LocalDateTime;

/**
 * A class for levels
 *
 * @invar A level may not have an invalid robot at any moment.
 *        | !isInvalidRobot(robot)
 * @invar A level may not have an invalid grid at any moment.
 *        | !isInvalidGrid(grid)
 *
 * @author Alpha-team
 */
public class Level implements GameWorld {

    /**
     * Variable referring to the robot in this level.
     */
    private Robot robot;
    /**
     * Variable referring to the grid in this level.
     */
    private Grid grid;
    /**
     * Variable referring to the level painter of this level.
     */
    private final LevelPainter levelPainter = new LevelPainter();


    /**
     * TODO library + level painter creation
     * Initialise a new level with given robot and grid.
     *
     * @param robot the robot for this level.
     * @param grid The grid for this level.
     *
     * @post The robot of this level is set to the given robot, if that robot
     *       is valid in this level.
     * @post The grid of this level is set to the given grid, if that grid is
     *       valid in this level.
     *
     * @throws IllegalArgumentException
     *         If the given robot is invalid.
     * @throws IllegalArgumentException
     *         If the given grid is invalid.
     * @throws IllegalStateException
     *         If the position of the given robot is not walkable in the given grid.
     */
    public Level(Robot robot, Grid grid) throws IllegalArgumentException, IllegalStateException {
        if (isInvalidRobot(robot)) {
            throw new IllegalArgumentException("The given robot is not valid!");
        }
        if (isInvalidGrid(grid)) {
            throw new IllegalArgumentException("The given grid is not valid!");
        }
        if (!grid.isWalkablePosition(robot.getGridPosition())) {
            throw new IllegalStateException("The given robot can't stand on the given grid!");
        }
        this.robot = robot;
        this.grid = grid;
    }

    /**
     * Checks whether or not the given robot is invalid or not.
     *
     * @param robot The robot to check.
     *
     * @return True if and only if the given robot is not effective.
     */
    public static boolean isInvalidRobot(Robot robot) {
        return robot == null;
    }

    /**
     * Checks whether or not the given grid is invalid or not.
     *
     * @param grid The grid to check.
     *
     * @return True if and only if the given grid is not effective.
     */
    public static boolean isInvalidGrid(Grid grid) {
        return grid == null;
    }

    /**
     * Get the robot of this level
     *
     * @return The robot of this level.
     *
     * @throws IllegalStateException
     *         If no action should be done anymore, thus assuring that the robot can't be changed anymore.
     */
    public Robot getRobot() throws IllegalStateException {
        if (mayNotDoAction()) {
            throw new IllegalStateException("No action may be done and the robot shouldn't be changed anymore!");
        }
        return robot;
    }

    /**
     * Check if the robot has a wall in front of him.
     *
     * @return True if and only if the cell in the grid in front of the robot has
     *         the cell type wall, or if no cell is in front of the robot. False
     *         in all other cases.
     */
    public boolean robotHasWallInFront() {
        try {
            return grid.getCellAt(robot.getPositionForward()).getCellType() == CellType.WALL;
        }
        catch (IndexOutOfBoundsException ignore) {
            return true;
        }
    }

    /**
     * Check whether or not an action may be done.
     *
     * @return True if and only if the resulting condition of the robot
     *         position in the grid is not the failure or end result.
     */
    public boolean mayNotDoAction() {
        Result result = grid.resultingCondition(robot.getGridPosition());
        return result == Result.FAILURE || result == Result.END;
    }

    /**
     * Execute the given action with this level.
     *
     * @param action The action to execute.
     *
     * @effect The given action is executed with this level.
     *
     * @return The last result after the action has been executed.
     *
     * @throws IllegalStateException
     *         If the current result of the grid may not be executed anymore.
     */
    @Override
    public Result executeAction(Action action) throws IllegalStateException {
        if (mayNotDoAction()) {
            throw new IllegalStateException("No more actions should be executed!");
        }
        action.execute(this);
        return grid.resultingCondition(robot.getGridPosition());
    }

    /**
     * Evaluate the given predicate with this level.
     *
     * @param predicate The predicate to evaluate.
     *
     * @return The evaluation of the predicate using this level.
     */
    @Override
    public boolean evaluatePredicate(Predicate predicate) {
        return predicate.evaluate(this);
    }

    /**
     * Create a new snapshot of this level.
     *
     * @return A new snapshot based on this level.
     */
    @Override
    public Snapshot createSnapshot() {
        return new LevelSnapshot();
    }

    /**
     * Load the given snapshot.
     *
     * @param snapshot The snapshot to load.
     *
     * @post The grid of this level is set to the grid in the snapshot.
     * @post The robot of this level is set to the robot in the snapshot.
     *
     * @throws IllegalArgumentException
     *         If the given snapshot doesn't have a valid robot.
     * @throws IllegalArgumentException
     *         If the given snapshot doesn't have a valid grid.
     */
    @Override
    public void loadSnapshot(Snapshot snapshot) throws IllegalArgumentException {
        LevelSnapshot memento = (LevelSnapshot) snapshot;
        if (isInvalidRobot(memento.mementoRobot)) {
            throw new IllegalArgumentException("The given snapshot doesn't have a valid robot!");
        }
        if (isInvalidGrid(memento.mementoGrid)) {
            throw new IllegalArgumentException("The given snapshot doesn't have a valid robot!");
        }
        this.grid = memento.mementoGrid;
        this.robot = memento.mementoRobot;
    }

    /**
     * Paint this level.
     *
     * @param g The graphics to paint this level with.
     * @param library The image library containing the drawn images.
     *
     * @effect Paints this level, grid and robot using the level painter.
     */
    @Override
    public void paint(Graphics g, ImageLibrary library) {
        levelPainter.paint(g, grid, robot, library);
    }

    /**
     * A private class for snapshots of a level.
     */
    private class LevelSnapshot implements Snapshot {

        /**
         * Variable referring to the robot to remember.
         */
        private final Robot mementoRobot = robot.copy();
        /**
         * Variable referring to the grid to remember.
         */
        private final Grid mementoGrid = grid.copy();
        /**
         * Variable referring to the creation time of this snapshot.
         */
        private final LocalDateTime creationTime = LocalDateTime.now();

        /**
         * Get the name of this snapshot.
         *
         * @return First the robot of this snapshot with it's position and direction, followed
         *         by the grid and then the last result.
         */
        @Override
        public String getName() {
            return "Robot: " + mementoRobot.getGridPosition() + " " + mementoRobot.getDirection() + " - Grid: " + mementoGrid;
        }

        /**
         * Get the date of this snapshot.
         *
         * @return The creation time of this snapshot.
         */
        @Override
        public LocalDateTime getSnapshotDate() {
            return creationTime;
        }
    }
}
