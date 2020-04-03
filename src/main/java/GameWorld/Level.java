package GameWorld;

import GameWorld.History.GenericHistory;
import GameWorld.History.HistoryTracked;
import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldAPI.History.*;
import RobotCollection.Robot.Robot;

import java.awt.Graphics;
import java.time.LocalDateTime;

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

    private Result lastResult = Result.SUCCES;

    /**
     * Initialise a new level with given robot and direction,
     * as well as the cells for the grid of the level.
     *
     * @param robot the robot for this level
     * @param gridCells The cells for the grid of this level.
     *
     * @post The robot of this level is set to a new robot with given
     *       position and direction.
     * @post The grid of this level is set to a new grid with the
     *       given cells.
     * @throws IllegalArgumentException
     *         When the given robot position is an invalid position in the cells.
     */
    public Level(Robot robot, Cell[][] gridCells) throws IllegalArgumentException {
        this.grid = new Grid(gridCells);
        if (!grid.isValidRobotPositionInCells(robot.getGridPosition())) {
            throw new IllegalArgumentException("The given robot position is invalid in the cells");
        }
        this.robot = robot;
        this.history = new GenericHistory(this);
        backup();
    }


    @Override
    public Result executeAction(Action action) {
        action.execute();
        lastResult = grid.resultingCondition(robot.getGridPosition());
        backup();
        return lastResult;
    }

    @Override
    public boolean evaluatePredicate(Predicate predicate) {
        return predicate.evaluate();
    }

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
        robot.backup();
        grid.backup();
    }

    @Override
    public void undo() {
        robot.undo();
        grid.undo();
        history.undo();
    }

    @Override
    public void redo() {
        robot.redo();
        grid.redo();
        history.undo();
    }

    @Override
    public void reset() {
        robot.reset();
        grid.reset();
        history.undo();
    }

    @Override
    public void paint(Graphics graphics) {

    }

    @Override
    public String toString() {
        return robot.toString() + "Result: " + lastResult;
    }

    public boolean robotHasWallInFront() {
        try {
            return grid.getCellAt(robot.getForwardPosition()).getCellType() == CellType.WALL;
        }
        catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    private class LevelSnapshot implements Snapshot {

        private final Robot mementoRobot;

        private final Grid mementoGrid;

        private final Result mementoResult;

        private final LocalDateTime creationTime;


        public LevelSnapshot() {
            this.mementoRobot = robot;
            this.mementoGrid = grid;
            this.mementoResult = lastResult;
            this.creationTime = LocalDateTime.now();
        }


        @Override
        public String getName() {
            return "Robot: " + robot + " Grid: " + grid;
        }

        @Override
        public LocalDateTime getSnapshotDate() {
            return creationTime;
        }
    }
}
