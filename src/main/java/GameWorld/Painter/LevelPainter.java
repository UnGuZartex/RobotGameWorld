package GameWorld.Painter;

import GameWorld.Grid;
import Images.ImageLibrary;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;

import java.awt.*;

/**
 * A class painting levels.
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
     * @param grid The given grid.
     * @param robot The given robot.
     * @param library The given image library.
     *
     * @effect The grid properties are calculated.
     * @effect The grid is drawn.
     * @effect The robot is drawn.
     */
    public void paint(Graphics g, Grid grid, Robot robot, ImageLibrary library) {
        calculateGridProperties(g, grid.getWidth(), grid.getHeight());
        drawGrid(g, grid, library);
        drawRobot(g, robot, library);
    }

    private void calculateGridProperties(Graphics g, int gridWidth, int gridHeight) {
        Rectangle clipRect = g.getClipBounds();
        cellSize = (int) Math.min((clipRect.getWidth() - minGridDelta) / gridWidth, (clipRect.getHeight() - minGridDelta) / gridHeight);
        gridStartingPointX = (int) (clipRect.getX() + (clipRect.getWidth() - (cellSize * gridWidth)) / 2);
        gridStartingPointY = (int) (clipRect.getY() + (clipRect.getHeight() - (cellSize * gridHeight)) / 2);
    }

    /**
     * Draw the robot of the current level with a given graphics object, robot
     * and image library.
     *
     * @param g The given graphics object.
     * @param grid The given grid.
     * @param library The given image library.
     *
     * @effect Each cell in the grid is drawn at the right coordinates and image.
     */
    private void drawGrid(Graphics g, Grid grid, ImageLibrary library) {
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
     * @param robot The given robot.
     * @param library The given image library.
     *
     * @effect The robot is drawn inside the grid at its current grid position.
     */
    private void drawRobot(Graphics g, Robot robot, ImageLibrary library) {
        GridPosition robotPosition = robot.getGridPosition();
        g.drawImage(library.getImage("robot" + robot.getDirection()),
                gridStartingPointX + robotPosition.getX() * cellSize, gridStartingPointY +
                        robotPosition.getY() * cellSize, cellSize, cellSize, null);
    }
}
