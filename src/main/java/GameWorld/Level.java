package GameWorld;

import GameWorld.History.GenericHistory;
import GameWorld.History.HistoryTracked;
import GameWorld.Painter.LevelPainter;
import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldAPI.History.*;
import GameWorldUtility.RobotAction;
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

    private final GenericHistory history;
    private Result lastResult = Result.SUCCESS;
    private LevelPainter levelPainter;

    /**
     * TODO library
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
     *         When the given robot and grid are invalid.
     */
    public Level(Robot robot, Grid grid) throws IllegalArgumentException {
        if (!isValidRobotGrid(robot, grid)) {
            throw new IllegalArgumentException("The given robot and grid are invalid!");
        }
        this.robot = robot;
        this.grid = grid;
        this.history = new GenericHistory(this);
    }

    /**
     * Check whether or not the given robot and grid are valid for this level.
     *
     * @param robot The robot to check.
     * @param grid The grid to check.
     *
     * @return True if and only if the given robot's position is walkable in
     *         the given grid.
     */
    public static boolean isValidRobotGrid(Robot robot, Grid grid) {
        return grid.isWalkablePosition(robot.getGridPosition());
    }

    // TODO execute action in level
    @Override
    public Result executeAction(Action action) {
        backup();
        RobotAction robotAction = (RobotAction) action;
        robotAction.setRobot(this.robot);
        action.execute();
        lastResult = grid.resultingCondition(robot.getGridPosition());
        return lastResult;
    }

    // TODO evaluate predicate in level
    @Override
    public boolean evaluatePredicate(Predicate predicate) {
        return predicate.evaluate();
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


    @Override
    public void loadSnapshot(Snapshot snapshot) {
        LevelSnapshot memento = (LevelSnapshot) snapshot;
        this.grid = memento.mementoGrid;
        this.robot = memento.mementoRobot;
        this.lastResult = memento.mementoResult;
    }

    @Override
    public void backup() {
        history.add(createSnapshot());
    }

    @Override
    public void undo() {
        history.undo();
    }

    @Override
    public void redo() {
        history.redo();
    }

    @Override
    public void reset() {
        history.reset();
    }

    @Override
    public void paint(Graphics g) {
        levelPainter.paint(g, grid, robot);
    }

    @Override
    public String toString() {
        return robot.getGridPosition() + " " + robot.getDirection() + "Result: " + lastResult;
    }

    public boolean robotHasWallInFront() {
        try {
            return grid.getCellAt(robot.getPositionForward()).getCellType() == CellType.WALL;
        }
        catch (IndexOutOfBoundsException ignore) {
            return true;
        }
    }

    private class LevelSnapshot implements Snapshot {

        private final Robot mementoRobot;

        private final Grid mementoGrid;

        private final Result mementoResult;

        private final LocalDateTime creationTime;

        public LevelSnapshot() {
            this.mementoRobot = robot.copy();
            this.mementoGrid = grid.copy();
            this.mementoResult = lastResult;
            this.creationTime = LocalDateTime.now();
        }

        @Override
        public String getName() {
            return "Robot: " + robot.getGridPosition() + robot.getDirection() + " Grid: " + grid;
        }

        @Override
        public LocalDateTime getSnapshotDate() {
            return creationTime;
        }
    }
}
