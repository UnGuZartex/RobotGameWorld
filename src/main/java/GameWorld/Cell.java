package GameWorld;

/**
 * A class for cells, which have a CellType.
 *
 * @author Alpha-team
 */
public class Cell {

    /**
     * Variable referring to the CellType of this cell.
     */
    private CellType cellType;

    /**
     * Initialise a new Cell with given CellType.
     *
     * @param cellType The CellType for this Cell.
     *
     * @post The cell type of this cell is set to the given type.
     */
    public Cell(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Get the CellType of this Cell.
     *
     * @return The CellType of this Cell.
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * Get a copy of this cell.
     *
     * @return A new cell with equal cell type.
     */
    public Cell copy() {
        return new Cell(cellType);
    }
}
