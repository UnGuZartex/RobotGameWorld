package GameWorld;

import GameWorld.History.GenericHistory;
import GameWorld.History.HistoryTracked;
import GameWorld.Painter.LevelPainter;
import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldAPI.History.*;
import RobotCollection.Robot.Robot;

import java.awt.Graphics;
import java.time.LocalDateTime;

/**
 * A class for levels
 *
 * @invar A level must have valid robot and grid at all time.
 *        | isValidRobotGrid(robot, grid)
 *
 * @author Alpha-team
 */
public class Level implements GameWorld, HistoryTracked {

    /**
     * Variable referring to the robot in this level.
     */
    private Robot robot;
    /**
     * Variable referring to the grid in this level.
     */
    private Grid grid;

    private final GenericHistory history = new GenericHistory(this);
    private LevelPainter levelPainter;

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
        if (!isValidRobot(robot)) {
            throw new IllegalArgumentException("The given robot is not valid!");
        }
        if (!isValidGrid(grid)) {
            throw new IllegalArgumentException("The given grid is not valid!");
        }
        if (!grid.isWalkablePosition(robot.getGridPosition())) {
            throw new IllegalStateException("The given robot can't stand on the given grid!");
        }
        this.robot = robot;
        this.grid = grid;
    }

    /**
     * Checks whether or not the given robot is valid or not.
     *
     * @param robot The robot to check.
     *
     * @return True if and only if the given robot is effective.
     */
    public static boolean isValidRobot(Robot robot) {
        return robot != null;
    }

    /**
     * Checks whether or not the given grid is valid or not.
     *
     * @param grid The grid to check.
     *
     * @return True if and only if the given grid is effective.
     */
    public static boolean isValidGrid(Grid grid) {
        return grid != null;
    }

    /**
     * Get the robot of this level
     *
     * @return The robot of this level.
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Execute the given action with this level.
     *
     * @param action The action to execute.
     *
     * @effect A backup is made of this level.
     * @effect The given action is executed with this level.
     *
     * @return The last result after the action has been executed.
     */
    @Override
    public Result executeAction(Action action) {
        backup();
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
     * @effect The name and creation time of the snapshot to return are printed.
     *
     * @return A new snapshot based on this level.
     */
    @Override
    public Snapshot createSnapshot() {
        LevelSnapshot toReturn = new LevelSnapshot();
        System.out.println("Creating new Snapshot: " + toReturn.getName() + "@" + toReturn.getSnapshotDate());
        return toReturn;
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
        if (!isValidRobot(memento.mementoRobot)) {
            throw new IllegalArgumentException("The given snapshot doesn't have a valid robot!");
        }
        if (!isValidGrid(memento.mementoGrid)) {
            throw new IllegalArgumentException("The given snapshot doesn't have a valid robot!");
        }
        this.grid = memento.mementoGrid;
        this.robot = memento.mementoRobot;
    }

    /**
     * Make a backup of this level.
     *
     * @effect create a new snapshot and add it to the history.
     */
    @Override
    public void backup() {
        history.add(createSnapshot());
    }

    /**
     * Undo this level.
     *
     * @effect The history of this level is undone.
     */
    @Override
    public void undo() {
        history.undo();
    }

    /**
     * Redo this level.
     *
     * @effect The history of this level is redone.
     */
    @Override
    public void redo() {
        history.redo();
    }

    /**
     * Reset this level.
     *
     * @effect The history of this level is reset.
     */
    @Override
    public void reset() {
        history.reset();
    }

    /**
     * Paint this level.
     *
     * @param g The graphics to paint this level with.
     *
     * @effect Paints this level, grid and robot using the level painter.
     */
    @Override
    public void paint(Graphics g) {
        levelPainter.paint(g, grid, robot);
    }

    /**
     * Give a string representation of this level.
     *
     * @return First the robot of this level with it's position and direction, followed
     *         by the grid.
     */
    @Override
    public String toString() {
        return "Robot: " + robot.getGridPosition() + " " + robot.getDirection() + " - " +
                "Grid: " + grid;
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
            return "Robot: " + mementoRobot.getGridPosition() + " " + mementoRobot.getDirection() + " - " +
                    "Grid: " + mementoGrid;
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
