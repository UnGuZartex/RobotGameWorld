package GameWorld;

import GameWorldAPI.GameWorld.*;
import GameWorldAPI.GameWorldType.*;
import GameWorldAPI.History.*;
import RobotCollection.Robot.Robot;

import java.awt.Graphics;

public class Level implements GameWorld {
    /**
     * Variable referring to the robot in this level.
     */
    private final Robot robot;
    /**
     * Variable referring to the grid in this level.
     */
    private final Grid grid;

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
    }


    @Override
    public Result executeAction(Action action) {
        action.execute();
        return grid.resultingCondition(robot.getGridPosition());

    }

    @Override
    public boolean evaluatePredicate(Predicate predicate) {
        return predicate.evaluate();
    }

    @Override
    public Snapshot createSnapshot() {
        return null;
    }

    @Override
    public void loadSnapshot(Snapshot snapshot) {

    }

    @Override
    public void paint(Graphics graphics) {

    }


    public boolean robotHasWallInFront() {
        try {
            return grid.getCellAt(robot.getPositionForward()).getCellType() == CellType.WALL;
        }
        catch (IndexOutOfBoundsException e) {
            return true;
        }
    }
}
