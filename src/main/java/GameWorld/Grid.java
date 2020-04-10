package GameWorld;

import GameWorldAPI.GameWorld.Result;
import RobotCollection.Utility.GridPosition;

/**
 * A class to keep track of the grid of the game. This is
 * a matrix of cells.
 *
 * @invar A grid must have valid cells
 *        | areValidCells(cells)
 *
 * @author Alpha-team
 */
public class Grid {

    /**
     * Variable referring to the cells in this grid.
     */
    private Cell[][] cells;

    /**
     * Initialise a new grid with given cells.
     *
     * @param cells The cells for this Grid.
     *
     * @post The cells of this grid are set to the given cells only
     *       if these are valid.
     *
     * @throws IllegalArgumentException
     *         If the given cells are not valid for a grid.
     */
    public Grid(Cell[][] cells) throws IllegalArgumentException {
        if (areValidCells(cells)) {
            this.cells = cells;
        } else {
            throw new IllegalArgumentException("The given cells are invalid for a grid!");
        }
    }

    /**
     * Get a copy of this grid.
     *
     * @return A new grid with cells equal to this grid but copied.
     */
    public Grid copy() {
        Cell[][] copy = new Cell[cells.length][cells[0].length];
        for (int x = 0; x < copy.length; x++) {
            for (int y = 0; y < copy[x].length; y++) {
                copy[x][y] = cells[x][y].copy();
            }
        }
        return new Grid(copy);
    }

    /**
     * Check whether or not the given cells are valid for a grid.
     *
     * @param cells The cells to check.
     *
     * @return True if and only if the given cells are effective and both the height
     *         and width are greater than 0, false in all other cases.
     */
    public static boolean areValidCells(Cell[][] cells) {
        return cells != null && cells.length > 0 && cells[0].length > 0;
    }

    /**
     * Get the cell in this grid at the given indices.
     *
     * @param position the Position to get the cell of
     *
     * @return The cell in this grid at position [x,y] coordinate.
     *
     * @throws IndexOutOfBoundsException
     *         If the given position is not inside of this grid.
     */
    public Cell getCellAt(GridPosition position) throws IndexOutOfBoundsException {
        return cells[position.getX()][position.getY()];
    }

    /**
     * Get the result based on the given position.
     *
     * @param position The position to base the result on.
     *
     * @return If the given position is not walkable in this grid, then a failure
     *         result is returned. If the given position is winning, then is an
     *         ending result returned. In all other cases is a success result returned.
     */
    public Result resultingCondition(GridPosition position) {
        if (!isWalkablePosition(position)) {
            return Result.FAILURE;
        }
        if (isWinningPosition(position)) {
            return Result.END;
        }
        return Result.SUCCESS;
    }

    /**
     * Check whether or not given robot coordinates and grid are valid.
     *
     * @param position the position of the Robot
     *
     * @return True if and only if the given position is of a cell in this grid
     *         with a cell type on which can be walked. Otherwise false is returned.
     */
    public boolean isWalkablePosition(GridPosition position) {
        try {
            return getCellAt(position).getCellType().canWalkOn();
        } catch (IndexOutOfBoundsException ignore) {
            return false;
        }
    }

    /**
     * Checks whether or not the level has been won.
     *
     * @return True if and only if the given position is of a cell in this grid
     *         with a cell type which is winning. Otherwise false is returned.
     */
    public boolean isWinningPosition(GridPosition position) {
        try {
            return getCellAt(position).getCellType().isWin();
        } catch (IndexOutOfBoundsException ignore) {
            return false;
        }
    }
}