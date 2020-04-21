package GameWorld;

import GameWorld.Grid;
import Images.ImageLibrary;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;

import java.awt.*;

/**
 * A class for painting levels.
 *
 * @author Alpha-team
 */
public class LevelPainter {

    /**
     * Variable referring to the visual cell size of each cell in the drawn grid of the level.
     */
    private int cellSize;

    /**
     * Variable referring to the x-coordinate of the left corner of the drawn grid.
     */
    private int gridStartingPointX;

    /**
     * Variable referring to the y-coordinate of the left corner of the drawn grid.
     */
    private int gridStartingPointY;

    /**
     * Variable referring to the minimum amount of pixels around the grid allowing some space
     * between the drawn grid and its allowed bounds.
     */
    private static int minGridDelta = 20;

    /**
     * The current level is painted with its given grid and robot
     * using the graphics object to draw and the image library for its images.
     *
     * @param g The given graphics object.
     * @param library The given image library.
     * @param grid The given grid.
     * @param robot The given robot.
     *
     * @effect The grid properties are calculated.
     * @effect The grid is drawn.
     * @effect The robot is drawn.
     */
    public void paint(Graphics g, ImageLibrary library, Grid grid, Robot robot) {
        calculateGridProperties(g, grid.getWidth(), grid.getHeight());
        drawBackground(g, library);
        drawGrid(g, library, grid);
        drawRobot(g, library, robot);
    }

    /**
     * Calculate the variables used to draw the grid with a given graphics object,
     * grid width and grid height.
     *
     * @param g The given graphics object.
     * @param gridWidth The given grid width.
     * @param gridHeight The given grid height.
     *
     * @effect The visual cell size is calculated properly.
     * @effect The starting coordinates of the drawn grid are calculated properly.
     */
    private void calculateGridProperties(Graphics g, int gridWidth, int gridHeight) {
        Rectangle clipRect = g.getClipBounds();
        cellSize = (int) Math.min((clipRect.getWidth() - minGridDelta) / gridWidth, (clipRect.getHeight() - minGridDelta) / gridHeight);
        gridStartingPointX = (int) (clipRect.getX() + (clipRect.getWidth() - (cellSize * gridWidth)) / 2);
        gridStartingPointY = (int) (clipRect.getY() + (clipRect.getHeight() - (cellSize * gridHeight)) / 2);
    }

    /**
     * Draw the background of the robot game world.
     *
     * @param g The given graphics object.
     * @param library The given image library.
     *
     * @effect The game world background is drawn.
     */
    private void drawBackground(Graphics g, ImageLibrary library) {
        Rectangle clipRect = g.getClipBounds();
        g.drawImage(library.getImage("gameWorldBackground"), clipRect.x, clipRect.y,
                clipRect.width, clipRect.height, null);
    }

    /**
     * Draw the robot of the current level with a given graphics object, robot
     * and image library.
     *
     * @param g The given graphics object.
     * @param library The given image library.
     * @param grid The given grid.
     *
     * @effect Each cell in the grid is drawn at the right coordinates and image.
     */
    private void drawGrid(Graphics g, ImageLibrary library, Grid grid) {
        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                g.drawImage(library.getImage(grid.getCellAt(new GridPosition(x, y)).getCellType().name()),
                        gridStartingPointX + x * cellSize, gridStartingPointY + y * cellSize, cellSize, cellSize, null);
                g.drawRect(gridStartingPointX + x * cellSize, gridStartingPointY + y * cellSize, cellSize, cellSize);
            }
        }

        g2.setStroke(new BasicStroke(1));
    }

    /**
     * Draw the robot of the current level with a given graphics object, robot
     * and image library.
     *
     * @param g The given graphics object.
     * @param library The given image library.
     * @param robot The given robot.
     *
     * @effect The robot is drawn inside the grid at its current grid position.
     */
    private void drawRobot(Graphics g, ImageLibrary library, Robot robot) {
        GridPosition robotPosition = robot.getGridPosition();
        g.drawImage(library.getImage("robot" + robot.getDirection()),
                gridStartingPointX + robotPosition.getX() * cellSize, gridStartingPointY +
                        robotPosition.getY() * cellSize, cellSize, cellSize, null);
    }
}
