package RobotCollection.Utility;

/**
 * A class for positions with x,y coordinates
 *
 * @author Alpha-team
 */
public class GridPosition {

    /**
     * Variable referring to the coordinates in the gird.
     */
    private final int x, y;

    /**
     * Initialise a new position with given x and y coordinates.
     *
     * @param x The x-coordinate for this position.
     * @param y The y-coordinate for this position.
     *
     * @post The x coordinate of this position is set to the given x.
     * @post The y coordinate of this position is set to the given y.
     */
    public GridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    public GridPosition(GridPosition gridPosition) {
//        this.x = gridPosition.x;
//        this.y = gridPosition.y;
//    }

    /**
     * Get the x-coordinate of this position.
     *
     * @return The x-coordinate of this position.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of this position.
     *
     * @return The y-coordinate of this position.
     */
    public int getY() {
        return  y;
    }


    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
