package GameWorldUtility;

import GameWorld.Cell;
import GameWorld.CellType;
import GameWorld.Grid;
import GameWorld.Level;
import RobotCollection.Robot.Robot;
import RobotCollection.Robot.Direction;
import RobotCollection.Utility.GridPosition;

/**
 * A class to load levels.
 *
 * @author Alpha-team
 */
public class LevelLoader {

    /**
     * Variable referring to the default grid. This is a 3x3 grid which looks like
     *
     *     WALL  WALL   WALL
     *     WALL  BLANK  WALL
     *     WALL  GOAL   WALL
     */
    private static final Grid defaultGrid = new Grid(new Cell[][] {
            {new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)},
            {new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.GOAL)},
            {new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)}}
    );

    /**
     * Variable referring to the default robot. This is a robot starting on position
     * (1,1) which is directed to the left.
     */
    private static final Robot defaultRobot =
            new Robot(new GridPosition(1,1), Direction.LEFT);

    /**
     * Load a level which is hardcoded in this level loader with the given robot.
     *
     * @return A new level with a copy of the default robot and grid.
     */
    public Level loadDefaultLevel() {
        return new Level(defaultRobot.copy(), defaultGrid.copy());
    }

    /**
     * Get the default robot.
     *
     * @return A copy of the default robot.
     */
    public Robot getDefaultRobot() {
        return defaultRobot.copy();
    }

    /**
     * Get the default grid.
     *
     * @return A copy of the default grid.
     */
    public Grid getDefaultGrid() {
        return defaultGrid.copy();
    }
}