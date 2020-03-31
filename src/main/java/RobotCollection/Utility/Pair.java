package RobotCollection.Utility;

/**
 * A class for positions with x,y coordinates
 *
 * @author Alpha-team
 */
public class Pair {

    /**
     * Variable referring to the x-coordinate of this position.
     */
    private final int x;
    /**
     * Variable referring to the y-coordinate of this position.
     */
    private final int y;

    /**
     * Initialise a new position with given x and y coordinates.
     *
     * @param x The x-coordinate for this position.
     * @param y The y-coordinate for this position.
     *
     * @post The x coordinate of this position is set to the given x.
     * @post The y coordinate of this position is set to the given y.
     */
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair gridPosition) {
        this.x = gridPosition.x;
        this.y = gridPosition.y;
    }

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
}
