package GameWorld.Painter;

import GameWorld.Grid;
import Images.ImageLibrary;
import RobotCollection.Robot.Robot;
import RobotCollection.Utility.GridPosition;

import java.awt.*;

public class LevelPainter {

    private int cellSize;
    private int gridStartingPointX;
    private int gridStartingPointY;
    private static int minGridDelta = 20;

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

    private void drawRobot(Graphics g, Robot robot, ImageLibrary library) {
        GridPosition robotPosition = robot.getGridPosition();
        g.drawImage(library.getImage("robot" + robot.getDirection()),
                gridStartingPointX + robotPosition.getX() * cellSize, gridStartingPointY +
                        robotPosition.getY() * cellSize, cellSize, cellSize, null);
    }
}
