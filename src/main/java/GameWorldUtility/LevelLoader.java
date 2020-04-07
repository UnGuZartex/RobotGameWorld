package GameWorldUtility;

import GameWorld.Cell;
import GameWorld.CellType;
import GameWorld.Level;
import GameWorldAPI.GameWorld.GameWorld;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;

/**
 * A class to load levels.
 *
 * @author Alpha-team
 */
public class LevelLoader {

//    private Cell[][] level = new Cell[][]{new Cell[]{new Cell(CellType.BLANK), new Cell(CellType.BLANK), new Cell(CellType.BLANK)},
//            new Cell[]{new Cell(CellType.BLANK), new Cell(CellType.BLANK), new Cell(CellType.BLANK)},
//                    new Cell[]{new Cell(CellType.BLANK), new Cell(CellType.BLANK), new Cell(CellType.BLANK)},
//                            new Cell[]{new Cell(CellType.BLANK), new Cell(CellType.BLANK), new Cell(CellType.GOAL)}};

    private Cell[][] cells = new Cell[][]{new Cell[]{new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)},
            new Cell[]{new Cell(CellType.WALL), new Cell(CellType.BLANK), new Cell(CellType.GOAL)},
            new Cell[]{new Cell(CellType.WALL), new Cell(CellType.WALL), new Cell(CellType.WALL)}};

    /**
     * Load a level which is hardcoded in this level loader. This level is
     * set in the game state.
     */

    public Level loadLevel() {
        Robot robot = new Robot(new GridPosition(1,1), Direction.LEFT);
        return new Level(robot, cells);
    }
}
